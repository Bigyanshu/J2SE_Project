import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class RptDepartmentApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2;
	GridLayout gl51;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState;
	JButton btnClose;
	String colHead[]={"SL_No","Dcode","Dname","Phone","HOD"};
	//colHead.setBackground(Color.white);
	Object objData[][];
	JTable tblData;
	JScrollPane jspData;
	Connection conn;
	Statement stmtSelect;
	ResultSet rs;
	int slno,rw,tot;
	JOptionPane dlg;
	Font fnt1;
	
	public RptDepartmentApp()
	{
		f1=new JFrame("Department Information");
		p1=new JPanel();
		p2=new JPanel();
		gl51=new GridLayout(7,1);
		flw=new FlowLayout();
		
		lHead=new JLabel("Department Information",JLabel.CENTER);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP University of Technology & Management",JLabel.CENTER);
		lOrgn.setForeground(Color.blue);
		lLocality=new JLabel("    Bhubaneswar",JLabel.CENTER);
		lLocality.setForeground(Color.blue);
		lCity=new JLabel("    Khurda-753001",JLabel.CENTER);
		lCity.setForeground(Color.blue);
		lState=new JLabel("    Odisha",JLabel.CENTER);
		lState.setForeground(Color.blue);
		
		btnClose=new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setToolTipText("Close The Interface");
		btnClose.setForeground(Color.red);
		btnClose.setBackground(Color.green);
		btnClose.setMnemonic('C');
		
	//Set Panel
		p1.setLayout(gl51);
		p1.add(lHead);
		p1.add(new JLabel(""));
		p1.add(lOrgn);
		p1.add(lLocality);
		p1.add(lCity);
		p1.add(lState);
		p1.add(new JLabel(""));
		
		p2.setLayout(flw);
		p2.add(btnClose);
		doConnect();
		
		tblData=new JTable(objData,colHead);
		jspData=new JScrollPane(tblData);
		
	//Set Frame
		f1.setLayout(new BorderLayout());
		f1.add(p1,BorderLayout.NORTH);
		f1.add(jspData,BorderLayout.CENTER);
		f1.add(p2,BorderLayout.SOUTH);
		f1.setBounds(0,0,1400,750);
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
			stmtSelect=conn.createStatement();
			rs=stmtSelect.executeQuery("Select * from eiprs_tblDepartment");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data"+se);
		}
		try
		{
			stmtSelect=conn.createStatement();
			rs=stmtSelect.executeQuery("select * from eiprs_tblDepartment");
			slno=1;
			rw=0;
			
			rs.last();
			tot=rs.getRow();
			objData=new Object[tot][5];
			rs.beforeFirst();
			
			while(rs.next())
			{
				objData[rw][0]=slno;
				objData[rw][1]=rs.getString("Dcode");
				objData[rw][2]=rs.getString("Dname");
				objData[rw][3]=rs.getString("Phone");
				objData[rw][4]=rs.getString("HOD");
				slno++;
				rw++;
			}
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Get Data");
			dlg.showMessageDialog(f1,"Unable to Get Data","EIPRS",0);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnClose)
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
class RptDepartment
{
	public static void main(String[] s)
	{
		RptDepartmentApp rpt1=new RptDepartmentApp();
	}
}