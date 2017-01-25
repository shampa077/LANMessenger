import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;


public class Font1 extends JFrame implements ActionListener
{
    JComboBox combo1;
    JLabel font;
    JLabel size;
    JComboBox combo2;
    JButton ok;
    JButton cancel;
    public String font_name;
    public int font_size;
    public Chat cg;//reference of chat
     

    public Font1(Chat cgu) {
    	this.cg=cgu;
        Font1Layout customLayout = new Font1Layout();
        Container c =this.getContentPane();
        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        c.setLayout(customLayout);
        combo1 = new JComboBox();
        combo1.addItem("Arial");
        combo1.addItem("Century Gothic");
        combo1.addItem("Courier");
        combo1.addItem("Fixedsys");
        combo1.addItem("Impact");
        combo1.addItem("Tahoma");
        combo1.addItem("Times New Roman");
        combo1.addItem("Tahoma");
        c.add(combo1);

        font = new JLabel("Font");
        c.add(font);

        size = new JLabel("Size");
        c.add(size);

        combo2 = new JComboBox();
        combo2.addItem("10");
        combo2.addItem("12");
        combo2.addItem("14");
        combo2.addItem("16");
        combo2.addItem("18");
        combo2.addItem("20");
        combo2.addItem("22");
        combo2.addItem("24");
        combo2.addItem("26");
        combo2.addItem("28");
        combo2.addItem("30");
        combo2.addItem("32");
        c.add(combo2);

        ok = new JButton(" Ok ");
        c.add(ok);

        cancel = new JButton(" Cancel");
        c.add(cancel);

        setSize(getPreferredSize());
        ok.addActionListener(this);
        cancel.addActionListener(this);
        this.setTitle("Font");
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        //this.cg.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            		setVisible(false);
               		
            }
        });
        
        
     }//end of font cls constructor
     
     public void showWindow()
     {
     	
     }
     
     public void hideWindow()
     {
     	this.setVisible(false);
     } 
     
     public void actionPerformed(ActionEvent e)
     {
        if(e.getSource()==this.ok)
        {
        	this.font_name=combo1.getSelectedItem().toString();
        	this.font_size=Integer.parseInt(combo2.getSelectedItem().toString());
        	int style=this.cg.f.getStyle();
        	Font font=new Font(font_name,style,font_size);
        	this.cg.f=font;
        	this.cg.textIn.setFont(this.cg.f);
        	this.cg.textOut.setFont(this.cg.f);
        	this.setVisible(false);
        	//this.cg.setEnabled(true);
        }
        if(e.getSource()==this.cancel)
        {
        	this.setVisible(false);
        	
        }	
     }//end of actionperformed
}//end of font cls

class Font1Layout implements LayoutManager {

    public Font1Layout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 337 + insets.left + insets.right;
        dim.height = 135 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+48,168,24);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+8,insets.top+16,72,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+232,insets.top+16,72,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+232,insets.top+48,72,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+88,96,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+192,insets.top+88,88,24);}
    }
}
