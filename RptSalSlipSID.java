import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class RptSalSlipSIDApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2;
	GridLayout gl51;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState;
	JButton btnClose;
	String colHead[]={"SL_No","SlipNum","Date","MonthOf","Year","Staff_ID","StaffName","Gender","Department","Designation","BasicSalary","Da","Amount1","Pf","Amount2","Hra","Amount3","Tax","Amount4","GrossSalary","NetSalary"};
	Object objData[][];
	JTable tblData;
	JScrollPane jspData;
	Connection conn;
	Statement stmtSelect;
	ResultSet rs;
	int slno,rw,tot;
	JOptionPane dlg;
	Font fnt1;
	String sId;
	
	public RptSalSlipSIDApp()
	{
		f1=new JFrame("Report of Staff Salary Slip Information StaffID-Wise");
		p1=new JPanel();
		p2=new JPanel();
		gl51=new GridLayout(5,1);
		flw=new FlowLayout();
		
		lHead=new JLabel("Staff Salary Slip Information",JLabel.CENTER);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP College of Technology & Management");
		lLocality=new JLabel("    Bhubaneswar");
		lCity=new JLabel("    Khurda-753001");
		lState=new JLabel("    Odisha");
		
		btnClose=new JButton("Close");
		//btnClose.setAccelerator(KeyStroke.getKeyStroke("ctrl c"));
		btnClose.addActionListener(this);
		btnClose.setToolTipText("Close The Report");
		btnClose.setForeground(Color.white);
		btnClose.setBackground(Color.red);
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
			sId=dlg.showInputDialog(f1,"Enter StaffID :","Detail Report",1);
			
			if(sId==null)
			{
				f1.setVisible(false);
				f1.dispose();
			}
			else
			{
				lHead.setText("Staff Salary Slip Information ["+sId+"]");
				rs=stmtSelect.executeQuery("select * from eiprs_tblsalslip where Staff_ID='"+sId+"'");
				slno=1;
				rw=0;
				
				rs.last();
				tot=rs.getRow();
				objData=new Object[tot][21];
				rs.beforeFirst();
				
				while(rs.next())
				{
					objData[rw][0]=slno;
					objData[rw][1]=rs.getString("SlipNum");
					objData[rw][2]=rs.getString("Date");
					objData[rw][3]=rs.getString("MonthOf");
					objData[rw][4]=rs.getString("Year");
					objData[rw][5]=rs.getString("Staff_ID");
					objData[rw][6]=rs.getString("StaffName");
					objData[rw][7]=rs.getString("Gender");
					objData[rw][8]=rs.getString("Department");
					objData[rw][9]=rs.getString("Designation");
					objData[rw][10]=rs.getString("BasicSalary");
					objData[rw][11]=rs.getString("Da");
					objData[rw][12]=rs.getString("Amount1");
					objData[rw][13]=rs.getString("Pf");
					objData[rw][14]=rs.getString("Amount2");
					objData[rw][15]=rs.getString("Hra");
					objData[rw][16]=rs.getString("Amount3");
					objData[rw][17]=rs.getString("Tax");
					objData[rw][18]=rs.getString("Amount4");
					objData[rw][19]=rs.getString("GrossSalary");
					objData[rw][20]=rs.getString("NetSalary");
					slno++;
					rw++;
				}
			}
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Get Data"+se);
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
class RptSalSlipSID
{
	public static void main(String[] s)
	{
		RptSalSlipSIDApp rpt1=new RptSalSlipSIDApp();
	}
}