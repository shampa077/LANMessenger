import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.InputStreamReader;

//socket serversocket in net
//serializable,buffer reader, obj input/output stream, input stream reader in io
public class Server implements Serializable 
{
	private ServerSocket ServSock;
	public Vector v;//for login information along with socket
	public Vector log;//for login information only with user name
	public Vector addRequest;

	Server()  
	{
		v=new Vector();
		log=new Vector();
		addRequest=new Vector();
	
		try
		{			
			ServSock = new ServerSocket(33333);
		
			while (true)
			{
			ServerThread m = new ServerThread(ServSock.accept(),v,log,addRequest);//for starting new thread
			}
		}
		catch(Exception e)
		{
			System.out.println("Server starts:"+e);
		}
	}//end of server cls constructor
	
	//server class's main
	public static void main(String args[]) 	throws UnknownHostException, IOException 
	{
		Server objServer = new Server();//declaring server cls object
	}
}//end of server class

 class ServerThread implements Runnable, Serializable
 {
	private Socket ClientSock;
	private Thread thr;
	private NetworkConnect nc;
	Vector v;
	Vector log;
	Vector addRequest;
	
	ServerThread(Socket client,Vector v,Vector log,Vector addRequest) 
	{
		this.v=v;
		this.log=log;
		this.addRequest=addRequest;
		this.ClientSock = client;//the client that server accept 
		this.thr = new Thread(this);//creating new thread for that client
		thr.start();
	}//end of serverthread cls constructor
	
	public void run() //override run method of runnable interface
	{
		this.nc=new NetworkConnect(ClientSock);//new network connect (the client)
		new ReadThreadServer(this.nc,this.v,this.log,this.addRequest);//read thread for server 
							
		
	}
}//end of serverthread cls



class ReadThreadServer implements Serializable,Runnable
{
	private Thread thr;
	private NetworkConnect nc;	
	Vector v;
	Vector log;
	Vector addRequest;


	public  ReadThreadServer(NetworkConnect nc,Vector v,Vector log,Vector addRequest) 
	{
		this.v=v;
		this.log=log;
		this.nc = nc;
		this.addRequest=addRequest;
		this.thr = new Thread(this);//new thread for reading
		thr.start();
	}//end of readthreadserver class's constructor
	
	public void run() //override run method of runnable interface
	{
		try
		{
			while(true)
			{
				Object o=nc.read();	//this read is networkconnect cls function															
				if(o==null) 
				{
					
				}
				else this.process(o);//instance of any type object				
			}			
		}catch(Exception e)
		{
			System.out.println (e);
		}			
		nc.closeConnection();//if any problem occur
	}//end of run function
	
	
	public void process(Object o)//this is a function of readthreadserver cls
	{
		System.out.println (o);
		if(o instanceof LoginInfo)//if instance of logininfo(for login) class obj
		{
			int flag1=0;//for checking if one name and multi user
			LoginInfo l=(LoginInfo)o;			
			String name=l.getName();
			String password=l.getPassword();
			for(int i=0;i<log.size();i++)   //checking multi user
			{
					String n=(String)log.elementAt(i);
					System.out.println (n);
					if(name.equals(n))
					{
						flag1=1;
						JOptionPane.showMessageDialog(null,"Already this user is sign in");
						nc.write("false");
						return;				
					}			
			}
			FileOperation fo=new FileOperation();//new file operation check pasword
			boolean b=fo.CheckPassword("user",name,password);//return true or false for check password				
			System.out.println (b);			
			if(b==true)
			{
				
				this.nc.name=l.getName();						
				this.v.add(nc);
				this.log.add(nc.name);
				System.out.println ("Start");
				for(int i=0;i<v.size();i++)
				{
					NetworkConnect n=(NetworkConnect)v.elementAt(i);
					System.out.println (n.name + " -- " + n.getSocket().toString());
					String p=(String)log.elementAt(i);
					System.out.println(p);
				}
				System.out.println ("End");
				
				nc.write("true");
				ContactInfo sentinel=new ContactInfo();//one sentinel add request
				sentinel.setName("wrong");
				sentinel.setContact("wrong");
				sentinel.setStatus(0);
				this.addRequest.add(sentinel);//this is for the global add request vector
				Vector sendRequest=new  Vector();//this is for sending add request to individuals
				int flag=0;
				for(int i=0;i<addRequest.size();i++)
				{
				    ContactInfo add=(ContactInfo)addRequest.elementAt(i);
					System.out.println (add.getName() + " -- " + add.getContact()+ " -- "+add.getStatus());
					String so=add.getContact();//contact here is the person logins
					String sn=l.getName(); //this person's name
					String sc=add.getName();//one who send addrequest              
					if(so.equals(sn))//if got one 
					{
						AddRequestinfo af=new AddRequestinfo();
						af.setReceivername(sn);
						af.setSendername(sc);
						af.setStatus(1);
						sendRequest.add(af);
						flag=1;//added atleast one
						System.out.println("sending contact");
						
							
					}
					
				}
				if(flag==1)
				{
					nc.write(sendRequest);//write that
					nc.write(fo.SendContact(name));	
				}
				if(flag==0)
				{
				
					AddRequestinfo af=new AddRequestinfo();//if not got then a dummy request send
					af.setReceivername("wrong");
					af.setSendername("wrong");
					af.setStatus(0);
					sendRequest.add(af);
					System.out.println("sending contact");
					nc.write(sendRequest);
					nc.write(fo.SendContact(name));	
				}	
				
									
			}
			else nc.write("false");
			
			
			
				
		}		
		if(o instanceof ChatInfo)//if instance of chatinfo(for chat) class obj
		{
			ChatInfo c=(ChatInfo)o;
			String to=c.getTo();
			String from=c.getFrom();
			String message=c.getMessage();
			for(int i=0;i<v.size();i++)
			{
				
				NetworkConnect n=(NetworkConnect)v.elementAt(i);								
				if(n.name.equals(to))
				{
					System.out.println ("Writing to : " + n + " : " + message);
					n.write(from + " : " + message + "\n");	
					break;					
				}
			}	
			System.out.println ("Writing to : " + nc + " : " + message);	
			nc.write(from + " : " + message + "\n");	
		}
			
		if(o instanceof RegisterInfo)//if instance of registerinfo(for register) class obj
		{
			System.out.println ("In ri");
			RegisterInfo r=(RegisterInfo)o;
			String name=r.getName();
			String password=r.getPassword();
			
			String a=name+":"+password;
			
			System.out.println (a);
			FileOperation fo=new FileOperation();//new file operation 
			fo.FileWrite("user",a);//adding in user named file
			fo.FileWrite(name,"wrong:0");//adding in contact list file		
		}
		if(o instanceof ContactInfo)//if instance of contactinfo(for adding contact) class obj
		{
			
			ContactInfo r=(ContactInfo)o;
			String name=r.getName();
			String contact=r.getContact();
			contact=contact+":"+r.getStatus();
			//FileOperation fo=new FileOperation();
			//fo.FileWrite(name,contact);	
			//System.out.println("adding"+contact);
			this.addRequest.add(r);	
		}
		if(o instanceof AfterTheAddRequestinfo)//if instance of AfterTheAddRequestinfo(for accepting add ) class obj
		{
			
			AfterTheAddRequestinfo r=(AfterTheAddRequestinfo)o;
			String name=r.getSendername();
			String contact=r.getReceivername();
			contact=contact+":"+r.getStatus();
			if(r.getStatus()==1)//if accepted 
			{
				
				String temp=r.getReceivername();
				int p;
				FileOperation fo1=new FileOperation();
				p=fo1.FileRead(name,temp);
				if(p==0)
				{
				
					FileOperation fo=new FileOperation();
					fo.FileWrite(name,contact);
				}
				if(p==1)
				{
					System.out.println("already in contactlist");
				}
			
			
				String name1=r.getReceivername();
				String contact1=r.getSendername();
				contact1=contact1+":"+r.getStatus();
				
				FileOperation fo2=new FileOperation();
				p=fo2.FileRead(temp,name);
				if(p==0)
				{
				
					FileOperation fo3=new FileOperation();
					fo3.FileWrite(name1,contact1);
					System.out.println("adding by receiver "+contact);
				}
				if(p==1)
				{
					System.out.println("already in contactlist");
				}
				
				//
			}
			//for removing the add request from vector
			for(int i=0;i<addRequest.size();i++)
			{
				    	
				ContactInfo n=(ContactInfo)addRequest.elementAt(i); 
				String si=n.getContact();
				String sm=r.getReceivername();
				System.out.println(si+"-"+sm);
				if(si.equals(sm))
				{
					addRequest.removeElementAt(i);
					System.out.println("add request removed");
					break;					
				}			
			
					
			}
			for(int i=0;i<addRequest.size();i++)
			{
				    	
				ContactInfo n1=(ContactInfo)addRequest.elementAt(i); 	
				System.out.println("now present:"+n1.getName()+"request");
			
					
			}
				//
			
			
		}
		if(o instanceof FileDTO)////if instance of filedto(for file) class obj
		{
			System.out.println("in server file");
			FileDTO c=(FileDTO)o;
			String to=c.getTo();
			String from=c.getFrom();
			
			for(int i=0;i<v.size();i++)
			{
				
				NetworkConnect n=(NetworkConnect)v.elementAt(i);								
				if(n.name.equals(to))
				{
					
					n.write(c);	
					break;					
				}
			}	
				
		}
		if(o instanceof Request)//if instance of Request class obj
		{
			System.out.println ("In request");
			Request r=(Request)o;
			String name=r.getName();
			System.out.println (name);
			boolean b=false;
			for(int i=0;i<log.size();i++)
			{
				String n=(String)log.elementAt(i);
				System.out.println (n);
				if(name.equals(n))
				{
					b=true;
					break;					
				}			
			}
			System.out.println ("Returned : " + b);
			Boolean bwr=new Boolean(b);
			nc.write(bwr);
					
		}//end of request
				
		if(o instanceof Logout)//if instance of logout class obj
		{
			System.out.println("in log out");
			Logout c=(Logout)o;
			String f=c.getName();
		
			
			for(int i=0;i<v.size()&& i<log.size();i++)
			{
				
				NetworkConnect n=(NetworkConnect)v.elementAt(i);								
				if(n.name.equals(f))
				{
					
					v.removeElementAt(i);
					log.removeElementAt(i);
					System.out.println("removed");
					break;				
				}
			}	
		
		}
		
		
		
				
	}//end of process function
	
	


}//end of readthreadserver class


class FileOperation
{
	
	FileOperation()
	{
	}
	
	public boolean CheckPassword(String fname,String user,String password)
	{
		try
		{
			File f=new File(fname);		
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			while(true)
			{
				String s=br.readLine();
				if(s==null) break;
				StringTokenizer st=new StringTokenizer(s,":");
				String s1=st.nextToken();
				String s2=st.nextToken();
				if(s1.equals(user) && s2.equals(password)) 
				{
					br.close();
					fr.close();	
					return true;				
				}
			}
			br.close();
			fr.close();	
		}catch(Exception e)
		{
			System.out.println (e);
		}
		System.out.println ("Done");		
		return false;
	}//end of check password function
	
	
	public Vector SendContact(String user)
	{
		Vector c=new Vector();
		try
		{
			File f=new File(user);		
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			while(true)
			{
				String s=br.readLine();
				if(s==null) break;
				StringTokenizer st=new StringTokenizer(s,":");
				String s1=st.nextToken();
				String s2=st.nextToken();
				
				if(s2.equals("1"))	
					c.add(s1);
								
			}
			br.close();
			fr.close();	
		}catch(Exception e)
		{
			System.out.println (e);
		}
	 	System.out.println ("Contact:"+c.size());		
		return c;
	}//end of send contact function
	 
	
	public void FileWrite(String fname,String text)
	{
		try
		{
			File f=new File(fname);	
			FileWriter fw=new FileWriter(f,true);			
			PrintWriter pw=new PrintWriter(fw);			
			pw.println(text);		
			pw.close();
			fw.close();	
		}catch(Exception e)
		{
			System.out.println (e);
		}
		System.out.println ("Done");		
	}//end of filewrite function
	public int FileRead(String fname,String message)
	{
		try
		{
			File f=new File(fname);		
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			while(true)
			{
				String s=br.readLine();
				if(s==null) break;
				System.out.println (s);
				StringTokenizer st=new StringTokenizer(s,":");
				String s1=st.nextToken();
				String s2=st.nextToken();
			
				if(s1.equals(message)&& s2.equals("1")) 
				{
					br.close();
					fr.close();	
					return 1;				
				}
			}
			br.close();
			fr.close();	
		
			
		}catch(Exception e)
		{
			System.out.println (e);
		}
		System.out.println ("Done");
		return 0;	
	}//end of fileread function
	
	
	
}//end of class fileoperation