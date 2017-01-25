
import java.io.*;

public class  LoginInfo implements Serializable{
  private String name,password;
 
  public LoginInfo () { }
  /** constructor with parameters */
  public LoginInfo ( String s1,String s2) {
    setName(s1);
    setPassword(s2);
   
    
  }
  public void setName(String s) { name=s; }
  public String getName() { return name; }
  public void setPassword(String s) { password=s; }
  public String getPassword() { return password; }
  
  
}//end of logininfo