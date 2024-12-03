import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Eiprs_AboutApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl31,gl22;
	FlowLayout flw;
	JLabel lHead,lAbout;
	JButton bClose;
	Box vb,hb;
	ImageIcon imgClose;
	Font fnt1;
	
	JOptionPane dlg;
	Statement stmtInsert;
	ResultSet rs;
	Connection conn;
	String Disc;
	//Table Disclaimer;
	
	public Eiprs_AboutApp()
	{
		f1=new JFrame("About EIPRS");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		gl31=new GridLayout(3,1);
		gl22=new GridLayout(2,2);
		flw=new FlowLayout();
		
		lHead=new JLabel("About EIPRS");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,26);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		lAbout=new JLabel("Disclaimer: B.P. University of Technology & Management & Manage Thier Employee Information & Pay Role System.");
		
		imgClose=new ImageIcon("IClose1.png");
		
		bClose=new JButton("Close",imgClose);
	//Buttons Assign with W-Button
		bClose.setMnemonic('C');
		
		bClose.setToolTipText("Close The Program");
		
		bClose.addActionListener(this);
		//lAbout.addActionListener(this);
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl22);
		p2.add(lAbout);
		p2.add(new JLabel(""));
		
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bClose);//,JLabel.RIGHT);
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(p2);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p3);
		vb.add(Box.createVerticalStrut(10));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(220,220,800,250);
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
			System.out.println("Unable to Load Driver");
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
		if(ae.getSource()==bClose)
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
	}
}
class Eiprs_About
{
	public static void main(String s[])
	{
		Eiprs_AboutApp nm=new Eiprs_AboutApp();
	}
}