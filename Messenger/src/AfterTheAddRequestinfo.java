import java.io.*;

public class  AfterTheAddRequestinfo implements Serializable{
  private String receivername,sendername;
  public int status;
 
  public AfterTheAddRequestinfo () { }
  /** constructor with parameters */
  public AfterTheAddRequestinfo ( String s1,String s2) {
    setReceivername(s1);
    setSendername(s2);
   setStatus(0);
    
  }
  public void setReceivername(String s) {receivername =s; }
  public String getReceivername() { return receivername; }
  public void setSendername(String s) {sendername=s; }
  public String getSendername() { return sendername; }
  public void  setStatus(int  s) {status =s; }
  public int getStatus() { return status; }
  
  
}//end of contactinfo