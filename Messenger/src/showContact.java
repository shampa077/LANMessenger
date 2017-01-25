import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class showContact extends JFrame {
    JLabel Contact;
    JTextArea area;
    JScrollPane sp_area;
    JScrollBar scrollbar_v_1;
	messenger1 m;
    public showContact(messenger1 m) {
    	this.m=m;
        showContactLayout customLayout = new showContactLayout();
		
		Container c=this.getContentPane();
        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        c.setLayout(customLayout);
		c.setBackground(Color.blue);
        Contact = new JLabel("");
        Icon a = new ImageIcon( getClass().getResource( "eastergoodwishes_215.gif" ) );
        Contact.setIcon(a);
        c.add(Contact);

        area = new JTextArea("");
       	area.setEditable(false);
       	area.setFont(new Font("Helvetica", Font.BOLD, 12));
       	area.setForeground(Color.BLUE);
       	area.setText("                  CONTACT OF "+ m.lg.name1+"\n");
        sp_area = new JScrollPane(area);
    	
        c.add(sp_area);
        for(int i=0;i<m.v.size();i++)
        {
        	if(!m.v.elementAt(i).equals(""))
        	{
        	
        	System.out.println ("Adding");
        	area.setText(area.getText()+(String)m.v.elementAt(i)+"\n");
        	}
        
        }   

        scrollbar_v_1 = new JScrollBar(Scrollbar.VERTICAL);
        c.add(scrollbar_v_1);

        setSize(getPreferredSize());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            
            }
        });
    }//end of show conatct cls constructor
    public void f1()
    {
    	this.setVisible(false);
    }

    
}//end of show contact

class showContactLayout implements LayoutManager {

    public showContactLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 250 + insets.left + insets.right;
        dim.height = 481 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+16,insets.top+8,248,160);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+16,insets.top+184,216,280);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+208,insets.top+184,24,280);}
    }
}
