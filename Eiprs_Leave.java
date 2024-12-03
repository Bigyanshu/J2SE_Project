import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class Eiprs_LeaveApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3;
	FlowLayout flw;
	GridLayout gl74,gl31;
	JLabel lHead,lRNo,lDt,lS_id,lSName,lGen,lDept,lDegn,lLType,lFDt,lTDt,lNar;
	JTextField txtRNo,txtDt,txtSName,txtGen,txtDept,txtDegn,txtFDt,txtTDt,txtNar;
	JButton btnClear, btnSave, btnNext, btnPrevious, btnUpdate, btnClose,btnSearch, btnDelete;
	JComboBox cmbS_id,cmbLType;
	Box vb,hb;
	Font fnt1,fnt2;
	Color black,white;
	ImageIcon imgNext,imgSave,imgClear,imgClose,imgDelete,imgPrevious,imgSearch,imgUpdate;
	
	Connection conn;
	String RefNo,Date,sID,Sname,Gender,Department,Designation,LeaveType,FromDate,ToDate,Narration;
	Statement stmtInsert,stmtUpdate,stmtSelect,stmtDelete,stmtSearch,stmtData,stmtDupsID,stmtsID;
	JOptionPane dlg;
	int choice;
	ResultSet rs,rsData,rsSearch,rsDupsID,rssID;
	
//Constructor Start
	public Eiprs_LeaveApp()
	{
	//Declaring All Objects
		f1=new JFrame("BPCTM Staff Leave Register");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		flw=new FlowLayout();
		
		gl74=new GridLayout(7,4);
		gl31=new GridLayout(3,1);
		
	//Label Declared
		lHead=new JLabel("Leave Application");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,30);
		lHead.setFont(fnt1);
		lHead.setFont(fnt2);
		lHead.setForeground(Color.white);
		p1.setBackground(Color.black);
		
		lRNo=new JLabel("Reference No:");
		lDt=new JLabel("    Date:");
		lS_id=new JLabel("Staff ID:");
		lSName=new JLabel("Staff Name:");
		lGen=new JLabel("    Gender:");
		lDept=new JLabel("Department:");
		lDegn=new JLabel("    Designation");
		lLType=new JLabel("Leave Type:");
		lFDt=new JLabel("From Date:");
		lTDt=new JLabel("    To Date:");
		lNar=new JLabel("Narration:");
		
	//TextField Declared
		txtRNo=new JTextField(30);
		txtDt=new JTextField(20);
		txtSName=new JTextField(50);
		txtGen=new JTextField(10);
		txtDept=new JTextField(40);
		txtDegn=new JTextField(30);
		txtFDt=new JTextField(20);
		txtTDt=new JTextField(20);
		txtNar=new JTextField(50);
		
		cmbS_id=new JComboBox();
		cmbS_id.addItem("-Select Staff ID-");
		//Comes From staffinfo
		
		cmbLType=new JComboBox();
		cmbLType.addItem("-Select Leave Type-");
		cmbLType.addItem("Duty Leave");
		cmbLType.addItem("Sabbatical Leave");
		cmbLType.addItem("Casual Leave");
		cmbLType.addItem("Special Casual Leave");
		cmbLType.addItem("Earned Leave");
		cmbLType.addItem("Half-Pay Leave");
		cmbLType.addItem("Commuted Leave");
		cmbLType.addItem("Extraordinary Leave");
		cmbLType.addItem("Leave Not Due");
		cmbLType.addItem("Child Care Leave");
		cmbLType.addItem("Paternity Leave");
		cmbLType.addItem("Maternity Leave");
		cmbLType.addItem("Adoption Leave");
		cmbLType.addItem("Vacation Leave");
		cmbLType.addItem("Teachers Appointed On Probation");
		cmbLType.addItem("Leave Salary");
		
	//ImageIcon Declared
		imgClear=new ImageIcon("JClear1.png");//3
		imgSave=new ImageIcon("JSave.png");//2
		imgDelete=new ImageIcon("JDelete.png");//2
		imgNext=new ImageIcon("JNext.png");//
		imgPrevious=new ImageIcon("JPrevious.png");//
		imgClose=new ImageIcon("IClose1.png");
		imgSearch=new ImageIcon("ISearch.png");
		imgUpdate=new ImageIcon("IEdit1.png");
		
	// Button Declared
		btnClear = new JButton("Clear",imgClear);
		btnSave = new JButton("Save",imgSave);
		btnNext = new JButton("Next",imgNext);
		btnPrevious = new JButton("Previous",imgPrevious);
		btnSearch = new JButton("Search",imgSearch);
		btnDelete = new JButton("Delete",imgDelete);
		btnUpdate = new JButton("Update",imgUpdate);
		btnClose = new JButton("Close",imgClose);
		
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
		btnClear.setToolTipText("Clear The Records");
		btnSave.setToolTipText("Save The Records");
		btnNext.setToolTipText("Go To Next Records");
		btnPrevious.setToolTipText("Go to Previous Records");
		btnSearch.setToolTipText("Search Staff ID");
		btnDelete.setToolTipText("Delete Records");
		btnUpdate.setToolTipText("Update The Records");
		btnClose.setToolTipText("Close The GUI Application");
	//Tooltip for TextField
		txtRNo.setToolTipText("Enter Reference No of Staff...");
		txtDt.setToolTipText("Enter Current Date...");
		cmbS_id.setToolTipText("Enter Staff ID...");
		txtSName.setToolTipText("Enter Staff Name...");
		txtGen.setToolTipText("Enter Staff Gender...");
		txtDept.setToolTipText("Enter Department...");
		txtDegn.setToolTipText("Enter Designation...");
		cmbLType.setToolTipText("Enter Leave Type...");
		txtFDt.setToolTipText("Enter From Which Date to Staff Leave...");
		txtTDt.setToolTipText("Enter To Which Date Staff Leave...");
		txtNar.setToolTipText("Enter Cuase for Leaving...");
	//ActionListener Adding to Buttons
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
		cmbS_id.addActionListener(this);
	//Tooltip for TextField
		txtRNo.addActionListener(this);
		txtDt.addActionListener(this);
		cmbS_id.addActionListener(this);
		txtSName.addActionListener(this);
		txtGen.addActionListener(this);
		txtDept.addActionListener(this);
		txtDegn.addActionListener(this);
		cmbLType.addActionListener(this);
		txtFDt.addActionListener(this);
		txtTDt.addActionListener(this);
		txtNar.addActionListener(this);
	
	//Vertical & Horizontal Box
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl74);
		p2.add(lRNo);
		p2.add(txtRNo);
		p2.add(lDt);
		p2.add(txtDt);
		p2.add(lS_id);
		p2.add(cmbS_id);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lSName);
		p2.add(txtSName);
		p2.add(lGen);
		p2.add(txtGen);
		p2.add(lDept);
		p2.add(txtDept);
		p2.add(lDegn);
		p2.add(txtDegn);
		p2.add(lLType);
		p2.add(cmbLType);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lFDt);
		p2.add(txtFDt);
		p2.add(lTDt);
		p2.add(txtTDt);
		p2.add(lNar);
		p2.add(txtNar);
		
		p3.setLayout(flw);
		p3.add(btnClear);
		p3.add(btnSave);
		p3.add(btnNext);
		p3.add(btnPrevious);
		p3.add(btnSearch);
		p3.add(btnDelete);
		p3.add(btnUpdate);
		p3.add(btnClose);
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p2);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p3);
		vb.add(Box.createVerticalStrut(10));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));

	//Set Frames
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		fillsID();
		f1.setBounds(120,140,1000,400);
		f1.setVisible(true);
	}
	public void fillsID()
	{
		try
		{
			stmtsID=conn.createStatement();
			rssID=stmtsID.executeQuery("select * from eiprs_tblStaffInfo");			
			while(rssID.next())
			{
				sID=rssID.getString("SID");
				cmbS_id.addItem(sID);
			}
		}
		catch(SQLException se)
		{
			//System.out.println("Unable to Create Staff ID");
			dlg.showMessageDialog(f1,"Unable to Create Staff ID","EIPRS",1);
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
			rs=stmtSelect.executeQuery("select * from eiprs_tblLeave");
		}
		catch(SQLException se)
		{
			System.out.println("Unable To Fetch Data"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnClear)
		{
			if(txtRNo.getText().length()>0)
			{
				txtRNo.setText("");
				txtDt.setText("");
				cmbS_id.setSelectedIndex(0);
				txtSName.setText("");
				txtGen.setText("");
				txtDept.setText("");
				txtDegn.setText("");
				cmbLType.setSelectedIndex(0);
				txtFDt.setText("");
				txtTDt.setText("");
				txtNar.setText("");
				dlg.showMessageDialog(f1,"Cleared The Records","TextField",2);
			}
		}
			else if(ae.getSource()==txtRNo)
			{
				txtDt.requestFocus();
			}
			else if(ae.getSource()==txtDt)
			{
				cmbS_id.requestFocus();
			}
			else if(ae.getSource()==cmbS_id)
			{
				txtSName.requestFocus();
			}
			else if(ae.getSource()==txtSName)
			{
				txtGen.requestFocus();
			}
			else if(ae.getSource()==txtGen)
			{
				txtDept.requestFocus();
			}
			else if(ae.getSource()==txtDept)
			{
				txtDegn.requestFocus();
			}
			else if(ae.getSource()==txtDegn)
			{
				cmbLType.requestFocus();
			}
			else if(ae.getSource()==cmbLType)
			{
				txtFDt.requestFocus();
			}
			else if(ae.getSource()==txtFDt)
			{
				txtTDt.requestFocus();
			}
			else if(ae.getSource()==txtTDt)
			{
				txtNar.requestFocus();
			}
			
		else if(ae.getSource()==cmbS_id && cmbS_id.getSelectedIndex()>0)//for Avoid AutoText in TextField
		{
			sID=cmbS_id.getSelectedItem().toString();
			try
			{
				stmtData=conn.createStatement();
				rsData=stmtData.executeQuery("select * from eiprs_tblstaffinfo where SID='"+sID+"'");//Table='"+String+"'
				
				rsData.next();
				
			//String=rsData.getString("Table");
				Sname=rsData.getString("Sname");
				Gender=rsData.getString("Gender");
				Department=rsData.getString("Department");
				Designation=rsData.getString("Designation");
				
				txtSName.setText(Sname);//String Name
				txtGen.setText(Gender);
				txtDept.setText(Department);
				txtDegn.setText(Designation);
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Show Data"+se);
				dlg.showMessageDialog(f1,"Unable to Show Data","EIPRS",1);
			}
			txtSName.requestFocus();//Auto Go To Next Field
		}		
		else if(ae.getSource()==btnSave)
		{
		//String Name --> TextField.getText();
			RefNo=txtRNo.getText();
			Date=txtDt.getText();
			sID=cmbS_id.getSelectedItem().toString();
			Sname=txtSName.getText();
			Gender=txtGen.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			LeaveType=cmbLType.getSelectedItem().toString();
			FromDate=txtFDt.getText();
			ToDate=txtTDt.getText();
			Narration=txtNar.getText();
			try
			{
				stmtInsert=conn.createStatement();
				stmtDupsID=conn.createStatement();
				rsDupsID=stmtDupsID.executeQuery("select * from eiprs_tblLeave where sID='"+sID+"'");
				if(rsDupsID.next() || sID.length()==0 ||  RefNo.length()==0 || Date.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || LeaveType.length()==0 || FromDate.length()==0 || ToDate.length()==0 || Narration.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate Staff ID or Blank Records","EIPRS",1);
				}
				else
				{
					stmtInsert.executeUpdate("insert into eiprs_tblLeave values ('"+RefNo+"','"+Date+"','"+sID+"','"+Sname+"','"+Gender+"','"+Department+"','"+Designation+"','"+LeaveType+"','"+FromDate+"','"+ToDate+"','"+Narration+"')");//String Name are between ('"++"')
					
					dlg.showMessageDialog(f1,"One Record Save Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable to Save Record","EIPRS",1);
				System.out.println("Unable to Save Record"+se);
			}
		}
		else if(ae.getSource()==btnNext)
		{
			try
			{
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"Last Record !","EIPRS",1);
				}
				else
				{
					rs.next();
					//String Name --> Table Name
					RefNo=rs.getString("RefNo");
					Date=rs.getString("Date");
					sID=rs.getString("sID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					LeaveType=rs.getString("LeaveType");
					FromDate=rs.getString("FromDate");
					ToDate=rs.getString("ToDate");
					Narration=rs.getString("Narration");
					
					txtRNo.setText(RefNo);
					txtDt.setText(Date);
					cmbS_id.setSelectedItem(sID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbLType.setSelectedItem(LeaveType);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNar.setText(Narration);
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable To Go Next !","EIPRS",1);
				System.out.println("Unable To Go Next !");
			}
		}
		else if(ae.getSource()==btnPrevious)
		{
			try
			{
				if(rs.isFirst())
				{
					dlg.showMessageDialog(f1,"First Record !","EIPRS",1);
				}
				else
				{
					rs.previous();
					//String Name --> Table Name
					RefNo=rs.getString("RefNo");
					Date=rs.getString("Date");
					sID=rs.getString("sID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					LeaveType=rs.getString("LeaveType");
					FromDate=rs.getString("FromDate");
					ToDate=rs.getString("ToDate");
					Narration=rs.getString("Narration");
					
					txtRNo.setText(RefNo);
					txtDt.setText(Date);
					cmbS_id.setSelectedItem(sID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbLType.setSelectedItem(LeaveType);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNar.setText(Narration);
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable To Go Previous !","EIPRS",1);
				System.out.println("Unable To Go Previous !");
			}
		}
		else if(ae.getSource()==btnSearch)
		{
			sID=dlg.showInputDialog(f1,"Enter Staff ID:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("select * from eiprs_tblLeave where sID='"+sID+"'");
				if(rsSearch.next())
				{
					//String Name --> Table Name
					RefNo=rsSearch.getString("RefNo");
					Date=rsSearch.getString("Date");
					sID=rsSearch.getString("sID");
					Sname=rsSearch.getString("Sname");
					Gender=rsSearch.getString("Gender");
					Department=rsSearch.getString("Department");
					Designation=rsSearch.getString("Designation");
					LeaveType=rsSearch.getString("LeaveType");
					FromDate=rsSearch.getString("FromDate");
					ToDate=rsSearch.getString("ToDate");
					Narration=rsSearch.getString("Narration");
					
					txtRNo.setText(RefNo);
					txtDt.setText(Date);
					cmbS_id.setSelectedItem(sID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					cmbLType.setSelectedItem(LeaveType);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNar.setText(Narration);
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable To Go Search !","EIPRS",2);
				System.out.println("Unable To Go Search !"+se);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
			Sname=txtSName.getText();//String=TextField.getText();
			try
			{
				if(txtRNo.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure To Delete...?","EIPRS",1);
					if(choice==0)//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete from eiprs_tblLeave where Sname='"+Sname+"'");
						dlg.showMessageDialog(f1,"One Record Delete Successfully","EIPRS",1);
					}
					else
					{
						dlg.showMessageDialog(f1,"Thanksgiven Data Not Delete","EIPRS",1);
					}
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable To Delete.Try Again Latter","EIPRS",1);
				System.out.println("Unable To Delete.Try Again Latter"+se);
			}
		}
		
		else if(ae.getSource()==btnUpdate)
		{
		//String Name --> TextField.getText();
			RefNo=txtRNo.getText();
			Date=txtDt.getText();
			sID=cmbS_id.getSelectedItem().toString();
			Sname=txtSName.getText();
			Gender=txtGen.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			LeaveType=cmbLType.getSelectedItem().toString();
			FromDate=txtFDt.getText();
			ToDate=txtTDt.getText();
			Narration=txtNar.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				
				if(RefNo.length()==0 || Date.length()==0 || sID.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || LeaveType.length()==0 || FromDate.length()==0 || ToDate.length()==0 || Narration.length()==0)
				{
					dlg.showMessageDialog(f1,"Blank Records Can't Update","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblLeave set RefNo='"+RefNo+"',Date='"+Date+"',sID='"+sID+"',Sname='"+Sname+"',Gender='"+Gender+"',Department='"+Department+"',Designation='"+Designation+"',LeaveType='"+LeaveType+"',FromDate='"+FromDate+"',ToDate='"+ToDate+"',Narration='"+Narration+"' where sID='"+sID+"'");
					
					dlg.showMessageDialog(f1,"One Record Updated Successfully","EIPRS",1);
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
				System.out.println("Error in Closing The Program...!");
			}
			f1.setVisible(false);
			f1.dispose();
		}
	}
}
class Eiprs_Leave
{
	public static void main(String args[])
	{
		Eiprs_LeaveApp la=new Eiprs_LeaveApp();
	}
}