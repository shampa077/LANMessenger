import java.io.*;
import java.util.*;

public class Logout implements Serializable{
  private String name;
  
 
  public Logout() 
  {
  	
  }
  /** constructor with parameters */
  public Logout ( String s1) {
    setName(s1);
    
   
    
  }
  public void setName(String s) { name=s; }
  public String getName() { return name; }
  
  
  }//end of logout
  
  
  
  