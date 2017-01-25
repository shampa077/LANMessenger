import java.io.*;

public class  ContactInfo implements Serializable{
  private String name,contact;
  public int status;
 
  public ContactInfo () { }
  /** constructor with parameters */
  public ContactInfo ( String s1,String s2) {
    setName(s1);
    setContact(s2);
   setStatus(0);
    
  }
  public void setName(String s) { name=s; }
  public String getName() { return name; }
  public void setContact(String s) {contact=s; }
  public String getContact() { return contact; }
  public void  setStatus(int  s) {status =s; }
  public int getStatus() { return status; }
  
  
}//end of contactinfo