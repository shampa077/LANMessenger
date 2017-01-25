import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.FileDialog;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

//read thread for networkconnect
public class ReadThread implements Serializable,Runnable
{
	private Thread thr;
	private NetworkConnect nc;//reference of networkconnect
	public Chat c;//reference of chat 
	messenger1 m;//reference of messenger

	public ReadThread(NetworkConnect nc,Chat c,messenger1 m) 
	{
		this.nc = nc;
		this.c=c;
		this.m=m;
		this.thr = new Thread(this);
		thr.start();
	}
	
	public void run() //override the run method of runnable cls
	{
		try
		{
			while(true)
			{
				
				Object t=nc.read();
				System.out.println ("Read :"+t);
				if(t instanceof String)//if instance of chat(string)
				{				
					if(!t.equals(""))
       				{
       						System.out.println("client :"+t.toString());				
					 		this.c.textOut.setText(this.c.textOut.getText()+t.toString());
					 		this.c.setVisible(true);
					}	
																
				}//end of string				
				if(t instanceof Boolean)
				{
					Boolean b=(Boolean)t;
					if(b.booleanValue()==false)
       				{
        				JOptionPane.showMessageDialog(null,"Status : Offline");
        	 		}  
       				else 
       				{		
	       				JOptionPane.showMessageDialog(null,"Status : Online");
    	   			}		
				
				}//end of boolean
				if(t instanceof FileDTO)//if instance of filedto (receiving file)
				{
					
				FileDTO r=(FileDTO)t;
				
				String cop=new String(r.getFrom()+" wants to send you a file named: "+r.getName()+" You accept?");
				if(JOptionPane.showConfirmDialog(null,cop,"File Save:"+r.getTo(),JOptionPane.YES_NO_OPTION)==0)
				{
				
				try{
					
					
			
					int size=r.getSize();
					byte b[]=new byte[size];
					b=r.getBytes();
					String str=r.getName();
					FileDialog fd=new FileDialog(this.m);
					fd.setMode(FileDialog.SAVE);
					fd.setTitle(" Saving : "+r.getTo()+":"+str);
					fd.setFile(str);
					fd.show();
					File f=new File(fd.getDirectory()+fd.getFile());
					FileOutputStream fos=new FileOutputStream(f);
					fos.write(b);
					fos.flush();
					fos.close();
					System.out.println("receive file");
				
					}
					catch(Exception e)
					{
					System.out.println (e);
				
					}
				}
			  }//end of file
			 
				
			}//end of while
		}catch(Exception e)
		{
			System.out.println (e);
		}			
		nc.closeConnection();//close connection
	}//end of run method
	
}//end of networkconnect cls

