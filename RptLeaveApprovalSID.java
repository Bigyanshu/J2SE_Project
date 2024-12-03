import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class RptLeaveApprovalSIDApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2;
	GridLayout gl51;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState;
	JButton btnClose;
	String colHead[]={"SL_No","SerialNo","Curr_Date","Reference_Code","Date","StaffID","StaffName","Gender","Department","Designation","LeaveType","FromDate","ToDate","Narration","Status"};
	Object objData[][];
	JTable tblData;
	JScrollPane jspData;
	Connection conn;
	Statement stmtSelect;
	ResultSet rs;
	int slno,rw,tot;
	JOptionPane dlg;
	Font fnt1;
	String SiD;
	
	public RptLeaveApprovalSIDApp()
	{
		f1=new JFrame("Report of Staff Leave Approval Information StaffID-Wise");
		p1=new JPanel();
		p2=new JPanel();
		gl51=new GridLayout(5,1);
		flw=new FlowLayout();
		
		lHead=new JLabel("Staff Leave Approval Information",JLabel.CENTER);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP College of Technology & Management");
		lLocality=new JLabel("    Bhubaneswar");
		lCity=new JLabel("    Khurda-753001");
		lState=new JLabel("    Odisha");
		
		btnClose=new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setForeground(Color.orange);
		btnClose.setBackground(Color.black);
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
			SiD=dlg.showInputDialog(f1,"Enter Staff_ID :","Detail Report",1);
			
			if(SiD==null)
			{
				f1.setVisible(false);
				f1.dispose();
			}
			else
			{
				lHead.setText("Staff Leave Approval Information ["+SiD+"]");
				rs=stmtSelect.executeQuery("select * from eiprs_tblleaveapprov where StaffID='"+SiD+"'");
				slno=1;
				rw=0;
				
				rs.last();
				tot=rs.getRow();
				objData=new Object[tot][15];
				rs.beforeFirst();
				
				while(rs.next())
				{
					objData[rw][0]=slno;
					objData[rw][1]=rs.getString("SerialNo");
					objData[rw][2]=rs.getString("Date1");
					objData[rw][3]=rs.getString("ReferenceCode");
					objData[rw][4]=rs.getString("Date2");
					objData[rw][5]=rs.getString("StaffID");
					objData[rw][6]=rs.getString("StaffName");
					objData[rw][7]=rs.getString("Gender");
					objData[rw][8]=rs.getString("Department");
					objData[rw][9]=rs.getString("Designation");
					objData[rw][10]=rs.getString("LeaveType");
					objData[rw][11]=rs.getString("FromDate");
					objData[rw][12]=rs.getString("ToDate");
					objData[rw][13]=rs.getString("Narration");
					objData[rw][14]=rs.getString("Status");
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
class RptLeaveApprovalSID
{
	public static void main(String[] s)
	{
		RptLeaveApprovalSIDApp rpt1=new RptLeaveApprovalSIDApp();
	}
}