import java.io.*;
import java.util.*;

public class Request implements Serializable{
  private String name,f;
  
  
 
  public Request () 
  {
  	
  }
  /** constructor with parameters */
  public Request ( String s1) {
    setName(s1);
    
    
    
   
    
  }
  
  public void setName(String s) { name=s; }
  public String getName() { return name; }
  public void setf(String s) { f=s; }
  public String getf() { return f; }
  
  
  
  }
  
  
  
  