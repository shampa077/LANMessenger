import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//socket in net,obj input/output stream,stream reader  in io
public class NetworkConnect implements Serializable 
{
	private Socket socket;
	public  String name="";
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	Object o;
	

		
	public NetworkConnect(String s,int port)
	{
		try
		{
			this.socket=new Socket(s,port);  
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream(socket.getInputStream());
		} 
		catch (Exception e) 
		{
			System.out.println("In NetworkConnect" + e.toString());
		}
	}
		
	public NetworkConnect(Socket s)
	{
		try
		{
			this.socket = s;
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream(socket.getInputStream());
		} 
		catch (Exception e) 
		{
			System.out.println("In NetworkConnect" + e.toString());
		}
	}
	
	public Object read() //function for read
	{
		try 
		{	o=ois.readObject();
		} 
		catch (Exception e) 
		{
		  
		  return null;
		}
		return o;
	}
	
	public void write(Object o)//function for write 
	{
		try
		{
			oos.writeObject(o);
		}
		catch (IOException e) 
		{
			//System.out.println("Writing  Error in network:" + e.toString());
		}
	}
	public void closeConnection() {        //function for close connection
		try {
			
			ois.close();
			oos.close();
		} 
		catch (Exception e) {
			System.out.println("Closing Error in network: "  + e.toString());
		}
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}
	


}//end of networkconnect cls