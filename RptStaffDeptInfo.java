import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class RptStaffDeptInfoApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2;
	GridLayout gl51;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState;
	JButton btnClose;
	String colHead[]={"SL_No","Staff_ID","Staff_Name","Gender","Father_Name","Mother_Name","DOB","Locality","City","State","PIN","Phone","Email","Date_Of_Join","Department","Designation","Bsalary","DA","HRA","PF","TAX"};
	Object objData[][];
	JTable tblData;
	JScrollPane jspData;
	Connection conn;
	Statement stmtSelect;
	ResultSet rs;
	int slno,rw,tot;
	JOptionPane dlg;
	Font fnt1;
	String dname;
	
	public RptStaffDeptInfoApp()
	{
		f1=new JFrame("Report of Staff Department Information");
		p1=new JPanel();
		p2=new JPanel();
		gl51=new GridLayout(5,1);
		flw=new FlowLayout();
		
		lHead=new JLabel("Staff Information",JLabel.CENTER);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP University of Technology & Management");
		lLocality=new JLabel("    Bhubaneswar");
		lCity=new JLabel("    Khurda-753001");
		lState=new JLabel("    Odisha");
		
		btnClose=new JButton("Close");
		btnClose.addActionListener(this);
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
		f1.setBackground(Color.black);
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
			dname=dlg.showInputDialog(f1,"Enter Department Name : ","All Report",1);
			
			if(dname==null)
			{
				f1.setVisible(false);
				f1.dispose();
			}//It is use to go back from Interface not entering into REPORT if click Cancle button
			else
			{
				lHead.setText("Staff Information ["+dname+"]");
				rs=stmtSelect.executeQuery("select * from eiprs_tblstaffinfo where Department='"+dname+"'");
				slno=1;
				rw=0;
				
				rs.last();
				tot=rs.getRow();
				objData=new Object[tot][21];
				rs.beforeFirst();
				
				while(rs.next())
				{
					objData[rw][0]=slno;
					objData[rw][1]=rs.getString("SID");
					objData[rw][2]=rs.getString("Sname");
					objData[rw][3]=rs.getString("Gender");
					objData[rw][4]=rs.getString("Fname");
					objData[rw][5]=rs.getString("Mname");
					objData[rw][6]=rs.getString("DOB");
					objData[rw][7]=rs.getString("Locality");
					objData[rw][8]=rs.getString("City");
					objData[rw][9]=rs.getString("State");
					objData[rw][10]=rs.getString("PIN");
					objData[rw][11]=rs.getString("Phone");
					objData[rw][12]=rs.getString("Email");
					objData[rw][13]=rs.getString("DOJoin");
					objData[rw][14]=rs.getString("Department");
					objData[rw][15]=rs.getString("Designation");
					objData[rw][16]=rs.getString("Bsalary");
					objData[rw][17]=rs.getString("DA");
					objData[rw][18]=rs.getString("HRA");
					objData[rw][19]=rs.getString("PF");
					objData[rw][20]=rs.getString("TAX");
					slno++;
					rw++;
				}
			}
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Get Data");
			dlg.showMessageDialog(f1,"Unable to Get Data","All Report",0);
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
class RptStaffDeptInfo
{
	public static void main(String[] s)
	{
		RptStaffDeptInfoApp rpt1=new RptStaffDeptInfoApp();
	}
}