import java.io.*;

public class FileDTO implements Serializable
{
	private int size;
	private byte b[];
	private String name;
	private String from,to;
	
	FileDTO()
	{
	}
	
	public FileDTO(int s,byte a[],String name)
	{
		this.size=s;
		this.b=a;
		this.name=name;
	}
	
	public int getSize()
	{
		return this.size;
	}
		
	public byte[] getBytes()
	{
		return this.b;
	}
	
	public String getName()
	{
		return this.name;
	}
  public void setFrom(String s) { from=s; }
  public String getFrom() { return from; }
  public void setTo(String s) {to=s; }
  public String getTo() { return to; }
		
}//end of filedto