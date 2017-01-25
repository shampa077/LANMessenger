
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTextArea;


public class Chat extends JFrame implements ActionListener {
    public JTextArea textOut;
    JScrollPane sp_textOut;
    public JTextArea textIn;
    JScrollPane sp_textIn;
    JButton buttonFont;
    JButton buttonCol;
    JButton sendButton;
    JButton buttonBold;
    JButton buttonItalic;
    JButton buttonPlain;
    Font f;
    messenger1 met;//reference of messenger
    NetworkConnect nc;//reference of networkconnect  
   	Font1 fg;//reference of font
    
    public Chat(messenger1 me) {
    	this.met=me;
    	this.nc=me.lg.nc;
        ChatLayout customLayout = new ChatLayout();
       
        Container c=this.getContentPane();
	
        c.setFont(new Font("Helvetica", Font.PLAIN, 12));
        f=new Font("FixedSys",0,16);
        c.setLayout(customLayout);
		
	
        textOut = new JTextArea("");
        textOut.setForeground(Color.blue);
        
        sp_textOut = new JScrollPane(textOut);
        textOut.setEditable(false);
        
        c.add(sp_textOut);

        textIn = new JTextArea("");
        textIn.setForeground(Color.blue);
        sp_textIn = new JScrollPane(textIn);
        c.add(sp_textIn);

        buttonFont = new JButton("Font");
        buttonFont.addActionListener(this);
        c.add(buttonFont);

        buttonCol = new JButton("Color");
        buttonCol.addActionListener(this);
        c.add(buttonCol);

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        c.add(sendButton);

        buttonBold = new JButton("B");
        buttonBold.addActionListener(this);
        c.add(buttonBold);

        buttonItalic = new JButton("I");
        buttonItalic.addActionListener(this);
        c.add(buttonItalic);

        buttonPlain = new JButton("P");
        buttonPlain.addActionListener(this);
        c.add(buttonPlain);

        setSize(getPreferredSize());
		this.setResizable(false);
    	
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	setVisible(false);           		
            	met.setVisible(true);
            	
            	
               
            }
        });
        
        this.fg=new Font1(this);
    	this.fg.setVisible(false);
        
    }//end of chat cls constructor  
    
    
   
	
	
	 public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() ==  this. buttonFont)
		{
			
			
    		this.fg.setVisible(true);
    	
			
		}
		else if(ae.getSource() ==  this. buttonCol)
		{
		
			JColorChooser jc=new  JColorChooser();
    		Color c=jc.showDialog(this,"Choose the Color",Color.BLACK);
    		textIn.setForeground(c);
    		textOut.setForeground(c);
    		
		}
		else if(ae.getSource() ==  this.sendButton)
		{
			
			//textOut.setText(textOut.getText() +"\n");
			ChatInfo c=new ChatInfo();			
			c.setTo(this.met.selectedContact);
			System.out.println("to"+c.getTo());
			c.setFrom(met.lg.name1);
			System.out.println("from"+c.getFrom());
			c.setMessage(textIn.getText());				
			nc.write(c);
			textIn.setText("");		
		
		}
		
		else if(ae.getSource() ==  this.buttonBold)
		{
			String name=this.f.getFontName();
    		int size=this.f.getSize();
    		int style=Font.BOLD;
    		Font font=new Font(name,style,size);
    		this.f=font;
    		//textIn.setFont(f);
    		//textOut.setFont(f);
    		textIn.setFont(new Font("FixedSys",Font.BOLD,size));
			textOut.setFont(new Font("FixedSys",Font.BOLD,size));
		}
		
		else if(ae.getSource() ==  this.buttonItalic)
		{
			
			String name=this.f.getFontName();
    		int size=this.f.getSize();
    		int style=Font.ITALIC;
    		Font font=new Font(name,style,size);
    		this.f=font;
    		//textIn.setFont(f);
    		//textOut.setFont(f);
    		textIn.setFont(new Font("FixedSys",Font.ITALIC,size));
			textOut.setFont(new Font("FixedSys",Font.ITALIC,size));
		}
		
		else if(ae.getSource() ==  this.buttonPlain)
		{
			String name=this.f.getFontName();
    		int size=this.f.getSize();
    		int style=Font.PLAIN;
    		Font font=new Font(name,style,size);
    		this.f=font;
    		//textIn.setFont(f);
    		//textOut.setFont(f);
    		textIn.setFont(new Font("FixedSys",Font.PLAIN,size));
			textOut.setFont(new Font("FixedSys",Font.PLAIN,size));
    		
		}
		
		
		
	}//end of action performed

  
	 
}//end of chat cls 


class ChatLayout implements LayoutManager {

    public ChatLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 468 + insets.left + insets.right;
        dim.height = 422 + insets.top + insets.bottom;

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
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+8,424,240);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+320,304,88);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+24,insets.top+264,104,32);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+144,insets.top+264,104,32);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+352,insets.top+320,96,88);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+264,insets.top+264,56,32);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+328,insets.top+264,56,32);}
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+392,insets.top+264,48,32);}
    }
}
