import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Eiprs_UserManualApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl31,gl22;
	FlowLayout flw;
	JLabel lHead,lHiw;
	JButton bClose;
	Box vb,hb;
	ImageIcon imgClose;
	Font fnt1;
	
	JOptionPane dlg;
	Statement stmtInsert;
	ResultSet rs;
	Connection conn;
	String Hiw;
	//Table HIW;
	
	public Eiprs_UserManualApp()
	{
		f1=new JFrame("User Manual of EIPRS");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		gl31=new GridLayout(3,1);
		gl22=new GridLayout(2,2);
		flw=new FlowLayout();
		
		lHead=new JLabel("User Manual");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,26);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		lHead.setBackground(Color.black);
		lHiw=new JLabel("HOW IT WORKS: It is a Project of Employee Information & Pay Role System of B.P. University of Technology & Management.		Which Contains 7 Interface i.e.");// EIPRS_College,EIPRS_Department,EIPRS_StaffInfo,EIPRS_StaffAttend,EIPRS_Leave,EIPRS_LeaveApproval,EIPRS_SalSlip");
		//.		And Every Program contain one All Report i.e. RptCollege,RptDepartment,RptStaffInfo,RptStaffAttend,RptStaffLeave,RptLeaveApproval,RptSalSlip.	");
		
		
		imgClose=new ImageIcon("IClose1.png");
		
		bClose=new JButton("Close",imgClose);
		bClose.setMnemonic('C');
		
		bClose.setToolTipText("Close The Program");

		bClose.addActionListener(this);
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl22);
		p2.add(lHiw);
		p2.add(new JLabel(""));
		
		
		p3.setLayout(flw);
		p3.add(bClose);
		
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
		//f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(0,00,1200,1000);
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
class Eiprs_UserManual
{
	public static void main(String s[])
	{
		Eiprs_UserManualApp nm=new Eiprs_UserManualApp();
	}
}