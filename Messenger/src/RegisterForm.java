import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterForm extends JFrame implements ActionListener{
    JLabel profileInformation;
    JLabel FirstName;
    JTextField textfield_1;
    JLabel LastName;
    JTextField textfield_2;
    JLabel EmailId;
    JTextField textfield_3;
    JLabel AccountInformation;
    JLabel UserName;
    JLabel Password;
    JLabel RetypePassword;
    JTextField textfield_4;
    JPasswordField textfield_5;
    JPasswordField textfield_6;
    JButton Submit;
    JButton Clear;
    JButton Cancel;
    login log;//reference og login
    public String name;
    public String password;

    public RegisterForm(login l)
     {
    	
    	this.log=l;    	
        RegisterFormLayout customLayout = new RegisterFormLayout();
        
        Container c=this.getContentPane();

        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        c.setLayout(customLayout);

        this.profileInformation = new JLabel("Profile Information");
        this.profileInformation.setFont(new Font("Helvetica", Font.BOLD, 15)); 
        this.profileInformation.setForeground(Color.blue);
        c.add(this.profileInformation);

        this.FirstName = new JLabel("First Name");
        c.add(this.FirstName);

        this.textfield_1 = new JTextField("");
        c.add(this.textfield_1);

        this.LastName = new JLabel("Last Name");
        c.add(this.LastName);

        this.textfield_2 = new JTextField("");
        c.add(this.textfield_2);

        this.EmailId = new JLabel("Email Id");
        c.add(this.EmailId);

        this.textfield_3 = new JTextField("");
        c.add(this.textfield_3);

        this.AccountInformation = new JLabel("Account Information");
        this.AccountInformation.setFont(new Font("Helvetica", Font.BOLD, 15)); 
        this.AccountInformation.setForeground(Color.blue);
		c.add(this.AccountInformation);

        this.UserName = new JLabel("User Name");
        c.add(this.UserName);

        this.Password = new JLabel("Password");
        c.add(this.Password);

        this.RetypePassword = new JLabel("Retype Password");
        c.add(this.RetypePassword);

        this.textfield_4 = new JTextField("");
        c.add(this.textfield_4);

        this.textfield_5 = new JPasswordField("");
        c.add(this.textfield_5);

        this.textfield_6 = new JPasswordField("");
        c.add(this.textfield_6);

        this.Submit = new JButton("Submit");
        Submit.addActionListener(this);
        c.add(this.Submit);

        this.Clear = new JButton("Clear");
        Clear.addActionListener(this);
        c.add(this.Clear);
        
        this.Cancel = new JButton("Cancel");
        Cancel.addActionListener(this);
        c.add(this.Cancel); 
        this.setResizable(false);       

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                
                setVisible(false);              
                log.setVisible(true);
            }
        });
    }//end of registerform cls constructor


    
    
    public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() ==  this.Submit)
		{
			String s1=this.textfield_4 .getText();
      		String s2=new String( this.textfield_5 .getPassword());
            String s3=new String(this.textfield_6.getPassword());
            
            if(s1.equals("") || s2.equals("") ||textfield_1.getText().equals("") || this.textfield_2.getText().equals("") ||s3.equals("")||this.textfield_3 .getText().equals(""))
			{
					JOptionPane.showMessageDialog(null,"You have left one or all the fields blank" ); 	
			
			}
			else if(!s2.equals(s3))
            {
            	JOptionPane.showMessageDialog(null,"Passwords don't match" ); 	
                this.textfield_5.setText("");
                this.textfield_6.setText("");
            } 
            else
            {
            
				this.name=this.textfield_4.getText();
				this.password=this.textfield_5.getText();						
				RegisterInfo r=new RegisterInfo();
				r.setName(name);           	
           		r.setPassword(password);
           		NetworkConnect nc=new NetworkConnect(log.Ip,33333); //creating a networkconnect          	
           		nc.write(r);	
           		JOptionPane.showMessageDialog(null,"submission succesful");
           		nc.closeConnection();//closing the connection
           		setVisible(false);        
            	log.setVisible(true);	
           }					
		}
		else if(ae.getSource() ==  this.Clear)
		{
			
			textfield_1.setText("");
			textfield_2.setText("");
			textfield_3.setText("");
			textfield_4.setText("");
			textfield_5.setText("");
			textfield_6.setText("");
		}
		
		else if(ae.getSource() ==  this.Cancel)
		{
			setVisible(false);        
            log.setVisible(true);	
		
		}
		
		
	}//end of actionperformed
}//end of register cls

class RegisterFormLayout implements LayoutManager {

    public RegisterFormLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 501 + insets.left + insets.right;
        dim.height = 528 + insets.top + insets.bottom;


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
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+40,168,24);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+88,120,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+88,184,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+136,120,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+136,184,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+184,120,24);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+184,184,24);}
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+264,168,24);}
        c = parent.getComponent(8);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+312,120,24);}
        c = parent.getComponent(9);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+360,120,24);}
        c = parent.getComponent(10);
        if (c.isVisible()) {c.setBounds(insets.left+96,insets.top+408,120,24);}
        c = parent.getComponent(11);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+312,184,24);}
        c = parent.getComponent(12);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+360,184,24);}
        c = parent.getComponent(13);
        if (c.isVisible()) {c.setBounds(insets.left+288,insets.top+408,184,24);}
        c = parent.getComponent(14);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+480,120,32);}
        c = parent.getComponent(15);
        if (c.isVisible()) {c.setBounds(insets.left+176,insets.top+480,120,32);}
        c = parent.getComponent(16);
        if (c.isVisible()) {c.setBounds(insets.left+328,insets.top+480,120,32);}
    }
}
