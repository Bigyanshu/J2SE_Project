import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Eiprs_NewUserApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4;
	GridLayout gl42,gl41;
	FlowLayout flw;
	JLabel lUserN,lPswd,lPswdHint,lHintAns,lHead;
	JTextField tUserN,tPswd,tHintAns;
	JComboBox cmbPswdHint;
	JButton bCreateU,bCancel,bClose,bLogin;
	Box vb,hb;
	Font fnt1;
	ImageIcon imgCreateU,imgCancel,imgClose;
	
	int choice;
	JOptionPane dlg;
	Connection conn;
	Statement stmtSelect,stmtInsert,stmtDupUserName;
	ResultSet rs,rsDupUserName;
	String UserName,Password,PasswordHint,HintAnswer;
	//Table User_Name,Passwords,Password_Hint,Hint_Answer;
	
	public Eiprs_NewUserApp()
	{
		f1=new JFrame("Login Page");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		
		gl41=new GridLayout(4,1);
		gl42=new GridLayout(4,2);
		
		flw=new FlowLayout();
		
		lUserN=new JLabel("User Name:");
		lPswd=new JLabel("Password:");
		lPswdHint=new JLabel("Password Hint:");
		lHintAns=new JLabel("Hint Answer:");
		lHead=new JLabel("New User Registration");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		lHead.setBackground(Color.white);
		
		tUserN=new JTextField(50);
		tPswd=new JTextField(50);
		tHintAns=new JTextField(50);
		
		cmbPswdHint=new JComboBox();
		cmbPswdHint.addItem("-Select Password Hint-");
		cmbPswdHint.addItem("Your Birth Day");
		cmbPswdHint.addItem("Your Favourite Person");
		cmbPswdHint.addItem("Any Location");
		
		imgCreateU=new ImageIcon("JSave.png");
		imgCancel=new ImageIcon("JClear1.png");
		imgClose=new ImageIcon("IClose1.png");
		
		
		bCreateU=new JButton("Create User",imgCreateU);
		bCancel=new JButton("Cancel",imgCancel);
		bClose=new JButton("Close",imgClose);
		bLogin=new JButton("Go To Login");
		
		bCreateU.setMnemonic('N');
		bCancel.setMnemonic('X');
		bClose.setMnemonic('C');
		bLogin.setMnemonic('L');
		
		bCreateU.setToolTipText("Creating An User ID");
		bCancel.setToolTipText("Clear The Records");
		bClose.setToolTipText("Close The Registration");
		bLogin.setToolTipText("If you create new user id then Go To The Login Page");
		bClose.setForeground(Color.red);
		
		bCreateU.addActionListener(this);
		bCancel.addActionListener(this);
		bClose.addActionListener(this);
		bLogin.addActionListener(this);
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
		//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl42);
		p2.add(lUserN);
		p2.add(tUserN);
		p2.add(lPswd);
		p2.add(tPswd);
		p2.add(lPswdHint);
		p2.add(cmbPswdHint);
		p2.add(lHintAns);
		p2.add(tHintAns);
		
		p3.setLayout(flw);
		p3.add(bCreateU);
		p3.add(bCancel);
		p3.add(bClose);
		
		p4.setLayout(flw);
		p4.add(bLogin);
		
		vb.add(Box.createVerticalStrut(20));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(Box.createVerticalStrut(30));
		vb.add(p3);
		vb.add(p4);
		vb.add(Box.createVerticalStrut(20));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(260,140,700,400);
		f1.setVisible(true);
	}
	public void doConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Unable To Load Driver");
		}
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/eiprsdb","root","root1");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==bCreateU)
		{
			UserName=tUserN.getText();
			Password=tPswd.getText();
			PasswordHint=cmbPswdHint.getSelectedItem().toString();
			HintAnswer=tHintAns.getText();
			try
			{
				stmtSelect=conn.createStatement();
				stmtDupUserName=conn.createStatement();
				rsDupUserName=stmtDupUserName.executeQuery("select * from eiprs_tbllogin where User_Name='"+UserName+"'");
				if(rsDupUserName.next() || UserName.length()==0 || Password.length()==0 || PasswordHint.length()==0 || HintAnswer.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate or Blank Records !","Login Page",1);
				}
				else
				{
					stmtSelect.executeUpdate("Insert into eiprs_tbllogin values ('"+UserName+"','"+Password+"','"+PasswordHint+"','"+HintAnswer+"')");
					
					dlg.showMessageDialog(f1,"User ID Created Successfully","Login Page",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Create User ID"+se);
			}
		}
		else if(ae.getSource()==bCancel)
		{
			tUserN.setText("");
			tPswd.setText("");
			cmbPswdHint.setSelectedItem("");
			tHintAns.setText("");
		}
		else if(ae.getSource()==bClose)
		{
			try
			{
				conn.close();
			}
			catch(SQLException se)
			{
				System.out.println("Error...");
			}
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==bLogin)
		{
			Eiprs_LoginPageApp lw=new Eiprs_LoginPageApp();
			f1.setVisible(false);
			f1.dispose();
		}
	}
}
class Eiprs_NewUser
{
	public static void main(String s[])
	{
		Eiprs_NewUserApp una=new Eiprs_NewUserApp();
	}
}