import java.io.*;

public class  RegisterInfo implements Serializable{
  private String name,Password;
 
  public RegisterInfo () { }
  /** constructor with parameters */
  public RegisterInfo( String s1,String s2) {
    setName(s1);
    setPassword(s2);
   
    
  }
  public void setName(String s) { name=s; }
  public String getName() { return name; }
  public void setPassword(String s) { Password=s; }
  public String getPassword() { return Password; }
  
  
}//end of registerinfo cls