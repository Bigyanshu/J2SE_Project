import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class RptStaffDeptAttendApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2;
	GridLayout gl51;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState;
	JButton btnClose;
	String colHead[]={"SL_No","Referernce_No","Date","Staff_ID","Staff_Name","Gender","Phone","Email","Department","Designation","Status"};
	Object objData[][];
	JTable tblData;
	JScrollPane jspData;
	Connection conn;
	Statement stmtSelect;
	ResultSet rs;
	int slno,rw,tot;
	JOptionPane dlg;
	Font fnt1;
	String Dept;
	
	public RptStaffDeptAttendApp()
	{
		f1=new JFrame("Report of Staff Attend Information Department-Wise");
		p1=new JPanel();
		p2=new JPanel();
		gl51=new GridLayout(5,1);
		flw=new FlowLayout();
		
		lHead=new JLabel("Staff Information",JLabel.CENTER);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP College of Technology & Management");
		lLocality=new JLabel("    Bhubaneswar");
		lCity=new JLabel("    Khurda-753001");
		lState=new JLabel("    Odisha");
		
		btnClose=new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBackground(Color.red);
		btnClose.setForeground(Color.white);
		btnClose.setMnemonic('C');
		
	//Set Panel
		p1.setLayout(gl51);
		p1.add(lHead);
		p1.add(lOrgn);
		p1.add(lLocality);
		p1.add(lCity);
		p1.add(lState);
		
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
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data"+se);
		}
		try
		{
			stmtSelect=conn.createStatement();
			Dept=dlg.showInputDialog(f1,"Enter Department : ","Detail Report",1);
			
			if(Dept==null)
			{
				f1.setVisible(false);
				f1.dispose();
			}
			else
			{
				lHead.setText("Staff Attend ["+Dept+"]");
				rs=stmtSelect.executeQuery("select * from eiprs_tblstaffattend where Department='"+Dept+"'");
				slno=1;
				rw=0;
				
				rs.last();
				tot=rs.getRow();
				objData=new Object[tot][11];
				rs.beforeFirst();

				while(rs.next())
				{
					objData[rw][0]=slno;
					objData[rw][1]=rs.getString("Ref_No");
					objData[rw][2]=rs.getString("Date");
					objData[rw][3]=rs.getString("SID");
					objData[rw][4]=rs.getString("Sname");
					objData[rw][5]=rs.getString("Gender");
					objData[rw][6]=rs.getString("Phone");
					objData[rw][7]=rs.getString("Email");
					objData[rw][8]=rs.getString("Department");
					objData[rw][9]=rs.getString("Designation");
					objData[rw][10]=rs.getString("Status");
					slno++;
					rw++;
				}
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
class RptStaffDeptAttend
{
	public static void main(String[] s)
	{
		RptStaffDeptAttendApp rpt1=new RptStaffDeptAttendApp();
	}
}