/*
Restaurant Management System
UserId: admin
password: myself	
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.*;
/* to create table in sql
	user the file Table.lst which can be found in the same directory as the Restaurant.java file

	@D:\RMS\ResTable.lst;
	path may change according to 
	availability of Table.lst file in your computer*/


public class Restaurant
{
	static public String active="Restaurant"; //it contains the name of last active frame for getting back to it
	
	public static void main(String args[])
	{
		//Updatequant uq=new Updatequant();
		//uq.trunc();
		
		WelcomePage w=new WelcomePage();
		w.showFrame();
		
	}
}

//************** Misc Classes **************************


// class connect is used to connect java
// with oracle by giving driver path and oracle
// id and password according to currend userid
// and password change the userid and password 
// and also path if required


class connect
{
	static Connection con;	//used form storing connection path in a variable

	/*	aconnect method is used to provide path to
	// connect java with oracle
	// this method returns Connetion and 
	// this not accept any value*/
	public Connection aconnect()	
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","*****");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
}


//Validity class contains all the methods related to varifying the different fields
class Validity
{
	//all the methods below validiates an Value as per the method's name
	//it returns false if it is not valid else returns true
	
	String myString;
	
	public boolean name(String s)				//NAME
	{
		/*
		length should be more than 2
		and it should not contain any symbol other tha A-Z
		*/
		
		boolean valid=true;
		
		s=s.trim();
		s=s.toUpperCase();
		
		if(s.length()<2 || s.length()>15){
			valid=false;
			System.out.print("Name length is out of range");
			}
		
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)>'Z' || s.charAt(i)<'A')
			{
				valid=false;
				System.out.print("Name has invalid symbol");
				break;
			}
		}
		if(valid==true)
			myString=s;
		
		return valid;
	}
	
		//return the varified string
	public String setVarifiedString()
	{
		return myString;
	}
	
	//checks if there is any empty field or not
	public boolean noEmptyFields(String s1,String s2,String s3,String s4,String s5,String s6,String s7)
	{
		boolean valid=true;
		
		s1=s1.trim();
		s2=s2.trim();
		s3=s3.trim();
		s4=s4.trim();
		s5=s5.trim();
		s6=s6.trim();
		s7=s7.trim();
		
		if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals(""))
		{
			valid=false;
		}
		
		return valid;
	}
	
}

//********************** THE ABOUT PAGE****************** 
class AboutPage extends JFrame implements ActionListener
{
	
	static JButton ok;
	public void showFrame()
	{
		
		Container c=this.getContentPane();
		
		//label
		JLabel title=new JLabel("Image");
		title.setIcon(new ImageIcon("images\\about.jpg"));
		
		//text area
		JTextArea about=new JTextArea(200,150);
		about.setEditable(false);
		about.setText("Welcome to Restaurant Management System \n\nDeveloped by: Tumpa Sheet\nCollege: Pondicherry University, Computer Science Department (Session 2018-21)\n\nObjectivity: The Project is Aimed at providing ease in maintaing and handling the Restaurant\n Billing.Along with it, The application provides facility to generate daily transaction");
		about.setFont(new Font("Times New Roman",Font.ITALIC,15));
		about.setBackground(Color.BLACK);
		about.setForeground(Color.WHITE);
		
		
		
		ok=new JButton("OK");
		
		JPanel p=new JPanel();
		p.add(ok);
		
		
		c.add(title,BorderLayout.NORTH);
		c.add(about,BorderLayout.CENTER);
		c.add(p,BorderLayout.SOUTH);
		
		this.setTitle("About");
		this.setVisible(true);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ok.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			dispose();
		}	
	}
}

//**************** THE WELCOME PAGE **********************
class WelcomePage extends JFrame implements ActionListener
{
	static JButton start, exit;
	public void showFrame()
	{
		
		this.setLayout(null);
		Container c=this.getContentPane();
		
		JLabel lbl=new JLabel();
		
		start = new JButton("Log In");
		exit = new JButton("Exit");
		
		//*************** Adding Image to the Component***********************
		lbl.setIcon(new ImageIcon("images\\welcome.jpg"));
		
		//********** Coloring ,Postioning and Resizing the Components and ***************
		start.setBackground(Color.BLUE);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("Times New Roman",Font.BOLD,18));
		start.setToolTipText("Press the button to open login window");
		
		exit.setBackground(Color.RED);
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Times New Roman",Font.BOLD,15));
		exit.setToolTipText("Press the button to close the project");
		

		exit.setSize(80,40);
		exit.setLocation(50,350);
			
		start.setSize(110,40);
		start.setLocation(640,350);
		
		lbl.setSize(800,400);
		lbl.setLocation(0,0);
		
		//*************** Adding to Container *************************
		c.add(start);
		c.add(exit);
		c.add(lbl);
		
		//**************** Frame Properties ***********************
		this.setTitle("Welcome");
		this.setVisible(true);
		this.setSize(800,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start.addActionListener(this);
		exit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==start)
		{
				LoginPage h=new LoginPage();
				dispose();
				h.showFrame();
		} 
		else if(e.getSource()==exit)
		{
			System.exit(0);
		}
	}
}



//ValidateHome class is used to validate
//values while inserting detils of patient like name ,age etc
	
class ValidateHome 
{
	//valid method is used to validate name and age
	//it returns boolean type variable
	//it accepts two string type variable
	public boolean valid(String name,String age)
	{
		boolean va=true;
		name=name.trim();
		name=name.toUpperCase();
		if(name.length()<3 || name.length()>30)		//set va=false if length of name is less than 3
			va=false;
			
		//this for loop is used to check that 
		// if name variable is containing 
		// only alphabets and space after 
		for(int i=0;i<name.length();i++)
		{
			if(name.charAt(i)>'Z' || name.charAt(i)<'A' )
			{	
				//na variable is used to get ascci value
				//of characted by character to check space
				int na=(int)name.charAt(i);
				if(na==32)			//32 is ascii value of space
					va=true;
				else
				{
					va=false;
					break;
				}
			}
			
		}
		//this for loop is used to check age value
		//should only contains numbers
		if(age.length()>4)
			va=false;
		for(int i=0;i<age.length();i++)
		{
			if(age.charAt(i)<'0'|| age.charAt(i)>'9')
			{
				va =false;
				break;
			}
		}
		return va;
	}

}

//class to insert report record in table

class InsertTableHome 
{
	/*Home funtion is used to accept value from text box
	of generate report window
	here Sring s1 is used to accept value of name
	int n is used to accept value of age
	String s2 is used to accept value of sex choice
	String s3 is used to accept value of type that is problem
	*/
	
	public boolean Home(String s1,int n,String s2,String s3)//default constructor to insert record in table
	{
				
		try
		{	
			//connect class is used to
			//connect java with oracle
			connect cn=new connect();
			
			//acconect method is defined in connect class
			//that contains driver path and other necessary details
			Connection con =cn.aconnect();
			PreparedStatement stmt=con.prepareStatement("Insert into ReportTable values(?,?,?,?)");
			//prepared statement used to execute query to insert record in reporttable
			
			stmt.setString(1,s1);		
			//used to set value of name in table
			
			stmt.setInt(2,n);
			//used to set value of age in table
			
			stmt.setString(3,s2);
			//used to set value of sex in table
			
			stmt.setString(4,s3);
			//used to set value of problem in table
			
			int rs=stmt.executeUpdate();
			//rs variable is used to check whether record is successfully inserted or not
			if(rs==1)
				return true;
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
}


//***************** LOGIN PAGE **************************
class LoginPage extends JFrame implements ActionListener
{
	static JButton  login;
	static JTextField user;
	static JPasswordField pass;
	static JRadioButton rb1,rb2;  // to select the rights you hava with the account
	static ButtonGroup g;
	public void showFrame()
	{
		Restaurant.active="LoginPage";
		
		Container c=this.getContentPane();
		
		//******** Creating the button and adding to pannel ****************
		login=new JButton("Log In");
		
		
			//******* Creating other Components ************
		JLabel lbl1=new JLabel("Log in as:");
		JLabel lbl2=new JLabel("Username");
		JLabel lbl3=new JLabel("Password");
		
		g=new ButtonGroup();
		rb1=new JRadioButton("Billing Only",true);
		rb2=new JRadioButton("Restaurant Admin",false);
		g.add(rb1);
		g.add(rb2);
		
		user=new JTextField(20);
		pass=new JPasswordField(20);
		
			//************ Adding these components Panel by Panel*******
			
			JPanel selectPan=new JPanel();  //Panel to hold the selection radio box
			selectPan.add(lbl1);
			selectPan.add(rb1);
			selectPan.add(rb2);
			
			JPanel userPan=new JPanel();	//Panel to hold user name contents	
			userPan.add(lbl2);
			userPan.add(user);
			
			JPanel passPan=new JPanel();	//Panel to hold password contents	
			passPan.add(lbl3);
			passPan.add(pass);
			
			
			JPanel p2=new JPanel();			//Panel p2 holds all the above panels
			p2.setLayout(new GridLayout(3,1));
			p2.add(selectPan);
			p2.add(userPan);
			p2.add(passPan);
			
			
				//**** Panel p holds the login buttons ***************
				JPanel p1=new JPanel();
				p1.add(login);

			
		//********************** Formating with the Components ************************
		
				
		//************* Adding the panel p1 and p2 to the LOGIN FRAME ********************
		c.add(p2,BorderLayout.CENTER);
		c.add(p1,BorderLayout.SOUTH);			//Button panel added to South

		//*************** Setting Frame properties *********************
		
		this.setTitle("Log in");
		this.setVisible(true);
		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==login)
		{
			String usr=user.getText();
			String psw=pass.getText();
			
			if(usr.equals("admin")&&psw.equals("myself"))
			{
				if(rb1.isSelected())
				{
					BillingPage p=new BillingPage();
					p.showFrame();
					dispose();
				}
				else if(rb2.isSelected())
				{
						new MainMenu();
						dispose();
				}
			}	
	
			else 
					{
						JOptionPane.showMessageDialog(this,"You don't have the rights to login as Restaurnat Admin!");
					}	
				
			}
			else
			{
				user.setText("");
				pass.setText("");
				JOptionPane.showMessageDialog(this,"Invalid username or password!");
			}
		

	}
}


//**************** BILLING PAGE **************************
class BillingPage extends JFrame implements ActionListener,FocusListener
{
	static JButton viewC,addToC,back;
	static JTextField pCode,pName,inStock,price,netPrice;
	static Choice quan;
	static Double net;	
	int row=0;
	public void showFrame()
	{
		Container c=this.getContentPane();
		
		//creating header Components
		JLabel title=new JLabel("Product Billing");
		title.setFont(new Font("Times New Roman",Font.BOLD,20));
		
		//creating footer Components
		viewC=new JButton("View Cart");
		addToC=new JButton("Add To Cart");
		back=new JButton("BACK");
		
		//Creating Body Components
			//--Labels
			
			JLabel lbl1=new JLabel("Item Number :");
			JLabel lbl2=new JLabel("Item Name :");
			JLabel lbl3=new JLabel("Quantity     :");
			JLabel lbl4=new JLabel("Left in Stock:");
			JLabel lbl5=new JLabel("Price(Rs)    :");
			JLabel lbl6=new JLabel("Net Price(Rs):");
			
			//--TextFields
			
			pCode=new JTextField(20);
			pName=new JTextField(20);		
			
			inStock=new JTextField(6);		
			price=new JTextField(10);		
			netPrice=new JTextField(10);	
			
			//quantity choice box
			quan=new Choice();
			quan.add("0");
			quan.add("1");
			quan.add("2");
			quan.add("3");
			quan.add("4");
			quan.add("5");
			
			pName.setEditable(false);
			inStock.setEditable(false);
			price.setEditable(false);
			netPrice.setEditable(false);
			
		// Positioning the Components
		
			//--------Title---------------
			title.setLocation(10,10);
			title.setSize(150,35);
			//---------Buttons------------
			viewC.setLocation(50,470);
			viewC.setSize(100,33);
			//----------------------
			addToC.setLocation(50+125,470);
			addToC.setSize(100,33);
			//--------------------------
			back.setLocation(550,470);
			back.setSize(100,33);
			//-------Labels and TextBox---------------
					//size label
					lbl1.setSize(100,30);
					lbl2.setSize(100,30);
					lbl3.setSize(100,30);
					lbl4.setSize(100,30);
					lbl5.setSize(100,30);
					lbl6.setSize(100,30);
				//location label
				lbl1.setLocation(50,100);
				lbl2.setLocation(50,150);
				lbl3.setLocation(50,200);
				lbl4.setLocation(50,250);
				lbl5.setLocation(50,300);
				lbl6.setLocation(350,350);
					//size textbox
					pCode.setSize(400,28);
					pName.setSize(400,28);
					quan.setSize(100,28);
					inStock.setSize(100,28);
					price.setSize(100,28);
					netPrice.setSize(100,28);
				//location textbox
				pCode.setLocation(150,100);
				pName.setLocation(150,150);
				quan.setLocation(150,200);
				inStock.setLocation(150,250);
				price.setLocation(150,300);
				netPrice.setLocation(440,350); 
					netPrice.setForeground(Color.RED);
			
			
		//Adding Components to the Container c
		this.setLayout(null);
		c.add(back);
		c.add(viewC);
		c.add(addToC);
		c.add(title);
		
		c.add(lbl1);
		c.add(pCode);
		c.add(lbl2);
		c.add(pName);
		c.add(lbl3);
		c.add(quan);
		c.add(lbl4);
		c.add(inStock);
		c.add(lbl5);
		c.add(price);
		c.add(lbl6);
		c.add(netPrice);
		
		//Setting Frame Properties
		
		this.setTitle("Billing Page");
		this.setSize(700,550);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adding Listeners to the buttons and other components

		viewC.addActionListener(this);
		addToC.addActionListener(this);
		back.addActionListener(this);
		
		pCode.addFocusListener(this);
		quan.addFocusListener(this);
	}
	/*function to set value of Reciepe Name and other details from table
		it accepts 3 values
		and does not return any value*/
		
	public void get(String name,int quantity,Double rate)
	{
		pName.setText(name);
		if(quantity<1)
			quantity=0;
		inStock.setText(String.valueOf(quantity));
		price.setText(String.valueOf(rate));
		net=rate;	//value of rate is stored in net double type variable
	}
	
	public void focusGained(FocusEvent e){}
	public void focusLost(FocusEvent e)
	{	
		//if focus is lost from quantity choice
		// then control comes here
		
		if(e.getComponent()==quan)
		{
			//if value of price is null then
			// netprice is automaticall set to 0
			if(price.getText().equals(""))
				netPrice.setText("0");
			else
			{
				int q=Integer.parseInt(quan.getSelectedItem());
				if(!(inStock.getText().equals("")))
				{
					int is=Integer.parseInt(inStock.getText());
					is=is-q;
					if(is<1)
						is=0;
					inStock.setText(String.valueOf(is));
				}
				/* total double type variable is 
				used to store number of
				 items to be purchased by 
				 the customer	*/
				Double total=Double.parseDouble(quan.getSelectedItem());
				
				/* pric double type variable is
				used to calculate total value
				from rate and number of items
				that is quantity
				double type net variable already contains
				price value */
				
				Double pric=total*net;
				
				//ceil method of math class is used
				//to cut extra numbers after decimal place
				
				pric=Math.ceil(pric);
				
				//pr variable is used to convert double
				//type variable pric into string type
				//as textfield accept string 
				
				String pr=String.valueOf(pric);
				netPrice.setText(pr);
			}
		}
		else if(e.getComponent()==pCode)
		{
			String s=pCode.getText();
			s=s.trim();
			s=s.toUpperCase();
			
			if(s.equals(""))
				JOptionPane.showMessageDialog(this,"Invalid Item Number");
			else
			{
				pCode.setText(s);
				
				SearchStock ss=new SearchStock();
				
				/* searchstock class object ss is
				created then a string type variable
				bill is used that contains "bill" as value
				this particular bill variable
				is used to distinguish easily 
				for searchstock class that it's 
				object is created for billing page or 
				stock handler page code is also
				concated with the variable bill then
				passes to search method*/
				
				String bill="bill";	//to distinguish between billing page search and stockhandler page search
				
				bill=bill+s;
				boolean b=ss.Search(bill);
				
				if(b==false)
				{
					JOptionPane.showMessageDialog(this,"Item Number not found");
					String nam="NULL";
					String stk="NULL";
					String amount="NULL";
					String tot="NULL";
				
					pName.setText(nam);
					inStock.setText(stk);
					price.setText(amount);
					netPrice.setText(tot);
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
			if(e.getSource()==back)
			{
				/* this class
				is used to truncate temporary
				table of insert
				cart so that another bill is easily
				created without any error */
				
				Updatequant uq=new Updatequant();
				uq.trunc();
				
				//LoginPage p=new LoginPage();
				if(Restaurant.active.equals("LoginPage"))
				{
					LoginPage p=new LoginPage();
					p.showFrame();
				}	
				else if(Restaurant.active.equals("MainMenu"))
				{
					new MainMenu();
				}
				
				dispose();
				//p.showFrame();
			}
			else if(e.getSource()==viewC)
			{
				CartPage p=new CartPage();
				
				/* get method of cartpage class
				accepts int type variable row
				that contains how many records
				has been inserted to the InsertItem table*/
				
				p.get(row);
				p.showFrame();
				dispose();
			}
			else if(e.getSource()==addToC)
			{
				String nam=pName.getText();
				
				if(nam.equals("")||nam.equals("NULL"))
				{
					JOptionPane.showMessageDialog(this,"No Product to Add to Cart");
					quan.select(0);
				}
				else
				{
					//----------------- Code for adding to cart table goes here ---------
					String pc=pCode.getText();
					String n=pName.getText();
					String q=quan.getSelectedItem();
					
					//pr variable is used to store actual rate
					String pr=price.getText();
				
				//netprice variable is used to store 
				// total price after multiplication with quantity
					String ne=netPrice.getText();
					
					int qu=Integer.parseInt(q);
					int left=Integer.parseInt(inStock.getText());
					
					/* if quantity contains 0 items then it is
					mandatory for customer to select atleast one value
					and if left in stock is less than required amount
					then also adding to cart is not possible*/
					
					if(q.equals("0"))
						JOptionPane.showMessageDialog(this,"Please select atleast 1 quantity");
					else if(left<qu||left<1)
						JOptionPane.showMessageDialog(this," Stock is insufficient"); 
					else
					{
						InsertItem ic=new InsertItem();
						
						//passing values to insert method of
						//insert cart table that accepts 5 string type
						//variables
						
						ic.insert(pc,n,q,pr,ne);
						
						//after inserting row integer type
						//variable is incremented so that
						//row should be counted 
						
						row++;	
						//------------------------------------------------------------------
						JOptionPane.showMessageDialog(this,"Added to Cart.");
						quan.select(0);
						pCode.setText("");
						pName.setText("");
						price.setText("");
						netPrice.setText("");
						inStock.setText("");
					}
				}
				
			}
	}
}

//************Insert cart class is used to
// store value of cart page that is billing page values
class InsertItem 
{
	/* insert method is used to store five values
	accepted by this particular method to the
	table this method returns boolean type variable*/
	
	public boolean insert(String item_no,String i_name,String quant,String price,String netprice)			//function  to insert record in table
	{
		boolean b=false;
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
			
			Scanner in=new Scanner(System.in);
			PreparedStatement stmt=con.prepareStatement("Insert into InsertItem values(?,?,?,?,?)");
			stmt.setString(1,item_no);
			stmt.setString(2,i_name);
			stmt.setString(3,quant);
			stmt.setString(4,price);
			stmt.setString(5,netprice);
			
			int rs=stmt.executeUpdate();
			if(rs==1)
				b=true;
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
}

//**************** STOCK HANDLER PAGE ********************
class StockHandlerPage extends JFrame implements ActionListener,ItemListener
{
	/*
	NOTE:	 in card2 , del(RadioButton) 
			i.e. 'delete' is used for
			search, delete as well as modificaion.
			while modification is transfered to card1 add(Radio Button)
	*/
	//Declarations for the Entire Frame 
	static	JRadioButton add,del,view;
	static	CardLayout clo;
	static 	JPanel cards; //card1 and card2
	static 	JButton back;
	
	//Declarations for Card1
	static JButton addr;
	static JTextField code1,name1,quan1,rate1,mrp1;
	
	//Declarations for card2
	static 	JTextField searchbox;
	static 	JButton search;
	static 	JTextField i_name,quan,price,amount;
	static 	JButton delete,modify;
	
	static String date;
	 //used for containing date
	
	public void showFrame()
	{
		Container c=this.getContentPane();
		
		//---------header -------------
		JLabel title=new JLabel("Add, Delete or Modify the Records here :"); 	//Title
		
		ButtonGroup g=new ButtonGroup(); 									//RadioButton
		add=new JRadioButton("Add",true);
		del=new JRadioButton("Search/Delete/Modify Records",false);
		view=new JRadioButton("View Stock",false);
		g.add(add);
		g.add(del);
		g.add(view);
		
		JPanel rbutton=new JPanel();
		rbutton.add(add);
		rbutton.add(del);
		rbutton.add(view);
		
		JPanel p=new JPanel();												//Title + RadioButton Panel
			p.add(title);
			p.add(rbutton);
			
			//----- Back BUTTON
		back=new JButton("Back");
		
		JPanel toolbar=new JPanel();
		toolbar.setLayout(new BorderLayout());
		toolbar.add(back,BorderLayout.EAST);
		toolbar.add(p);
			
		//---------Body-----------------
		
		JPanel card1,card2;
		card1=new JPanel();
		card2=new JPanel();
		
		clo=new CardLayout();
		cards=new JPanel();
		cards.setLayout(clo);
		
		cards.add(card1,"ADD");
		cards.add(card2,"DELETE");
		
		card1.setLayout(null);  card1.setBackground(Color.WHITE);
		card2.setLayout(null);	card2.setBackground(Color.WHITE);
		
		//--- Designing THE CARDS------ CARD1 and CARD2 --------------------
		
			//########## CARD 1 ##############
				
				//ADD button and Title
				JLabel c1title=new JLabel("Enter the Details of the Item:");	//title
				c1title.setFont(new Font("Times New Roman",Font.BOLD,18));
				c1title.setSize(320,35);
				c1title.setLocation(350,10+30);
				
				addr=new JButton("ADD RECORD");
				addr.setSize(150,30);
				addr.setLocation(630,310);
				
				//-------------------------------Setting up size of other labels and textFields
				JLabel ll0=new JLabel("Item Number  :");
				JLabel ll1=new JLabel("Reciepe Name  :");
				JLabel ll2=new JLabel("Quantity  :");
				JLabel ll3=new JLabel("Rate  :");
				JLabel ll4=new JLabel("MRP  :");
				
				code1=new JTextField();
				name1=new JTextField();
				quan1=new JTextField();
				rate1=new JTextField();
				mrp1=new JTextField();
			
						//Label size 
						ll0.setSize(100,30);
						ll1.setSize(100,30);
						ll2.setSize(100,30);
						ll3.setSize(100,30);
						ll4.setSize(100,30);

						//TextField size
						code1.setSize(280,28);
						name1.setSize(280,28);	
						quan1.setSize(100,28);	
						rate1.setSize(100,28);	
						mrp1.setSize(100,28);	
						
						
						//Label Location
						ll0.setLocation(50+100,60+50);
						ll1.setLocation(50+100,100+50);
						ll2.setLocation(50+100,140+50);
						ll3.setLocation(50+100,180+50);
						ll4.setLocation(50+100,220+50);
					
						//textField location
						code1.setLocation(200+100,60+50);
						name1.setLocation(200+100,100+50);
						quan1.setLocation(200+100,140+50);
						rate1.setLocation(200+100,180+50);
						mrp1.setLocation(200+100,220+50);
				
				
				
				//---Adding components to card2
				
				card1.add(c1title);
				card1.add(ll0);
				card1.add(ll1);
				card1.add(ll2);
				card1.add(ll3);
				card1.add(ll4);
				card1.add(code1);
				card1.add(name1);
				card1.add(quan1);
				card1.add(rate1);
				card1.add(mrp1);
				card1.add(addr);
				
			
			//########## CARD 2 ##############
				
				JLabel c2title=new JLabel("Enter the Item Number to search for : ");	//title
				c2title.setFont(new Font("Times New Roman",Font.BOLD,18));
				c2title.setSize(320,35);
				c2title.setLocation(350,10+30);
				
				searchbox=new JTextField();										//search box
				searchbox.setSize(350,30);
				searchbox.setLocation(250,50+30);
				
				search=new JButton("SEARCH RECORD");							//search button
				search.setSize(150,30);
				search.setLocation(630,50+30);
				
				//DELETE and MODIFY button
				
				delete=new JButton("DELETE RECORD");
				modify=new JButton("MODIFY RECORD");
				modify.setEnabled(false);
				
				delete.setSize(150,30);
				modify.setSize(150,30);
				
				delete.setLocation(630,240);
				modify.setLocation(630,310);
				
				//-------------------------------Setting up size of other labels and textFields
				JLabel l1=new JLabel("Reciepe Name  :");
				JLabel l2=new JLabel("Quantity  :");
				JLabel l3=new JLabel("Rate  :");
				JLabel l4=new JLabel("MRP  :");
				
				i_name=new JTextField();
				quan=new JTextField();
				price=new JTextField();
				amount=new JTextField();
				
				
						//Label size 
						l1.setSize(100,30);
						l2.setSize(100,30);
						l3.setSize(100,30);
						l4.setSize(100,30);
						//TextField size
						i_name.setSize(300,28);	i_name.setEditable(false);
						quan.setSize(100,28);	quan.setEditable(false);
						price.setSize(100,28);	price.setEditable(false);
						amount.setSize(100,28);	amount.setEditable(false);
											
						
						//Label Location
						l1.setLocation(50+100,100+50);
						l2.setLocation(50+100,140+50);
						l3.setLocation(50+100,180+50);
						l4.setLocation(50+100,220+50);
						//textField location
						i_name.setLocation(200+100,100+50);
						quan.setLocation(200+100,140+50);
						price.setLocation(200+100,180+50);
						amount.setLocation(200+100,220+50);
						
				//---Adding components to card2
				card2.add(c2title);
				card2.add(searchbox);
				card2.add(search);
				card2.add(l1);
				card2.add(l2);
				card2.add(l3);
				card2.add(l4);
				card2.add(i_name);
				card2.add(quan);
				card2.add(price);
				card2.add(amount);
				
				card2.add(delete);
				card2.add(modify);
				
			
		c.add(toolbar,BorderLayout.NORTH);
		c.add(cards,BorderLayout.CENTER);
		
		
		//----------------Setting up frame properties----------------
		setTitle("Test");
		setVisible(true);
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//----------------- Adding Listeners ------------------------
		
		addr.addActionListener(this);
		search.addActionListener(this);
		modify.addActionListener(this);
		delete.addActionListener(this);
		back.addActionListener(this);
		
		add.addItemListener(this);
		view.addItemListener(this);
		del.addItemListener(this);
		
		
	}		
	/*get function is used to get value from
	searchstock table so that it can be
	inserted in the textbox	it accepts 5 variables
	nam is for name,qua for quantity,ra for price
	mr for amount and exp2 for containing expiry variables*/
	
	public void get(String nam,int qua,double ra,double mr)	//funtion to set text value after search
	{
		i_name.setText(nam);
		quan.setText(String.valueOf(qua));
		price.setText(String.valueOf(ra));
		amount.setText(String.valueOf(mr));

	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(add.isSelected())
		{
			clo.show(cards,"ADD");
		}
		else if(del.isSelected())
		{
		 	clo.show(cards,"DELETE");
		}
		else if(view.isSelected())
		{
			/* when view radio button is selected
			then stockjtable class object sj
			is created and table is shown in jtable
			format */
			
			StockJTable sj=new StockJTable();
			sj.showFrame();
			dispose();
		}
		
	}
	
	/* clear function is used to clear 
	all previos values of text box and list box
	to previous stage to perform another operation */
	
	public void clear()
	{
		code1.setText("");
		name1.setText("");
		quan1.setText("");
		rate1.setText("");
		mrp1.setText("");
	}
	
	public void actionPerformed(ActionEvent e)
	{
			if(e.getSource()==back)
			{
				new MainMenu();
				dispose();
			}
			else if(e.getSource()==delete)
			{
				/* Deletestock class object d
				is created then
				code value is passed to delete method
				if it is not equal to null */
				
				DeleteStock d=new DeleteStock();
				String c=searchbox.getText();
				
				if(c.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Item Number invalid");
					searchbox.setText("");
				}
				else
				{
					boolean b=d.Delete(c);
					if(b==true)
						JOptionPane.showMessageDialog(this,"Successfully deleted");
					else if(b==false)
						JOptionPane.showMessageDialog(this,"Item Number not available");
					i_name.setText("");
					quan.setText("");
					price.setText("");
					amount.setText("");
					
					searchbox.setText("");
				}
			}
			else if(e.getSource()==addr)
			{
				/* first code value is given
				to variable c then check that
				if length is less than 3 or does
				not containing null values */
				
				String c=code1.getText();
				
				
				if(c.length()<3 || c.equals("") ||c.length()>13)
				{
					JOptionPane.showMessageDialog(this,"Invalid Item Number");
					//clear();
				}
				else
				{
					/* checkcode class object ch is created
					then method check is called and passed
					code that whether code already exists in table
					or not  boolean type variable cod get value
					from check method if it returns false
					then code doesnot exists in table and new record
					is to be inserted otherwise update the 
					values in the existing record of table*/

					CheckCode ch=new CheckCode();
					boolean cod=ch.Check(c);
					
					if(cod==false)
					{
						/* after that valid method of validatestock
						class is called and check that whether
						all value of inserted field is valid or not
						it passes 4 values of textbox	valid 
						method returns boolean type variable
						if it returns true then value is inserted
						in the table else error message in the
						form of message dialog box is generated	*/
						
						ValidateStock vs=new ValidateStock();
						boolean v=vs.valid(name1.getText(),quan1.getText(),rate1.getText(),mrp1.getText());
						if(v==true)
						{
							String n=name1.getText();
							int q=Integer.parseInt(quan1.getText());
							double r=Double.parseDouble(rate1.getText());
							double m=Double.parseDouble(mrp1.getText());
							
							String ex;//used to concate date value
							
							
							
							
							InsertStock Is=new InsertStock();//creating object of class that insert stock
							//passing value to insert table function
							
							c=c.trim();
							c=c.toUpperCase();
							boolean bo=Is.Stock(c,n,q,r,m);
							
							/* after inserting in table by passing all
							values in stock method of insertstock
							class if it returns true then successfully inserted
							message is shown otherwise error message is displayed*/
							
							if(bo==true)
								JOptionPane.showMessageDialog(this," Successfully Added/Modified");
							else	
								JOptionPane.showMessageDialog(this,"Error while inserting");
						
							//after adding all textfields become empty
							clear();
						}
						else
						{	
							JOptionPane.showMessageDialog(this,"Invalid Data Provided");
							//clear();
						}
					}
					else
					{

						/*modify coding
						if code already exists in the table
						then automatically control comes to else
						part to update table record
						once again valid method of validatestock
						class is called and
						all values is passed to validate all
						fields it also returns boolean type variable
						it returns true if all are valid otherwise
						returns false in case of true operation of
						next step is to be performed otherwise generates
						error message */
						
						ValidateStock vst=new ValidateStock();
						boolean v=vst.valid(name1.getText(),quan1.getText(),rate1.getText(),mrp1.getText());
						if(v==true)
						{
							UpdateStock cs=new UpdateStock();
							String ca=code1.getText();
							String n=name1.getText();
							String q=quan1.getText();
							String r=rate1.getText();
							String m=mrp1.getText();
							
							
							/* after storing all values in string type
							variable it is passed to upstock method
							of updatestock table 	*/
							
							boolean up=cs.upstock(ca,n,q,r,m);
							if(up==true)
								JOptionPane.showMessageDialog(this,"Record Successfully updated");
							else
								JOptionPane.showMessageDialog(this,"Error while updating");
							clear();
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid data feeded while modifying");
							clear();
						}
					}
				}	
			}
			else if(e.getSource()==search)
			{	
				/* after clicking on search  modify
				button is enabled so that  modify
				operatio can be performed only after searching
				operation*/
				modify.setEnabled(true);
				SearchStock se=new SearchStock();
				if(searchbox.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this,"Invalid Item Number!");
					searchbox.setText(" ");
				}
				else
				{
					String c=searchbox.getText();
					c=c.trim();
					c=c.toUpperCase();
					if(se.Search(c)==false)
						JOptionPane.showMessageDialog(this,"code not available");
					else
						JOptionPane.showMessageDialog(this,"Successfully find");
				}
				
			}
			else if(e.getSource()==modify)
			{
				code1.setText(searchbox.getText());
				name1.setText(i_name.getText());
				quan1.setText(quan.getText()); 
				rate1.setText(price.getText());
				mrp1.setText(amount.getText());
				String c=searchbox.getText();
				
				SearchStock ss=new SearchStock();
				boolean bl=ss.Search(c);
			
				/*  c variable contains
				code value is to be sarched
				if it contains null value then
				searching operation is not performed
				if search method of serchstock class
				returns false then  record is not
				found error message is generated 
				otherwise success message is generated*/
				
				if(c.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Item Number invalid");
					searchbox.setText("");
				}
				else if(bl==false)
				{
					JOptionPane.showMessageDialog(this,"Item Number not available");
					searchbox.setText("");
				}
					
				else
					add.setSelected(true);
				/* to modify control is transfered to 
				add record card after clicking on add button
				modify operation is performed then modify button
				is once again disabled and enabled again only 
				when searching operation is to be performed */
				
				modify.setEnabled(false);
			}	
	}
}

//*************** Stock PAGE ******************************
/*this class is used to show values of stock table
when ever user clicks on view stock radio button in
stockhandler page */

class StockJTable extends JFrame implements ActionListener
{	
	static JButton back;
	static JTable table;
	int row=0;
	static String s[][];
	public void showFrame()
	{
		Container c=this.getContentPane();
		
		//creating header Components
		
		back=new JButton("BACK");
		
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(back,BorderLayout.WEST);
		//Creating Body Components
		
		String head[]={"Code","Name","Quantity","Rate","Mrp"};
	
		/* tablestock class object ts is created
		to use method to display record in stock
		getrow method returns number of records
		in the form of integer so that
		jtable row can be adjusted in that format */
		
		Tablestock ts=new Tablestock();
		row=ts.getrow();
		
		/* string s double dimension array object
		is created to 	pass and accept values from
		views method of table stock class */
		s=new String[row][6];
			if(row!=0)
				s=ts.views(s);
			else
				JOptionPane.showMessageDialog(this,"No Product in stock");
		String body[][]=s;
		table=new JTable(body,head);
		this.add(new JScrollPane(table));
			
		//Adding Components to the Container c
		c.add(p1,BorderLayout.NORTH);
		//Setting Frame Properties
		
		this.setTitle("StockJTable");
		this.setSize(700,550);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adding Listeners to the buttons and other components

		back.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==back)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			dispose();
		}
	}
}


/* tablestock class is used to extract number of
records in table and return record in the form
of 2d array */

class Tablestock 
{ 

	/* getrow function is used to count number of
	rows in existing table so that array arr is 
	created according to the row
	this function does not accept any value but
	return integer type variable that is number of
	records in the table */
	
	public int getrow()
	{
		int row=0;
		try
		{	
			connect cn=new connect();
			Connection con =cn.aconnect();
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from ItemTable");
			while(rs.next())
				row++;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return row;
	}
	
	/* this function views accepts
	one string type double dimension array
	and also returns a double dimension
	string type variable */

	public String[][] views(String arr[][])
	{
			int row=getrow();
			arr=new String [row][5];
			try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("Select * from ItemTable");
				int c=0;
				while(rs.next())
				{
					arr[c][0]=rs.getString(1);
					arr[c][1]=rs.getString(2);
					arr[c][2]=rs.getString(3);
					arr[c][3]=rs.getString(4);
					arr[c][4]=rs.getString(5);
					c++;
				}
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			
			return arr;
	}
}

/* updatestock class is used to update
the record of ItemTable
after searching operation and user press modify button
*/

class UpdateStock
{
	/* upstock method is used to update record
	of stock table
	it accepts 6 values that is code,name
	quantity,price,amount */
	
	public boolean upstock(String c,String n,String q,String r,String m)
	{
	
			try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				
				PreparedStatement stmt=con.prepareStatement("update ItemTable set item_no=?,i_name=?,quant=?,price=?,amount=?where item_no=?");
				stmt.setString(1,c);
				stmt.setString(2,n);
				stmt.setString(3,q);
				stmt.setString(4,r);
				stmt.setString(5,m);
				stmt.setString(7,c);
				int rn=stmt.executeUpdate();
				if(rn==1)
					return true;
			}
			catch(Exception exx)
			{
				System.out.println(exx);
			}
			return false;
		}
}

/* validate stock class is used to validate
all fields in stock class while inputing in
textfield after pressing add record button
a valid method is defined in this class that 
accepts 4 values and return 1 boolean type
variable */

class ValidateStock
{
	public boolean valid(String i_name,String quant,String price,String amount)
	{
		boolean b=true;
		i_name=i_name.trim();
		//name=name.toUpperCase();
		
		/* validity of name is based on 
		name must contain alphabets
		its length should be between 3 and 19	*/
		
		if(i_name.length()>40 ||i_name.length()<3 || i_name.equals("") )
		{	
			b=false;
			System.out.println("Symbols allowed in Item Name and length range is 3-40");
		}
		
		/* quantity length must be between 1 and 4 and must contain 
		only numeric value and don't contain null values */
		
		if(quant.length()<1 || quant.equals("") || quant.length()>4)
			b=false;
		else
		{
			for(int i=0;i<quant.length();i++)
			{	
				if(quant.charAt(i)<'0'|| quant.charAt(i)>'9')
				{
					b=false;
					break;
				}
			}
		}	
		
		boolean ch=check(price);
		if(ch==false)
			b=false;
		
		boolean cha=check(amount);
		if (cha==false)
			b=false;
			
		if(b!=false)
		{
			Double r=Double.parseDouble(price);
			Double m=Double.parseDouble(amount);
			if(r>m)
				b=false;
		}
		return b;
	}
	
	public boolean check(String r)
	{
		boolean b=true;
		if(r.length()<1 || r.equals("") || r.length()>7)
			b=false;
		else	
		{
			for(int i=0;i<r.length();i++)
			{
				if(r.charAt(i)<'0' || r.charAt(i)>'9')
				{
					int na=(int)r.charAt(i);
					if(na==46)			//46 is ascii value of dot
						b=true;
					else
					{
						b=false;
						break;
					}
				}	
			}
			
		}
	
		return b;
	}
}

// this class is used to check whether item_no is 
// already existing or not
class CheckCode
{
	public boolean Check(String scode)
	{
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
		
			PreparedStatement stmt=con.prepareStatement("select * from ItemTable where item_no=?");
			stmt.setString(1,scode);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
				return true;
		
		}
		catch(Exception et)
		{
			System.out.println(et);
		}
		return false;
	}
}
//class to delete stock records in the table
class DeleteStock
{
	public  boolean Delete(String item_no)			//function  to insert record in table
	{
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
		
			Scanner in=new Scanner(System.in);
			PreparedStatement stmt=con.prepareStatement("delete from ItemTable where item_no=?");
			String r=item_no;
			stmt.setString(1,r);
			int rs=stmt.executeUpdate();
			if(rs==1)
				return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
}


//class to search stock
class SearchStock
{
	public boolean Search(String item_no)
	{
		String billing=item_no;
		if(item_no.length()>4)
			if(item_no.substring(0,4).equals("bill"))
				item_no=item_no.substring(4,item_no.length());
		String name2="";
		int qu=0;
		double rat=0.0;
		double mrp2=0.0;
		boolean ba=false;
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
			
			PreparedStatement stmt=con.prepareStatement("Select * from ItemTable where item_no=?");
			String r=item_no;
			stmt.setString(1,r);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				 ba=true;
				  
				name2=rs.getString(2);
				qu=rs.getInt(3);
				rat=rs.getDouble(4);
				mrp2=rs.getDouble(5);
			}
			else
				System.out.println("Record is not found");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			if(billing.equals(item_no))
			{
				StockHandlerPage sp=new StockHandlerPage();
				sp.get(name2,qu,rat,mrp2);
			}
			else
			{
				BillingPage bp=new BillingPage();
				bp.get(name2,qu,rat);
			}
			
		return ba;
	}
}

//class to insert stock records in the table
class InsertStock 
{
	public boolean Stock(String c,String n,int q,double r,double m)			//function  to insert record in table
	{
		boolean b=false;
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
			Scanner in=new Scanner(System.in);
			PreparedStatement stmt=con.prepareStatement("Insert into ItemTable values(?,?,?,?,?)");
			stmt.setString(1,c);
			stmt.setString(2,n);
			stmt.setInt(3,q);
			stmt.setDouble(4,r);
			stmt.setDouble(5,m);
			
			int rs=stmt.executeUpdate();
			if(rs==1)
				b=true;
			//con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
}

//********************** MainMenu Page *****************

class MainMenu extends JFrame implements ActionListener
{
	JMenuItem b1,b2,b3,b4,b5,c1,d2,e1;
	
	public MainMenu()
	{
		Restaurant.active="MainMenu";
		//working with menubar
		
		JMenuBar bar=new JMenuBar();
		JMenu m2=new JMenu("Manage");
		JMenu m3=new JMenu("Billing");
		JMenu m4=new JMenu("Records");
		JMenu m5=new JMenu("Help");
		
	
		bar.add(m2);
		bar.add(m3);
		bar.add(m4);
		bar.add(m5);
		
		
		
		 b1=new JMenuItem("Search");
		 b2=new JMenuItem("Add");
		 b3=new JMenuItem("Modify");
		 b4=new JMenuItem("Delete");
		 b5=new JMenuItem("View Records as Table");
		
		 c1=new JMenuItem("Billing Page");
		

		 d2=new JMenuItem("Invoice Report");
		
		 e1=new JMenuItem("About Restaurant Management System");
		
		
		b1.setToolTipText("Search the food by their item number");
		b2.setToolTipText("Add new food item to the List");
		b3.setToolTipText("Update a Item Record");
		b4.setToolTipText("Delete Existing Record from the List");
		b5.setToolTipText("View the Item in stock as TABLE");
		c1.setToolTipText("Jump to Billing Page");
		d2.setToolTipText("View the Sales Report as Table");
		e1.setToolTipText("About Restaurant Management System");
		
	
		m2.add(b1);
		m2.add(b2);
		m2.add(b3);
		m2.add(b4);
		m2.add(b5);
		m3.add(c1);
		m4.add(d2);
		m5.add(e1);
		
		
		// working with rest of the frame
		setLayout(null);
		JLabel l=new JLabel();
		l.setIcon(new ImageIcon("images\\menu.jpg"));
		l.setSize(800,500);
		l.setLocation(0,-15);
		
		//----------------- adding components to frame--------
		
		add(l);
		
		//----------------Setting up frame properties----------------
		
		this.setJMenuBar(bar);
		this.setTitle("Main Menu");
		this.setVisible(true);
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//-------------- adding listener -------------------------
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		c1.addActionListener(this);
		d2.addActionListener(this);
		e1.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 
	 if(e.getSource()==b1)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			p.del.setSelected(true);
			JOptionPane.showMessageDialog(this,"Search the Record to be deleted");
			dispose();
		}
		else if(e.getSource()==b2)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			p.add.setSelected(true);
			dispose();
		}
		else if(e.getSource()==b3)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			p.del.setSelected(true);
			JOptionPane.showMessageDialog(this,"Search the Record to be Modified");
			dispose();
		}
		else if(e.getSource()==b4)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			p.del.setSelected(true);
			JOptionPane.showMessageDialog(this,"Search the Record to be Deleted");
			dispose();
		}
		else if(e.getSource()==b5)
		{
			StockHandlerPage p=new StockHandlerPage();
			p.showFrame();
			p.view.setSelected(true);		
			dispose();
		}
		else if(e.getSource()==c1)
		{
			BillingPage p=new BillingPage();
			p.showFrame();
			dispose();
		}			
		else if(e.getSource()==d2)
		{
			Report r=new Report();
			r.showFrame();
		}
		else if(e.getSource()==e1)
		{
			AboutPage p=new AboutPage();
			p.showFrame();
		}
		
	}
	
	
}


class ViewReport 
{ 
	public int getrow()
	{
		int row=0;
		try
		{	
			connect cn=new connect();
			Connection con =cn.aconnect();
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from RestaurantReport");
			while(rs.next())
				row++;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return row;
	}
	
	/* this function  */
	
	public String[][] views()
	{
			int row=getrow();
			String arr[][]=new String [row][6];
			try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("Select * from RestaurantReport");
				int c=0;
				while(rs.next())
				{
					arr[c][0]=rs.getString(1);
					arr[c][1]=rs.getString(2);
					arr[c][2]=rs.getString(3);
					arr[c][3]=rs.getString(4);
					arr[c][4]=rs.getString(5);
					arr[c][5]=rs.getString(6);
					c++;
				}
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			
			return arr;
	}
}


class Report extends JFrame implements ActionListener
{	
	static JButton back;
	static JTable table;
	int row=0;
	static String s[][];
	public void showFrame()
	{
		Container c=this.getContentPane();
		
		//creating header Components
		
		back=new JButton("BACK");
		
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(back,BorderLayout.WEST);
		//Creating Body Components
		
		String head[]={"Code","Name","Quantity","MRP","Price","Date","Total"};
	
		/* tablestock class object ts is created
		to use method to display record in stock
		getrow method returns number of records
		in the form of integer so that
		jtable row can be adjusted in that format */
		
		ViewReport ts=new ViewReport();
		row=ts.getrow();
		
		/* string s double dimension array object
		is created to 	pass and accept values from
		views method of ViewReport class */
		s=new String[row][6];
			if(row!=0)
				s=ts.views();
			else
				JOptionPane.showMessageDialog(this,"No purchase is done");
		
		double total=0.0;
		String tab[][]=new String[row][7];
		String date="",da="";
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<6;j++)
				tab[i][j]=s[i][j];
			
			date=s[i][5];
			double t=Double.parseDouble(tab[i][4]);
			total+=t;
					
			if(i!=0)
			{
				if(da.equals(date))
					tab[i][6]="";
				else
				{
					double tr=total-t;
					tab[i-1][6]=String.valueOf(tr);
					total=t;
				}
			}
			da=date;
			if(i==row-1)
				tab[i][6]=String.valueOf(total);
	
		}
		
		String body[][]=tab;
		table=new JTable(body,head);
		this.add(new JScrollPane(table));
			
		//Adding Components to the Container c
		c.add(p1,BorderLayout.NORTH);
		//Setting Frame Properties
		
		this.setTitle("MedicineReport");
		this.setSize(700,550);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adding Listeners to the buttons and other components

		back.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==back)
		{
			//new MainMenu();
			//p.showFrame();
			dispose();
		}
	}
}




//*************** CART PAGE ******************************
class CartPage extends JFrame implements ActionListener
{	
	static JButton back,print;
	static JTextField total;
	static JTable tabl;
	static String item_no,i_name,quant,amount,price;
	String s[][];
	int row=0;
	static Double tot=0.0;
	public void get(int r)
	{
		row=r;
	}
	public void showFrame()
	{
		Container c=this.getContentPane();
		
		//creating header Components
		JLabel title=new JLabel("CART");
		JPanel p0=new JPanel();
		p0.add(title);
		
		title.setFont(new Font("Times New Roman",Font.BOLD,20));
		back=new JButton("BACK");
		
		JPanel p1=new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(back,BorderLayout.WEST);
		p1.add(p0,BorderLayout.CENTER);
		
		//creating footer Components
		
		print=new JButton("Print/Buy");
		JLabel lbl=new JLabel("TOTAL = ");
		total=new JTextField(15);
		total.setEditable(false);
		JPanel p2=new JPanel();
		p2.add(lbl);
		p2.add(total);
		p2.add(print);
		
		//Creating Body Components
		String head[]={"Item Number","Item Name","Quantity","MRP","Price"};
	
		int ro=row-1;
		s=new String[row][5];
		//String arr=new String[row][5];
		viewcart vc=new viewcart();
		if(row>0)
			s=vc.view(s,row);
		else
			JOptionPane.showMessageDialog(this,"No Product in cart");
		
		tot=0.0;
		//System.out.println("value in total"+tot);
		//System.out.println("value of row="+row);
		for(int r=0;r<row;r++)
		{
			String sr=s[r][4];
			if(sr.equals("null"))
				tot=0.0;
			else
			{
				//System.out.println("value"+s[r][4]);
				tot+=Double.parseDouble(sr);
				//System.out.println("Total"+tot);
			}
		}
		total.setText(String.valueOf(tot));
		
		String body[][]=s;
		
		tabl=new JTable(body,head);
		this.add(new JScrollPane(tabl));
			
		//Adding Components to the Container c
		c.add(p1,BorderLayout.NORTH);
		c.add(p2,BorderLayout.SOUTH);
		//Setting Frame Properties
		
		this.setTitle("Cart");
		this.setSize(700,550);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adding Listeners to the buttons and other components

		back.addActionListener(this);
		print.addActionListener(this);
	}
	public String date()
	{
		Calendar c=Calendar.getInstance();
		
		int d=c.get(Calendar.DATE);
		int m=c.get(Calendar.MONTH)+1;
		int y=c.get(Calendar.YEAR);
		
		String da;
		if(d<10)
		{
			String s="0";
			da=String.valueOf(d);
			da=s+da;
		}
		else
			 da=String.valueOf(d);
		
		String mon;
		if(m<10)
		{
			String mo="0";
			mon=String.valueOf(m);
			mon=mo+mon;
		}
		else
			mon=String.valueOf(m);
		String dat=da+"-"+mon+"-"+String.valueOf(y);
		return dat;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==back)
		{
			BillingPage p=new BillingPage();
			p.showFrame();
			dispose();
		}
		else if(e.getSource()==print)
		{

			if(row<1)
				JOptionPane.showMessageDialog(this,"No Product Available");
			else
			{	
				JOptionPane.showMessageDialog(this,"Purchase Successfull.");
				Updatequant uq=new Updatequant();
				uq.upstock();
				uq.trunc();
			
				String report[][]=new String[row][6];
			
				for(int i=0;i<row;i++)
				{
					for(int j=0;j<5;j++)
					{
						report[i][j]=s[i][j];
						//System.out.println(report[i][j]);
					}
					report[i][5]=date();
					//System.out.println(report[i][5]);
				
				}
			
				InsertReport ir=new InsertReport();
				ir.pass(report,row);
			
				dispose();
				new MainMenu();
			}	//p.showFrame();
		}
	}

}

class InsertReport 
{
	
	public void pass(String arr[][],int row)
	{
		for(int i=0;i<row;i++)
		{
			boolean b=Report(arr[i][0],arr[i][1],arr[i][2],arr[i][3],arr[i][4]);
			//System.out.println(b);
		}
	}
	public boolean Report(String item_no,String i_name,String quantity,String amount,String  price)			//function  to insert record in table
	{
		boolean b=false;
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
			Scanner in=new Scanner(System.in);
			PreparedStatement stmt=con.prepareStatement("Insert into RestaurantReport values(?,?,?,?,?)");
			stmt.setString(1,item_no);
			stmt.setString(2,i_name);
			stmt.setString(3,quantity);
			stmt.setString(4,amount);
			stmt.setString(5,price);
			int rs=stmt.executeUpdate();
			if(rs==1)
				b=true;
			//con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
}




class Updatequant
{
	public void upstock()
	{
		String item_no="";
		int quantity=0;
		int quant=0;
	
		try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from InsertItem");
				while(rs.next())
				{
					item_no=rs.getString(1);
					quantity=Integer.parseInt(rs.getString(3));
					
					quant=quantst(item_no);	
				
					update(item_no,quant-quantity);
				}
				
			}
			catch(Exception exx)
			{
				System.out.println(exx);
			}
			
	}
	public void update(String item_no,int left)
	{
		if(left<1)
			left=0;
		item_no=item_no.toUpperCase();
		try
		{
		connect cn=new connect();
		Connection con =cn.aconnect();
		
		PreparedStatement stmt=con.prepareStatement("update ItemTable set quant=? where item_no=?");
		stmt.setInt(1,left);
		stmt.setString(2,item_no);
		int rn=stmt.executeUpdate();
		if(rn==1)
			System.out.println("Successfully updated");
		else
			System.out.println("Error");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void trunc()
	{
		try
		{
			connect cn=new connect();
			Connection con =cn.aconnect();
			Statement stmt=con.createStatement();
			stmt.execute("truncate table InsertItem");
			System.out.println("success");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public int quantst(String item_no)
	{	
		item_no=item_no.toLowerCase();
		int q=0;
			try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				PreparedStatement stmta=con.prepareStatement("Select * from ItemTable where item_no=?");
				stmta.setString(1,item_no);
				ResultSet rsa=stmta.executeQuery();
					
				if(rsa.next())
					q=rsa.getInt(3);
						
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		return q;
	}
	
}
class viewcart 
{ 
	static Double total=0.0;
	public String[][] view(String arr[][],int row)
	{	
		try
			{
				connect cn=new connect();
				Connection con =cn.aconnect();
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("Select * from InsertItem");
				int c=0;
				
				while(rs.next())
				{
					arr[c][0]=rs.getString(1);
					arr[c][1]=rs.getString(2);
					arr[c][2]=rs.getString(3);
					arr[c][3]=rs.getString(4);
					arr[c][4]=rs.getString(5);
					
					total=total+Double.parseDouble(arr[c][4]);
					
					c++;
				}
				
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			return arr;
	}
}
