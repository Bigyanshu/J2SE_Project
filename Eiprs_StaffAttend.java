import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class StaffAttendApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl61,gl64,gl12;
	FlowLayout flw;
	JLabel lHead,lRefNo,lDt,lSID,lSName,lGen,lPhone,lEml,lDept,lDegn,lSts;
	JTextField txtRefNo,txtDt,txtSName,txtGen,txtPhone,txtEml,txtDept,txtDegn;
	JComboBox cmbSID,cmbSts;
	JButton btnClear,btnSave,btnNext,btnPrevious,btnSearch,btnDelete,btnUpdate		,btnClose;
	Box vb,hb;
	Font fnt1,fnt2;
	ImageIcon imgNext,imgSave,imgClose,imgClear,imgPrevious,imgDelete,			imgSearch,imgUpdate;
	Color blue,black;
	Connection conn;
	String Ref_No,Date,SID,Sname,Gender,Phone,Email,Department,Designation,Status;
	Statement stmtSID,stmtInsert,stmtSelect,stmtDelete,stmtUpdate,stmtSearch,stmtDupRef_No,stmtData;
	ResultSet rs,rsSID,rsSearch,rsDupRef_No,rsData;
	JOptionPane dlg;
	int choice;

	public StaffAttendApp()
	{
		f1=new JFrame("BPCTM Staff Attendance Register");
		
		//Panel Declaration
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
	
		gl61=new GridLayout(6,1);
		gl64=new GridLayout(6,4);
		gl12=new GridLayout(1,4);
	
		flw=new FlowLayout();
	
		lHead=new JLabel("Staff  Attendance  Register");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,26);
		lHead.setFont(fnt1);
		lHead.setFont(fnt2);
		lHead.setForeground(Color.red);
	
		lRefNo=new JLabel("Reference No:");
		lDt=new JLabel("    Date:");
		lSID=new JLabel("Staff ID:");
		lSName=new JLabel("Staff Name:");
		lGen=new JLabel("    Gender:");
		lPhone=new JLabel("Phone No:");
		lEml=new JLabel("    E-Mail ID:");
		lDept=new JLabel("Department:");
		lDegn=new JLabel("    Designation:");
		lSts=new JLabel("Status:");

		txtRefNo=new JTextField(20);
		txtDt=new JTextField(20);
		txtSName=new JTextField(30);
		txtGen=new JTextField(10);
		txtPhone=new JTextField(30);
		txtEml=new JTextField(30);
		txtDept=new JTextField(20);
		txtDegn=new JTextField(20);
	
		cmbSID=new JComboBox();
		cmbSID.addItem("-Select Staff ID: -");
	//Like Previous Prog
	
		cmbSts=new JComboBox();
		cmbSts.addItem("_Select Status_");
		cmbSts.addItem("Present");
		cmbSts.addItem("Absent");
		cmbSts.addItem("Present/X");
		cmbSts.addItem("X/Present");
	
	//ImageIcon Declaration
		imgClear=new ImageIcon("JClear1.png");//3
		imgSave=new ImageIcon("JSave.png");//2
		imgDelete=new ImageIcon("JDelete.png");//2
		imgNext=new ImageIcon("JNext.png");//
		imgPrevious=new ImageIcon("JPrevious.png");//
		imgClose=new ImageIcon("IClose1.png");
		imgSearch=new ImageIcon("ISearch.png");
		imgUpdate=new ImageIcon("IEdit1.png");
	//Button Declaration
		btnClear=new JButton("Clear",imgClear);
		btnSave=new JButton("Save",imgSave);
		btnNext=new JButton("Next",imgNext);
		btnPrevious=new JButton("Previous",imgPrevious);
		btnSearch=new JButton("Search",imgSearch);
		btnDelete=new JButton("Delete",imgDelete);
		btnUpdate=new JButton("Update",imgUpdate);
		btnClose=new JButton("Close",imgClose);
		
		//Assigning Window Button to Program
		btnClear.setMnemonic('B');
		btnSave.setMnemonic('S');
		btnNext.setMnemonic('N');
		btnPrevious.setMnemonic('P');
		btnSearch.setMnemonic('F');
		btnDelete.setMnemonic('D');
		btnUpdate.setMnemonic('U');
		btnClose.setMnemonic('C');
	//Set ToolTipText
		btnClear.setToolTipText("Clear the Fields...");
		btnSave.setToolTipText("Save Records...");
		btnNext.setToolTipText("Go to Next Records...");
		btnPrevious.setToolTipText("Go to Previous Records...");
		btnSearch.setToolTipText("Search the Reference No...");
		btnDelete.setToolTipText("Delete Records...");
		btnUpdate.setToolTipText("Update Text Fields...");
		btnClose.setToolTipText("Close The GUI Application...");
	//Tooltip for TextField
		txtRefNo.setToolTipText("Enter Reference No of Staff...");
		txtDt.setToolTipText("Enter Current Date...");
		cmbSID.setToolTipText("Enter Staff ID...");
		txtSName.setToolTipText("Enter Staff Name...");
		txtGen.setToolTipText("Enter Gender of Staff...");
		txtPhone.setToolTipText("Enter Phone No of Staff...");
		txtEml.setToolTipText("Enter Email ID of Staff...");
		txtDept.setToolTipText("Enter Department of Staff...");
		txtDegn.setToolTipText("Enter Designation of Staff...");
		cmbSts.setToolTipText("Enter Status of Staff...");
	//ActionListener Adding in Button
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
		cmbSID.addActionListener(this);
	//Actoin Listener Adding in TextField
		txtRefNo.addActionListener(this);
		txtDt.addActionListener(this);
		cmbSID.addActionListener(this);
		txtSName.addActionListener(this);
		txtGen.addActionListener(this);
		txtPhone.addActionListener(this);
		txtEml.addActionListener(this);
		txtDept.addActionListener(this);
		txtDegn.addActionListener(this);
		cmbSts.addActionListener(this);
		
	//Vertical & Horizontal Box Creating
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
	
		blue=new Color(125,0,0);
		black=new Color(255,255,255);
	
	//Set Layout
		p1.setLayout(flw);
		p1.add(lHead);
	
		p2.setLayout(gl64);
		p2.add(lRefNo);
		p2.add(txtRefNo);
		p2.add(lDt);
		p2.add(txtDt);
		p2.add(lSID);
		p2.add(cmbSID);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lSName);
		p2.add(txtSName);
		p2.add(lGen);
		p2.add(txtGen);
		p2.add(lPhone);
		p2.add(txtPhone);
		p2.add(lEml);
		p2.add(txtEml);
		p2.add(lDept);
		p2.add(txtDept);
		p2.add(lDegn);
		p2.add(txtDegn);
	    p2.add(lSts);
		p2.add(cmbSts);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));

		
		p3.setLayout(flw);
		p3.add(btnClear);
		p3.add(btnSave);
		p3.add(btnNext);
		p3.add(btnPrevious);
		p3.add(btnSearch);
		p3.add(btnDelete);
		p3.add(btnUpdate);
		p3.add(btnClose);
	
		vb.add(Box.createVerticalStrut(5));
		vb.add(p1);
		vb.add(p2);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p3);
		hb.add(Box.createVerticalStrut(10));
	
		hb.add(Box.createHorizontalStrut(10));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(10));
	
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setBounds(220,130,800,450);
		//f1.setResizable(false);//Never be Full Screen
		doConnect();
		fillSID();
		f1.setVisible(true);
	}
	public void fillSID()
	{
		try
		{
			stmtSID=conn.createStatement();
			rsSID=stmtSID.executeQuery("select *from eiprs_tblStaffInfo");
			while(rsSID.next())
			{
				SID=rsSID.getString("SID");
				cmbSID.addItem(SID);
			}
		}
		catch(SQLException se)
		{
			//System.out.println("Unable to Add SID");
			dlg.showMessageDialog(f1,"Unable to Add SID !","EIPRS",1);
		}
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
			stmtSelect=conn.createStatement();
			rs=stmtSelect.executeQuery("select *from eiprs_tblstaffattend");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data:"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnClear)
		{
			if(txtRefNo.getText().length()>0)
			{
				txtRefNo.setText("");
				txtDt.setText("");
				cmbSID.setSelectedIndex(0);
				txtSName.setText("");
				txtGen.setText("");
				txtPhone.setText("");
				txtEml.setText("");
				txtDept.setText("");
				txtDegn.setText("");
				cmbSts.setSelectedIndex(0);
				dlg.showMessageDialog(f1,"Cleared All Record","Alert",2);
			}
		}
			else if(ae.getSource()==txtRefNo)
			{
				txtDt.requestFocus();
			}
			else if(ae.getSource()==txtDt)
			{
				cmbSID.requestFocus();
			}
			else if(ae.getSource()==cmbSID)
			{
				txtSName.requestFocus();
			}
			else if(ae.getSource()==txtSName)
			{
				txtGen.requestFocus();
			}
			else if(ae.getSource()==txtGen)
			{
				txtPhone.requestFocus();
			}
			else if(ae.getSource()==txtPhone)
			{
				txtEml.requestFocus();
			}
			else if(ae.getSource()==txtEml)
			{
				txtDept.requestFocus();
			}
			else if(ae.getSource()==txtDept)
			{
				txtDegn.requestFocus();
			}
			else if(ae.getSource()==txtDegn)
			{
				cmbSts.requestFocus();
			}
		
		else if(ae.getSource()==cmbSID && cmbSID.getSelectedIndex()>0)//for Avoiding Blank Statement
		{
			SID=cmbSID.getSelectedItem().toString();
			try
			{
				stmtData=conn.createStatement();
				rsData=stmtData.executeQuery("select * from eiprs_tblstaffinfo where SID='"+SID+"'");
				
				rsData.next();

			//String=rsData.getString("Table");
				Sname=rsData.getString("Sname");
				Gender=rsData.getString("Gender");
				Phone=rsData.getString("Phone");
				Email=rsData.getString("Email");
				Department=rsData.getString("Department");
				Designation=rsData.getString("Designation");
				
				txtSName.setText(Sname);
				txtGen.setText(Gender);
				txtPhone.setText(Phone);
				txtEml.setText(Email);
				txtDept.setText(Department);
				txtDegn.setText(Designation);
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Show Data"+se);
				dlg.showMessageDialog(f1,"Unable to Show Data","EIPRS",1);
			}
			txtSName.requestFocus();//Auto Go Combo To Next Field
		}
		else if(ae.getSource()==btnSave)
			{
			//Table_Name=Label_Variable.getText();
				Ref_No=txtRefNo.getText();
				Date=txtDt.getText();
				SID=cmbSID.getSelectedItem().toString();
				Sname=txtSName.getText();
				Gender=txtGen.getText();
				Phone=txtPhone.getText();
				Email=txtEml.getText();
				Department=txtDept.getText();
				Designation=txtDegn.getText();
				Status=cmbSts.getSelectedItem().toString();
				try
				{
					stmtInsert=conn.createStatement();
					stmtDupRef_No=conn.createStatement();
					rsDupRef_No=stmtDupRef_No.executeQuery("select * from eiprs_tblstaffattend where Ref_No='"+Ref_No+"'");
					
					if(rsDupRef_No.next()  || Ref_No.length()==0 || Date.length()==0 || SID.length()==0 || Sname.length()==0 || Gender.length()==0 || Phone.length()==0 || Email.length()==0 || Department.length()==0 || Designation.length()==0 || Status.length()==0)
					dlg.showMessageDialog(f1,"Duplicate Reference No Or Blank Record","EIPRS",1);
					else
					{
						stmtInsert.executeUpdate("insert into eiprs_tblstaffattend values('"+Ref_No+"','"+Date+"','"+SID+"','"+Sname+"','"+Gender+"','"+Phone+"','"+Email+"','"+Department+"','"+Designation+"','"+Status+"')");
						
						dlg.showMessageDialog(f1,"One Record Saved Successfully","EIPRS",1);
					}
				}
				catch(SQLException se)
				{
					System.out.println("Unable to Save Records !"+se);
					dlg.showMessageDialog(f1,"Unable to Save Records","EIPRS",1);
				}
			}
		else if(ae.getSource()==btnNext)
		{
			try
			{
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"Last Record !,Unable to Go Next","EIPRS",1);
				}
				else
				{
					rs.next();
					Ref_No=rs.getString("Ref_No");
					Date=rs.getString("Date");
					SID=rs.getString("SID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Phone=rs.getString("Phone");
					Email=rs.getString("Email");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					Status=rs.getString("Status");
					
					txtRefNo.setText(Ref_No);
					txtDt.setText(Date);
					cmbSID.setSelectedItem(SID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtPhone.setText(Phone);
					txtEml.setText(Email);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to go Next !"+se);
				dlg.showMessageDialog(f1,"Unable to Go Next","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnPrevious)
		{
			try
			{
				if(rs.isFirst())
				{
					dlg.showMessageDialog(f1,"This is First Record Unable to Go Previousl","EIPRS",1);
				}
				else
				{
					rs.previous();
					//String name--->Table name
					
					Ref_No=rs.getString("Ref_No");
					Date=rs.getString("Date");
					SID=rs.getString("SID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Phone=rs.getString("Phone");
					Email=rs.getString("Email");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					Status=rs.getString("Status");
						
					txtRefNo.setText(Ref_No);
					txtDt.setText(Date);
					cmbSID.setSelectedItem(SID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtPhone.setText(Phone);
					txtEml.setText(Email);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				//System.out.pritln("Unable to go previous"+se);
				dlg.showMessageDialog(f1,"Unable to Go Previous This is First Record","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnSearch)
		{
			Ref_No=dlg.showInputDialog(f1,"Enter Reference No:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("select * from eiprs_tblstaffattend where Ref_No='"+Ref_No+"'");
				if(rsSearch.next())
				{
					//Ref_No=rsSearch.getString("Ref_No");
					Date=rsSearch.getString("Date");
					SID=rsSearch.getString("SID");
					Sname=rsSearch.getString("Sname");
					Gender=rsSearch.getString("Gender");
					Phone=rsSearch.getString("Phone");
					Email=rsSearch.getString("Email");
					Department=rsSearch.getString("Department");
					Designation=rsSearch.getString("Designation");
					Status=rsSearch.getString("Status");
					
					txtRefNo.setText(Ref_No);
					txtDt.setText(Date);
					cmbSID.setSelectedItem(SID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtPhone.setText(Phone);
					txtEml.setText(Email);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Search"+se);
				dlg.showMessageDialog(f1,"Unable to Search","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
			Sname=txtSName.getText();
			try
			{
				if(txtRefNo.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure to Delete ?","Alert",1);
					if(choice==0)//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete from eiprs_tblstaffattend where SName='"+Sname+"'");
						dlg.showMessageDialog(f1,"One Record Delete Successfully","EIPRS",3);
					}
					else
					{
						System.out.println("Unable to Delete");
						dlg.showMessageDialog(f1,"Thankful Data not Delete","EIPRS",1);
					}
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable to Delete.Try Again Latter","EIPRS",2);
			}
		}
		else if(ae.getSource()==btnUpdate)
		{
			Ref_No=txtRefNo.getText();
			Date=txtDt.getText();
			SID=cmbSID.getSelectedItem().toString();
			Sname=txtSName.getText();
			Gender=txtGen.getText();
			Phone=txtPhone.getText();
			Email=txtEml.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			Status=cmbSts.getSelectedItem().toString();
			try
			{
				stmtUpdate=conn.createStatement();
				if(Ref_No.length()==0 || Date.length()==0 || SID.length()==0 || Sname.length()==0 || Gender.length()==0 || Phone.length()==0 || Email.length()==0 || Department.length()==0 || Designation.length()==0 || Status.length()==0)
				{
					dlg.showMessageDialog(f1,"Blank Statement Can't Update","EIPRS",1);

				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblstaffattend set Date='"+Date+"',SID='"+SID+"',Sname='"+Sname+"',Gender='"+Gender+"',Phone='"+Phone+"',Email='"+Email+"',Department='"+Department+"',Designation='"+Designation+"',Status='"+Status+"' where Ref_No='"+Ref_No+"'");
					
					dlg.showMessageDialog(f1,"One Record Update Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Update"+se);
				dlg.showMessageDialog(f1,"Unable to Update","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnClose)
		{
			dlg.showMessageDialog(f1,"Sure to Close Program...?","Alert",2);
			try
			{
				conn.close();
			}
			catch(SQLException se)
			{
				System.out.println("Error to close the Program...!");
			}
			f1.setVisible(false);
			f1.dispose();
		}
	}
}
class Eiprs_StaffAttend
{
	public static void main(String[] s)
	{
		StaffAttendApp sa;
		sa=new StaffAttendApp();
	}
}