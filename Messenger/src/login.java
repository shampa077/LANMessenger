import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.JOptionPane;

public class login extends JFrame implements ActionListener{
     public JLabel name;
     public JTextField	textfield_1;//for name
     public JLabel password;
     public JPasswordField textfield_2;//for password
     public JButton ok;
     public JButton register;
     public JButton cancel;
	 public messenger1 me;//reference of messenger
	 public NetworkConnect nc;//reference of networkconnect(client)
	 public LoginInfo log;//reference of logininfo
	 public String Ip;//ip address of server
	 public String name1;//user name
	 public Vector v3;//vector for series of ass request 
	 public AddRequestinfo v1;//for add request
	 //int addstatus; 
     
     public login() 
     {
        loginLayout customLayout = new loginLayout();
        
        Container c=this.getContentPane();

        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        c.setLayout(customLayout);

	
        this.name = new JLabel("Name");
        c.add( this.name);

        this.textfield_1 = new  JTextField("");
       	c.add( this.textfield_1);

        this.password = new JLabel("Password");
        c.add( this.password);

        this.textfield_2 = new  JPasswordField("");
        c.add( this.textfield_2);

        this. ok = new JButton("Ok");
        ok.addActionListener(this);
        c.add( this.ok);

        this.register = new JButton("Register");
        register.addActionListener(this);
        c.add( this.register);
		
        this.cancel = new JButton("Cancel");
       	cancel.addActionListener(this);
        c.add( this.cancel);
		
        this.setSize(getPreferredSize());
        this.setVisible(true);	// does not effect
		this.setResizable(false); 
	  	Ip=new String("127.0.0.1");
	  	addWindowListener(
         new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
            	System.out.println ("Exit");
            	nc.closeConnection();//if donot want to login no problem connection is closed if anywherer in register it is opened
            	System.exit(0);
            	
            	
            }
         }
      );   
	  	 
	  	 
     }//end of login class's constructor
	
	
	
   
   	public void actionPerformed(ActionEvent ae)
	{
		String s1,s2;
	
		if(ae.getSource() ==  this.ok)
		{
			s1=this.textfield_1.getText();
			s2=new String(this.textfield_2.getPassword());
			if(s1.equals("") || s2.equals(""))
			{
			
				JOptionPane.showMessageDialog(null,"You have left one or all the fields blank" ); 	
        	}
        	else
        	{        		
				this.name1=s1;				
            	log=new LoginInfo(s1,s2);//declare logininfo class's obj
            	this.nc=new NetworkConnect(Ip,33333);//declare networkconnect class's obj
            	if (this.nc==null)
            	{
            		System.out.println("problem");
            	}
            	nc.name=name1;
            	nc.write(log);//send to server
            	String p=(String)nc.read();//server send true/false
            	if(p.equals("false"))
            	{
            		JOptionPane.showMessageDialog(null,"Unsuccesful login");   
            		this.textfield_1.setText("");         		          		           		
            		this.textfield_2.setText("");
            		            		
            	}
            	else
            	{
            		
            		JOptionPane.showMessageDialog(null,"succesful login");
            		
            		this.v3=(Vector)nc.read();//read the request list
            	
            		for(int i=0;i<v3.size();i++)
            		{
            		
            			
            			this.v1=(AddRequestinfo)v3.elementAt(i);
        				int k=v1.getStatus();
						System.out.println("addrequest status:"+k);
        				if(k==1)
        				{
        			
        					if(JOptionPane.showConfirmDialog(null,v1.getSendername()+" wants to add you.You accept?","AddRequest: "+this.name1+":",JOptionPane.YES_NO_OPTION)==0)
        					{
        						System.out.println("yes accept");
        						AfterTheAddRequestinfo af=new AfterTheAddRequestinfo();
        						af.setReceivername(this.name1);	
        						af.setSendername(v1.getSendername());
        						af.setStatus(1);
        						nc.write(af);
        					}
        					else
        					{
        					
        						System.out.println("not accepted");
        						AfterTheAddRequestinfo af=new AfterTheAddRequestinfo();
        						af.setReceivername(this.name1);	
        						af.setSendername(v1.getSendername());
        						af.setStatus(0);
        						nc.write(af);	
        					}
        				}
        			}
            		me = new messenger1(this);//declare messenger class's obj
					this.setVisible(false);
        			me.setTitle(this.name1);
        			me.pack();
        			me.show();
        			
        		}  		
        	}	
		}//end of ok buttons action
		else if(ae.getSource() ==  this.register)
		{
			
		   this.setVisible (false);
           RegisterForm rf=new RegisterForm(this);//declare register class's obj
           rf.pack();         
           rf.show();			
		
		}
		else if(ae.getSource() ==  this.cancel)
		{
			
			System.exit(0);
		}
	}//end of action performed
}//end of login class

class loginLayout implements LayoutManager {

    public loginLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 389 + insets.left + insets.right;
        dim.height = 204 + insets.top + insets.bottom;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Component c;
        c = parent.getComponent(0);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+24,72,24);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+136,insets.top+24,152,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+72,72,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+136,insets.top+72,152,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+160,72,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+128,insets.top+160,112,24);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+272,insets.top+160,90,24);}
    }
}

