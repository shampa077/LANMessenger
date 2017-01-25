import java.io.*;

public class  ChatInfo implements Serializable{
  private String from,to,message;
 
  public ChatInfo () 
  {
  	 
  }
  /** constructor with parameters */
  public ChatInfo ( String s1,String s2,String s3) 
  {
    setFrom(s1);
    setTo(s2);
   
    
  }
  public void setFrom(String s) { from=s; }
  public String getFrom() { return from; }
  public void setTo(String s) {to=s; }
  public String getTo() { return to; }
  public void setMessage(String s) { message=s; }
  public String getMessage() { return message; }
  
  
}//end of chatinfo