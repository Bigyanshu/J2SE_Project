import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Eiprs_LoginPageApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4,p5;
	GridLayout gl22,gl51;
	FlowLayout flw;
	JLabel lUserN,lPswd,lHead,lHead2;
	JTextField tUserN;
	JPasswordField tPswd;//Password Field
	JButton bLogin,bCancel,bForget,bClose,btnNUser;
	Box vb,hb;
	Font fnt1;
	ImageIcon imgLogin,imgCancel,imgClose,imgForget;
	
	int choice;
	JOptionPane dlg;
	Connection conn;
	Statement stmtSelect,stmtSearch,stmtUpdate;
	ResultSet rs,rsSearch;
	String UserName,Password;
	//Table User_Name,Passwords;
	
	public Eiprs_LoginPageApp()
	{
		f1=new JFrame("Login Page");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		
		gl51=new GridLayout(5,1);
		gl22=new GridLayout(2,2);
		
		flw=new FlowLayout();
		
		lHead=new JLabel("Login");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lHead2=new JLabel("Who You Are ?");
		lHead2.setForeground(Color.blue);
		lUserN=new JLabel("User Name:");
		lPswd=new JLabel("Password:");
		
		tUserN=new JTextField(50);
		//tPswd=new JTextField(50);
		//tUserN.requestFocus();//Auto Go To Next Field
		tPswd=new JPasswordField();
		
		imgLogin=new ImageIcon("JSave.png");
		imgCancel=new ImageIcon("JClear1.png");
		imgClose=new ImageIcon("JClose.png");
		imgForget=new ImageIcon("JReset.png");
		
		bLogin=new JButton("Login",imgLogin);
		bCancel=new JButton("Cancel",imgCancel);
		bClose=new JButton("Close",imgClose);
		bForget=new JButton("Forget Password",imgForget);
		btnNUser=new JButton("New User");
		bClose.setForeground(Color.red);
		
		bLogin.setToolTipText("Login...");
		bCancel.setToolTipText("Clear Record...");
		bClose.setToolTipText("Close The Program...");
		bForget.setToolTipText("Reset The Password");
		btnNUser.setToolTipText("Create New User ID...");
		
		bLogin.addActionListener(this);
		bCancel.addActionListener(this);
		bForget.addActionListener(this);
		bClose.addActionListener(this);
		btnNUser.addActionListener(this);
		
		//Assigning Window Key to Button
		bLogin.setMnemonic('L');
		bCancel.setMnemonic('X');
		bForget.setMnemonic('F');
		bClose.setMnemonic('c');
		btnNUser.setMnemonic('N');
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(flw);
		p2.add(lHead2);
		
		p3.setLayout(gl22);
		p3.add(lUserN);
		p3.add(tUserN);
		p3.add(lPswd);
		p3.add(tPswd);
		
		p4.setLayout(flw);
		p4.add(bLogin);
		p4.add(bCancel);
		p4.add(bClose);
		
		p5.setLayout(flw);
		p5.add(bForget);
		p5.add(btnNUser);
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(p2);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p3);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p4);
		vb.add(p5);
		vb.add(Box.createVerticalStrut(20));
		
		hb.add(Box.createHorizontalStrut(40));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(40));
		
	//set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(330,200,600,350);
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
		if(ae.getSource()==bLogin)
		{
			UserName=tUserN.getText();
			Password=tPswd.getText();
			if(UserName.length()==0 || Password.length()==0 )
				dlg.showMessageDialog(f1,"How can You Login with Blank Records !","Login",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("Select * from eiprs_tbllogin where User_Name='"+UserName+"' and Passwords='"+Password+"'");
				if(rsSearch.next())
				{	
					UserName=rsSearch.getString("User_Name");
					Password=rsSearch.getString("Passwords");
					dlg.showMessageDialog(f1,"Login Successfully","Login",1);
					MenuDashWin bh=new MenuDashWin();
					f1.setVisible(false);
					f1.dispose();
				}
				else 
				{
					dlg.showMessageDialog(f1,"Invalid User ID","Login",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Create User ID"+se);
				dlg.showMessageDialog(f1,"Unable to Create User ID","Login",1);
			}
		}
		else if(ae.getSource()==bClose)
		{
			try
			{
				conn.close();//for no Hang
			}
			catch(SQLException se)
			{
				System.out.println("Error...");
			}
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==bForget)
		{
			UserName=tUserN.getText();
			Password=tPswd.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				stmtUpdate.executeUpdate("Update eiprs_tbllogin set User_Name='"+UserName+"',Passwords='"+Password+"' where User_Name='"+UserName+"' and Passwords='"+Password+"'");
				
				dlg.showMessageDialog(f1,"Sure To Forget Password...?","Forget Password",1);
				Eiprs_ResetPasswordApp gf=new Eiprs_ResetPasswordApp();
				f1.setVisible(false);
				f1.dispose();
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Forget The Password"+se);
			}
		}
		else if(ae.getSource()==btnNUser)
		{
			Eiprs_NewUserApp kl=new Eiprs_NewUserApp();
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==tUserN)
			{
				tPswd.requestFocus();
			}
		else if(ae.getSource()==bCancel)
		{
			if(tUserN.getText().length()>0)//This Msg can show when present at 1st position
			{
				tUserN.setText("");
				tPswd.setText("");
				dlg.showMessageDialog(f1,"Record was Cleared","Login",2);
			}
		}
	}
}
class Eiprs_LoginPage
{
	public static void main(String s[])
	{
		Eiprs_LoginPageApp una=new Eiprs_LoginPageApp();
	}
}