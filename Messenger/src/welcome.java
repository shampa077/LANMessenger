import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class welcome extends JFrame implements ActionListener{
    JLabel label_1;
    JButton Messenger;
    public login l;//keeping login reference

    public welcome() {
        welcomeLayout customLayout = new welcomeLayout();

        getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12));
        getContentPane().setLayout(customLayout);

        Icon bug = new ImageIcon( getClass().getResource( "71.gif" ) );
        label_1 = new JLabel("");
      	label_1.setIcon(bug);
        getContentPane().add(label_1);
        Messenger = new JButton("Messenger");
        Messenger.setForeground(Color.red);
        Messenger.setFont(new Font("Helvetica", Font.BOLD, 20));
        Messenger.addActionListener(this);
        getContentPane().add(Messenger);

        setSize(getPreferredSize());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }//end of welcome class's constructor

	//main class
    public static void main(String args[]) {
    	
        welcome window = new welcome();
	    window.setTitle("welcome");
        window.pack();
        window.show();
       
    }
    
    
     public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() ==  this.Messenger )
		{
			this.setVisible(false);
			l = new login();    //declaring login cls object
			l.setTitle("Login");
			l.pack();
        	l.show();
        	
        
		}
	
	}//end of actionperformed
}//end of welcome class 

class welcomeLayout implements LayoutManager {

    public welcomeLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 320 + insets.left + insets.right;
        dim.height = 335 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+40,insets.top+72,272,256);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+60,insets.top+8,192,56);}
    }
}
