import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Icon; 
import javax.swing.ImageIcon;
import javax.swing.tree.*;
import java.util.*;
import java.net.*;
import java.io.*;
//tree in swing
public class messenger1 extends JFrame implements ActionListener
{
    JTree jt;
    JScrollPane jtpane;
    JButton Addcontact;
    JButton Chat;
    JButton File1;
    JButton Contact;
    JButton Logout;
    JButton Password;
    JButton Offline;
    JLabel Contacts;
    JLabel label_1;    
    Chat ct;//reference of chat
    Vector v;//login persons info vector
    String f;//for true or false(online or offline)
    public String contact;//for adding contact
    public login lg;//reference of login
    public DefaultMutableTreeNode top; 
    private Icon off,con;  
    String selectedContact;//one who u want to chat/send file
	showContact sc;// for showing contact
	
    public messenger1(login lg)
     {
     	
        messenger1Layout customLayout = new messenger1Layout();
        
       	this.lg=lg;
        Container c=this.getContentPane();
	
        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        c.setLayout(customLayout);
	
		con = new ImageIcon("online.gif");
        off = new ImageIcon("contact.gif");
		Icon bug = new ImageIcon( getClass().getResource( "bug1.gif" ) );
		Icon a = new ImageIcon( getClass().getResource( "d.gif" ) );
		Icon e = new ImageIcon( getClass().getResource( "ef.gif" ) );
		Icon f = new ImageIcon( getClass().getResource( "online.gif" ) );
		Icon g = new ImageIcon( getClass().getResource( "offline.gif" ) );
		Icon t = new ImageIcon( getClass().getResource( "t.gif" ) );
		Icon r = new ImageIcon( getClass().getResource( "r.gif" ) );
		Icon df = new ImageIcon( getClass().getResource( "df.gif" ) );
		Icon uf = new ImageIcon( getClass().getResource( "garfield.gif" ) );
		Icon p = new ImageIcon( getClass().getResource( "happyf~3.gif" ) );
		
        this.v=(Vector)lg.nc.read();//server done 2 things inlogin write 2things 1st return true /false and 2nd return contact list. 
        System.out.println ("Contact : " + v.size());     
            
        top=new DefaultMutableTreeNode("Contacts");
        for(int i=0;i<v.size();i++)
        {
        	if(!v.elementAt(i).equals(""))
        	{
        	
        	System.out.println ("Adding");
        	top.add(new DefaultMutableTreeNode((String)v.elementAt(i)));
        	}
        }        
        jt=new JTree(top);
    	jt.expandRow(1); 
    	DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
    	renderer3.setOpenIcon(off);
    	renderer3.setClosedIcon(con);
    	renderer3.setLeafIcon(con);    	
    	jt.setCellRenderer(renderer3);
    	jtpane = new JScrollPane(jt);
    	getContentPane().add(jtpane);
        jt.addMouseListener(new MouseAdapter()
        {
        	public void mouseClicked(MouseEvent me)
        	{
        	
        		TreePath tp=jt.getPathForLocation(me.getX(),me.getY());
        		if(tp!=null)
        		{   
        		 
        		    String p=tp.toString();
        		    StringTokenizer st = new StringTokenizer(p," ],");
        		    
        			while(st.hasMoreTokens())
        			{
        			
        			  	selectedContact=st.nextToken();
        			  	System.out.println(	selectedContact);         			  	      			   	
        		     }
        		     
        		     f2(); 
        		     System.out.println (selectedContact);
        		     
        		}        		
        	}
        }); 
        
        Addcontact = new JButton("Add");
        Addcontact.setIcon(r);
        Addcontact.addActionListener(this);
        c.add(Addcontact);

        Chat = new JButton("Chat");
        Chat.setIcon(p);
        Chat.addActionListener(this);
        getContentPane().add(Chat);

        File1 = new JButton("File");
        File1.setIcon(a);
        File1.addActionListener(this);
       	c.add(File1);

        Contact = new JButton("Contact");
        Contact .setIcon(bug);
        Contact.addActionListener(this);
        c.add(Contact);

        Logout = new JButton("Logout");
        Logout.setIcon(e);
        Logout.addActionListener(this);
        c.add(Logout);

        Contacts = new JLabel("Contacts");
      	Contacts .setIcon(f);
        c.add(Contacts);

        label_1 = new JLabel("");
        label_1.setIcon(uf);
        c.add(label_1);
		
        setSize(getPreferredSize());
        this.setResizable(false);

        addWindowListener(new WindowAdapter()
         {
            public void windowClosing(WindowEvent e) 
            {
            	
            	f1();
                System.exit(0);
            }
        });
        
      
		
        this.ct=new Chat(this);//problem
		this.ct.setTitle("Chat:"+this.lg.name1);
		this.ct.pack();
        this.ct.show();
        this.ct.setVisible(false);
        new ReadThread(lg.nc,this.ct,this);
        
       
    }//end of messenger cls constructor
	
    
     public void f1()//function for closing client
     {
     	
            System.out.println ("Exit");
        	Logout lf=new Logout();
			lf.setName(lg.name1);
			lg.nc.write(lf);
            this.lg.nc.closeConnection();	
      }
     
      
      public void f2()//function for print online offline
      {
      	if(this.selectedContact.contains("Contacts")) return;
      	System.out.println ("Request Sent");
      	Request ro=new Request();
        ro.setName(selectedContact);
        lg.nc.write(ro);
        
      	
      }
     public void actionPerformed(ActionEvent ae)
	 {
		if(ae.getSource() ==  this.Addcontact)
		{
			
			this.contact= JOptionPane.showInputDialog(this,"What is the contact name?");
			System.out.println ("User : " + this.contact);
			ContactInfo ci=new ContactInfo();
			ci.setName(lg.name1);
			ci.setContact(this.contact);
			lg.nc.write(ci);
			
			
		}
		else if(ae.getSource() ==  this.Chat )
		{
		
		
			ct.setVisible(true);
        		
		}
		else if(ae.getSource() ==  this.File1)//sending file
		{
		
			//JOptionPane.showMessageDialog(null,"File");  
			try{
			
			FileDialog fd=new FileDialog(this);
			fd.setMode(FileDialog.LOAD);
			fd.setTitle("File Transfer GUI:"+lg.name1);
			fd.show();
			File f;
			f=new File(fd.getDirectory()+fd.getFile());
			System.out.println(f.length());
			int size=(int)f.length();
			byte b[]=new byte[size];
			String name=f.getName();
			FileInputStream fis=new FileInputStream(f);
			fis.read(b);
			fis.close();
			FileDTO fdto=new FileDTO(size,b,name);
			fdto.setFrom(lg.name1);
			fdto.setTo(selectedContact);
			lg.nc.write(fdto);
			System.out.println("send file");
			}
			catch(Exception e)
			{
				System.out.println (e);
				
			}
			   
		
		}//end of file action
		
		else if(ae.getSource() ==  this.Contact)
		{
			
			sc = new showContact(this);

        	sc.setTitle("");
        	sc.pack();
        	sc.show();
		}
		
		else if(ae.getSource() ==  this.Logout)
		{
			
				JOptionPane.showMessageDialog(null,"logout");
				
            	System.out.println ("Exit");
        		Logout lf=new Logout();
				lf.setName(lg.name1);
				lg.nc.write(lf);
            	this.lg.nc.closeConnection();	
				System.exit(0);
		}
		
		
		
		
	}//end of actionperformed

  
}//end of messenger cls

class messenger1Layout implements LayoutManager {

    public messenger1Layout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 302 + insets.left + insets.right;
        dim.height = 460 + insets.top + insets.bottom;

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
        
        if (c.isVisible()) {c.setBounds(insets.left+144,insets.top+56,152,384);}        
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+56,128,64);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+136,128,64);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+216,128,64);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+296,128,64);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+376,128,64);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+184,insets.top+16,104,32);}
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+8,128,40);}
    }
}


