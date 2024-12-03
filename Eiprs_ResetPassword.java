import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Eiprs_ResetPasswordApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4;
	GridLayout gl42,gl31;
	FlowLayout flw;
	JLabel lUserN,lPswdHint,lYourAns,lNewP,lHead;
	JTextField tUserN,tYourAns;
	JPasswordField tNewP;//for password field
	JComboBox cmbPswdHint;
	JButton bReset,bCancel,bBack,bClose;
	Box vb,hb;
	Font fnt1;
	ImageIcon imgReset,imgCancel,imgClose,imgGoBack;
	
	int choice;
	JOptionPane dlg;
	Connection conn;
	Statement stmtSelect,stmtInsert,stmtSearch,stmtUpdate;
	ResultSet rs,rsSearch;
	String UserName,PasswordHint,YourAnswer,NewPassword;
	//Table User_Name,Password_Hint,Hint_Answer,Passwords;
	public Eiprs_ResetPasswordApp()
	{
		f1=new JFrame("Forget Password Page");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		
		gl31=new GridLayout(3,1);
		gl42=new GridLayout(4,2);
		
		flw=new FlowLayout();
		
		lUserN=new JLabel("User Name:");
		lPswdHint=new JLabel("Password Hint:");
		lYourAns=new JLabel("Your Answer:");
		lNewP=new JLabel("New Password:");
		lHead=new JLabel("Forget Password");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		lHead.setBackground(Color.white);
		
		tUserN=new JTextField(50);
		tYourAns=new JTextField(50);
		tNewP=new JPasswordField();//Passsword Hiding
		
		cmbPswdHint=new JComboBox();
		cmbPswdHint.addItem("-Select Password Hint-");
		cmbPswdHint.addItem("Your Birth Day");
		cmbPswdHint.addItem("Your Favourite Person");
		cmbPswdHint.addItem("Any Location");
		cmbPswdHint.addItem("Nick Name of Your Delicious Person");
		
		imgCancel=new ImageIcon("JClear.png");
		imgReset=new ImageIcon("JReset.png");
		imgClose=new ImageIcon("JClose1.png");
		imgGoBack=new ImageIcon("JGoBack.png");
		
		bCancel=new JButton("Cancel",imgCancel);
		bReset=new JButton("Reset",imgReset);
		bBack=new JButton("Back To Login Page",imgGoBack);
		bClose=new JButton("Close",imgClose);
		
		bCancel.setMnemonic('X');
		bReset.setMnemonic('R');
		bBack.setMnemonic('B');
		bClose.setMnemonic('C');
		
		bCancel.setToolTipText("Clear The Record...");
		bReset.setToolTipText("Reset The Password...");
		bBack.setToolTipText("Go To Login Page...");
		bClose.setToolTipText("Close The Program...");
		bClose.setForeground(Color.red);
		
		bCancel.addActionListener(this);
		bReset.addActionListener(this);
		bBack.addActionListener(this);
		bClose.addActionListener(this);
		tYourAns.addActionListener(this);
		//tNewP.addActionListener(this);//if not inside of tYourAns then tNewP works, placed outside tNewP do addActionListener
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl42);
		p2.add(lUserN);
		p2.add(tUserN);
		p2.add(lPswdHint);
		p2.add(cmbPswdHint);
		p2.add(lYourAns);
		p2.add(tYourAns);
		p2.add(lNewP);
		p2.add(tNewP);
		
		p3.setLayout(flw);
		p3.add(bReset);
		p3.add(bCancel);
		p3.add(bClose);
		
		p4.setLayout(flw);
		p4.add(bBack);
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(Box.createVerticalStrut(40));
		vb.add(p3);
		vb.add(p4);
		vb.add(Box.createVerticalStrut(10));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(220,140,800,400);
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
		if(ae.getSource()==tYourAns)
		{
			UserName=tUserN.getText();
			PasswordHint=cmbPswdHint.getSelectedItem().toString();
			YourAnswer=tYourAns.getText();
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("Select * from eiprs_tbllogin where User_Name='"+UserName+"' and Password_Hint='"+PasswordHint+"' and Hint_Answer='"+YourAnswer+"'");
				if(rsSearch.next())
					dlg.showMessageDialog(f1,"Password Matched Successfully","Forgot Password",1);
				else
					dlg.showMessageDialog(f1,"Unauthorised Access...Password MissMatch","Forgot Password",1);
			}
			catch(SQLException se)
			{
				System.out.println("Error......"+se);
		    }
		tNewP.requestFocus();//Auto Go To Next Field
		}
		else if(ae.getSource()==bReset)
		{
			if(tUserN.getText().length()>0)
			{
				NewPassword=tNewP.getText();
				UserName=tUserN.getText();
				PasswordHint=cmbPswdHint.getSelectedItem().toString();
				YourAnswer=tYourAns.getText();
				System.out.println("User Name "+UserName);
				//dlg.showMessageDialog(f1,"Unable to Reset Passsword Due To Blank User ID","Alert",0);
				try
				{
					stmtUpdate=conn.createStatement();
					stmtUpdate.executeUpdate("update eiprs_tbllogin set Password_Hint='"+PasswordHint+"',Hint_Answer='"+YourAnswer+"',Passwords='"+NewPassword+"' where User_Name='"+UserName+"'");
					dlg.showMessageDialog(f1,"Password Changed","Forgot Password",1);
				}
				catch(SQLException se)
				{
					System.out.println("Unbale to Reset Password"+se);
				}
			}
		}
			else if(ae.getSource()==tUserN)
			{
				cmbPswdHint.requestFocus();
			}
			else if(ae.getSource()==cmbPswdHint)
			{
				tYourAns.requestFocus();
			}
			else if(ae.getSource()==tYourAns)
			{
				tNewP.requestFocus();
			}
			
		else if(ae.getSource()==bClose)
		{
			dlg.showMessageDialog(f1,"Sure to Close Program...?","Alert",2);
			try
			{
				conn.close();
			}
			catch(SQLException se)
			{
				System.out.println("Error in Closing The Program...!");
			}
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==bCancel)
		{
			tNewP.setText("");
			tUserN.setText("");
			tYourAns.setText("");
			cmbPswdHint.setSelectedItem("");
		}
		else if(ae.getSource()==bBack)
		{
			Eiprs_LoginPageApp una=new Eiprs_LoginPageApp();
			f1.setVisible(false);
			f1.dispose();
		}
	}
}
class Eiprs_ResetPassword
{
	public static void main(String s[])
	{
		Eiprs_ResetPasswordApp rp=new Eiprs_ResetPasswordApp();
	}
}