import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyNotePadTest extends Frame implements ActionListener
{
	MenuBar mb=new MenuBar();
	Menu file=new Menu("File");
	Menu edit=new Menu("Edit");
	Menu help=new Menu("About");
	MenuItem newFile=new MenuItem("New",new MenuShortcut(KeyEvent.VK_S,false));
	MenuItem open=new MenuItem("Open",new MenuShortcut(KeyEvent.VK_O,false));
	MenuItem save=new MenuItem("Save",new MenuShortcut(KeyEvent.VK_S,false));
	MenuItem exit=new MenuItem("Exit",new MenuShortcut(KeyEvent.VK_E,true));
	MenuItem font=new MenuItem("Font",new MenuShortcut(KeyEvent.VK_F,false));
	MenuItem info=new MenuItem("Information",new MenuShortcut(KeyEvent.VK_I,false));
	TextArea ta=new TextArea();
	Label l=new Label("Software develop by Shubham Atul Sontakke",Label.RIGHT);

	MyNotePadTest()
	{
		setBounds(100,200,1000,1000);
		setTitle("Notepad");
		mb.add(file);
		mb.add(edit);
		mb.add(help);
		file.add(newFile);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		edit.add(font);
		help.add(info);

		setMenuBar(mb);
		add(ta);
		add(l,"South");
		ta.setFont(new Font("Arial",Font.PLAIN,20));
		l.setFont(new Font("Arial",Font.BOLD,15));

		newFile.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);
		font.addActionListener(this);
		info.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();

		if(obj==newFile)
		{
			ta.setText(" ");
			setTitle("Notepad");
		}
		else
		 if(obj==open)
		 {
			 FileDialog fd=new FileDialog(this,"Open File");
			 fd.setVisible(true);
			 String filename=fd.getDirectory()+fd.getFile();
			 setTitle("Notepad"+filename);
			 BufferedReader bin=null;
			 try
			 {
				 bin=new BufferedReader(new FileReader(filename));
				 String b=bin.readLine();
				 while(b!=null)
				 {
					 ta.append(b+"\n");
					 b=bin.readLine();
				 }
				 bin.close();
			 }
			 catch(Exception e1)
			 {
				 System.out.println(e1);
			 }
		 }
		else
		 if(obj==save)
		 {
			 FileDialog fds=new FileDialog(this,"Save",FileDialog.SAVE);
			 fds.setVisible(true);
			 String filename=fds.getDirectory()+fds.getFile();
			 setTitle("Notepad"+filename);
			 try
			 {
				 PrintWriter dout=new PrintWriter(new FileWriter(filename));
				 String text=ta.getText();
				 dout.print(text);
				 dout.close();
			 }
			 catch(Exception e3)
			 {
				 System.out.println(e3);
			 }
		 }
	   else
	     if(obj==exit)
	      System.exit(0);
	   else
	     if(obj==font)
	     {
			 FontDialog fd=new FontDialog(this);
			 fd.setVisible(true);
		 }
	   else
	     if(obj==info)
	     {
			 InfoDialog id=new InfoDialog(this);
			 id.setVisible(true);
		 }
	 }

 class FontDialog extends Dialog
 {
 	Label l=new Label("Font:");
 	Label font=new Label("Font Style:");
 	Label sizes=new Label("Size:");
    List l1=new List(10);
    List l2=new List(3);
    List l3=new List(5);
 	Button b=new Button("OK");
 	Button b2=new Button("CANCEL");


 	FontDialog(Frame t)
 	{
 		super(t,"Font");
 		setBounds(100,200,700,600);
 		setLayout(new FlowLayout());
 		String fonts[];
		GraphicsEnvironment GE;
		GE=GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts=GE.getAvailableFontFamilyNames();

 		for(int i=0;i<fonts.length;i++)
 		{
			l1.add(fonts[i]);
		}

		l2.add("BOLD");
		l2.add("ITALIC");
		l2.add("PLAIN");
		l2.add("BOLD+ITALIC");

		String []size={"8","9","10","12","15","20","25","30","35","40","45","50","55","60","65","70","75","80","90","95","100"};
		for(int j=0;j<size.length;j++)
		{
			l3.add(size[j]);
		}

		add(l);
		add(l1);
		add(font);
		add(l2);
		add(sizes);
		add(l3);
		add(new Label("   "));
     	add(b);
		add(b2);
		setResizable(false);

 		b.addActionListener(new ActionListener()
 		                    {
 								public void actionPerformed(ActionEvent e)
 								{
 								    int size=Integer.parseInt(l3.getSelectedItem());
 									String font=l1.getSelectedItem();
 									if(l2.getSelectedItem().equals("BOLD"))
 									{
 										ta.setFont(new Font(font,Font.BOLD,size));
 										setVisible(false);

 									}
 									else
 									 if(l2.getSelectedItem().equals("ITALIC"))
 									 {
 										 ta.setFont(new Font(font,Font.ITALIC,size));
 										 setVisible(false);
 									 }
 									else
 									  if(l2.getSelectedItem().equals("BOLD+ITALIC"))
 									  {
										  ta.setFont(new Font(font,Font.BOLD|Font.ITALIC,size));
										  setVisible(false);
									  }
									else
									 	if(l2.getSelectedItem().equals("PLAIN"))
									 	{
									 	  ta.setFont(new Font(font,Font.PLAIN,size));
									 	  setVisible(false);
 									  }
 								 }
 							 });

 	b2.addActionListener(new ActionListener()
 	                     {
							 public void actionPerformed(ActionEvent a)
							 {
								 setVisible(false);
							 }
						 });

 	addWindowListener(new WindowAdapter()
 	                  {
 						  public void windowClosing(WindowEvent e)
 						  {
 							  setVisible(false);
 						  }
 					  });
 	}
 }

	 public static void main(String[] args)
	 {
		 MyNotePadTest ntp=new MyNotePadTest();
		 ntp.addWindowListener(new WindowAdapter()
		                       {
								   public void windowClosing(WindowEvent e)
								   {
									   System.exit(0);
								   }
							   });

		 ntp.setVisible(true);
	 }
 }

class InfoDialog extends Dialog
{
	Label l=new Label("Textpad Software develop by Shubham Atul Sontakke",Label.CENTER);
	Button b=new Button("OK");

	InfoDialog(Frame t)
	{
		super(t,"Information",true);
		setBounds(100,200,400,400);
		add(l);
		add(b,"South");
		l.setFont(new Font("Arial",Font.BOLD,15));

		b.addActionListener(new ActionListener()
		                    {
								public void actionPerformed(ActionEvent e)
								{
									setVisible(false);
								}
							});
		 addWindowListener(new WindowAdapter()
		                   {
							   public void windowClosing(WindowEvent e)
							   {
								   setVisible(false);
							   }
						   });
	}
}