import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

 class Eiprs_LeaveApprovApp implements ActionListener
 {
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl31,gl94;
	FlowLayout flw;
	JLabel lHead,lSNo,lDat,lRefCode,lDt,lSID,lSName,lGen,lDept,lDegn,lLType,lFDt,lTDt,lNarr,lSts;
	JTextField txtSNo,txtDat,txtDt,txtSID,txtSName,txtGen,txtDept,txtDegn,txtLType,txtFDt,txtTDt,txtNarr;
	JComboBox cmbRefCode,cmbSts;
	JButton btnClear, btnSave, btnNext, btnPrevious, btnUpdate, btnClose,btnSearch, btnDelete;
	Box vb,hb;
	Font fnt1,fnt2;
	ImageIcon imgClear,imgSave,imgNext,imgPrevious,imgUpdate,imgSearch,imgDelete,imgClose;
	
	Connection conn;
	Statement stmtSelect,stmtInsert,stmtData,stmtDupSNo,stmtUpdate,stmtSearch,stmtDelete,stmtRefC;
	ResultSet rs,rsData,rsDupSNo,rsSearch,rsRefC;
	JOptionPane dlg;
	int choice;
	String Serial,Date1,ReferenceC,Date2,Staff_ID,Sname,Gender,Department,Designation,LeaveType,FromDate,ToDate,Narration,Status;
	//Table Name(SerialNo,Date1,ReferenceCode,Date2,StaffID,StaffName,Gender,Department,Designation,LeaveType,FromDate,ToDate,Narration,Status)

	public Eiprs_LeaveApprovApp()
	{
		f1= new JFrame("BPCTM Staff Leave Approval Register");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		gl31=new GridLayout(3,1);
		gl94=new GridLayout(9,4);
		
		flw=new FlowLayout();
		
	//Label Decalred	
		lHead=new JLabel("Leave  Approval");
		//fnt2=new Font("Copperplate Gothic Bold",Font.ITALIC,30);
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,30);
		lHead.setFont(fnt1);
		lHead.setFont(fnt2);
		lHead.setForeground(Color.black);
		//p1.setBackground(Color.cyan);
		
		lSNo=new JLabel("Serial No:");
		lDat=new JLabel("    Date:");
		lRefCode=new JLabel("Reference Code:");
		lDt=new JLabel("    Date:");
		lSID=new JLabel("Staff ID:");
		lSName=new JLabel("Staff Name:");
		lGen=new JLabel("    Gender:");
		lDept=new JLabel("Department:");
		lDegn=new JLabel("    Designation:");
		lLType=new JLabel("Leave Type:");
		lFDt=new JLabel("From Date:");
		lTDt=new JLabel("    To Date:");
		lNarr=new JLabel("Narration:");
		lSts=new JLabel("Status:");
	
	//TextField Declared
		txtSNo=new JTextField(20);
		txtDat=new JTextField(20);
		txtDt=new JTextField(20);
		txtSID=new JTextField(30);
		txtSName=new JTextField(30);
		txtGen=new JTextField(30);
		txtDept=new JTextField(30);
		txtDegn=new JTextField(30);
		txtLType=new JTextField(30);
		txtFDt=new JTextField(30);
		txtTDt=new JTextField(30);
		txtNarr=new JTextField(50);
	
	//ComboBox Declared
		cmbRefCode=new JComboBox();
		cmbRefCode.addItem("-Select Reference Code-");
		
		cmbSts=new JComboBox();
		cmbSts.addItem("-Select Status-");
		cmbSts.addItem("Approved");
		cmbSts.addItem("Cancelled");
		
	//ImageIcon Declared
		imgClear=new ImageIcon("JClear1.png");//3
		imgSave=new ImageIcon("JSave.png");//2
		imgDelete=new ImageIcon("JDelete.png");//2
		imgNext=new ImageIcon("JNext.png");//
		imgPrevious=new ImageIcon("JPrevious.png");//
		imgClose=new ImageIcon("IClose1.png");
		imgSearch=new ImageIcon("ISearch.png");
		imgUpdate=new ImageIcon("IEdit1.png");
		
	//Button Declared	
		btnClear=new JButton("Clear",imgClear);
		btnSave=new JButton("Save",imgSave);
		btnNext=new JButton("Next",imgNext);
		btnPrevious=new JButton("Previous",imgPrevious);
		btnUpdate=new JButton("Update",imgUpdate);
		btnSearch=new JButton("Search",imgSearch);
		btnDelete=new JButton("Delete",imgDelete);
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
		
	//Adding ToolTip
		btnClear.setToolTipText("Clear The Records");
		btnSave.setToolTipText("Save The Records");
		btnNext.setToolTipText("Go To Next Records");
		btnPrevious.setToolTipText("Go to Previous Records");
		btnSearch.setToolTipText("Search Staff ID");
		btnDelete.setToolTipText("Delete Records");
		btnUpdate.setToolTipText("Update The Records");
		btnClose.setToolTipText("Close The GUI Application");
	//Tooltip for TextField
		txtSNo.setToolTipText("Enter Serial No of Staff...");
		txtDat.setToolTipText("Enter Current Date...");
		txtDt.setToolTipText("Enter Date...");
		txtSID.setToolTipText("Enter Staff ID...");
		txtSName.setToolTipText("Enter Staff Name...");
		txtGen.setToolTipText("Enter Staff Gender...");
		txtDept.setToolTipText("Enter Department of Staff...");
		txtDegn.setToolTipText("Enter Designation of Staff...");
		txtLType.setToolTipText("Enter Leave Type...");
		txtFDt.setToolTipText("Enter Enter From Which Date to Staff Leave...");
		txtTDt.setToolTipText("Enter To Which Date Staff Leave...");
		txtNarr.setToolTipText("Enter Cuase for Leaving...");
		cmbRefCode.setToolTipText("Enter Reference No of Staff...");
		cmbSts.setToolTipText("Enter Status of Staff...");
		
	//ActionListener Adding to Buttons
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
		cmbRefCode.addActionListener(this);
	//Tooltip for TextField
		txtSNo.addActionListener(this);
		txtDat.addActionListener(this);
		txtDt.addActionListener(this);
		txtSID.addActionListener(this);
		txtSName.addActionListener(this);
		txtGen.addActionListener(this);
		txtDept.addActionListener(this);
		txtDegn.addActionListener(this);
		txtLType.addActionListener(this);
		txtFDt.addActionListener(this);
		txtTDt.addActionListener(this);
		txtNarr.addActionListener(this);
		cmbRefCode.addActionListener(this);
		cmbSts.addActionListener(this);
	
	//V & H Box
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl94);
		p2.add(lSNo);
		p2.add(txtSNo);
		p2.add(lDat);
		p2.add(txtDat);
		p2.add(lRefCode);
		p2.add(cmbRefCode);
		p2.add(lDt);
		p2.add(txtDt);
		p2.add(lSID);
		p2.add(txtSID);
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
		p2.add(txtLType);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lFDt);
		p2.add(txtFDt);
		p2.add(lTDt);
		p2.add(txtTDt);
		p2.add(lNarr);
		p2.add(txtNarr);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
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
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p3);
		vb.add(Box.createVerticalStrut(20));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//Set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		fillReferenceC();
		f1.setBounds(35,120,1200,500);
		f1.setVisible(true);
	}
	public void fillReferenceC()
	{
		try
		{
			stmtData=conn.createStatement();
			rsData=stmtData.executeQuery("Select * from eiprs_tblLeave");
			while(rsData.next())
			{
				ReferenceC=rsData.getString("RefNo");//(Cur prog String)ReferenceC=rsData.getString(Leave Table "String Name");
				cmbRefCode.addItem(ReferenceC);
			}
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fill"+se);
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
			System.out.println("Unable To Load Driver");
		}
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/eiprsdb","root","root1");
			stmtSelect=conn.createStatement();
			rs=stmtSelect.executeQuery("select * from eiprs_tblLeaveApprov");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnClear)
		{
			txtSNo.setText("");
			txtDat.setText("");
			cmbRefCode.setSelectedIndex(0);
			//cmbRefCode.setSelectedItem("");
			txtDt.setText("");
			txtSID.setText("");
			txtSName.setText("");
			txtGen.setText("");
			txtDept.setText("");
			txtDegn.setText("");
			txtLType.setText("");
			txtFDt.setText("");
			txtTDt.setText("");
			txtNarr.setText("");
			cmbSts.setSelectedItem("");
		}
		else if(ae.getSource()==cmbRefCode && cmbRefCode.getSelectedIndex()>0)// && if 0th position Any Text Can't place in TextField 
		{
			ReferenceC=cmbRefCode.getSelectedItem().toString();
			try
			{
				stmtData=conn.createStatement();
				rsData=stmtData.executeQuery("Select * from eiprs_tblLeave where RefNo='"+ReferenceC+"'");//Previous Prog(Leave) String='"+Current Prog(LeaveApprov) String+"'
				
				rsData.next();
				
			//String=rsData.getString("Table");
				Date2=rsData.getString("Date");
				Staff_ID=rsData.getString("sID");
				Sname=rsData.getString("Sname");
				Gender=rsData.getString("Gender");
				Department=rsData.getString("Department");
				Designation=rsData.getString("Designation");
				LeaveType=rsData.getString("LeaveType");
				FromDate=rsData.getString("FromDate");
				ToDate=rsData.getString("ToDate");
				Narration=rsData.getString("Narration");
				
			//For visible in TextField we use setText(String);
				txtDt.setText(Date2);
				txtSID.setText(Staff_ID);
				txtSName.setText(Sname);
				txtGen.setText(Gender);
				txtDept.setText(Department);
				txtDegn.setText(Designation);
				txtLType.setText(LeaveType);
				txtFDt.setText(FromDate);
				txtTDt.setText(ToDate);
				txtNarr.setText(Narration);
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Show Data."+se);
				dlg.showMessageDialog(f1,"Unbale To Show Data.","EIPRS",1);
			}
			txtDt.requestFocus();//Auot goto Next Field
		}
		else if(ae.getSource()==btnSave)
		{
		//String Name --> TextField.getText();
			Serial=txtSNo.getText();
			Date1=txtDat.getText();
			ReferenceC=cmbRefCode.getSelectedItem().toString();
			Date2=txtDt.getText();
			Staff_ID=txtSID.getText();
			Sname=txtSName.getText();
			Gender=txtGen.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			LeaveType=txtLType.getText();
			FromDate=txtFDt.getText();
			ToDate=txtTDt.getText();
			Narration=txtNarr.getText();
			Status=cmbSts.getSelectedItem().toString();
			
			try
			{
				stmtInsert=conn.createStatement();
				stmtDupSNo=conn.createStatement();
				rsDupSNo=stmtDupSNo.executeQuery("Select * from eiprs_tblLeaveApprov where SerialNo='"+Serial+"'");//Table='"+String+"'
				if(rsDupSNo.next() || Serial.length()==0 || Date1.length()==0 || ReferenceC.length()==0 || Date2.length()==0 || Staff_ID.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || LeaveType.length()==0 || FromDate.length()==0 || ToDate.length()==0 || Narration.length()==0 || Status.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate or Blank Records. Unbale to Save","EIPRS",1);
				}
				else 
				{
					stmtInsert.executeUpdate("Insert Into eiprs_tblLeaveApprov values ('"+Serial+"','"+Date1+"','"+ReferenceC+"','"+Date2+"','"+Staff_ID+"','"+Sname+"','"+Gender+"','"+Department+"','"+Designation+"','"+LeaveType+"','"+FromDate+"','"+ToDate+"','"+Narration+"','"+Status+"')");
					
					dlg.showMessageDialog(f1,"One Record Save Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Save Records...!"+se);
				dlg.showMessageDialog(f1,"Unbale To Save Records...!","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnNext)
		{
			try
			{
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"Unbale To Go Next ! This is Last Record","EIPRS",1);
				}
				else
				{
					rs.next();
				//String Name --> "Table Name"
					Serial=rs.getString("SerialNo");
					Date1=rs.getString("Date1");
					ReferenceC=rs.getString("ReferenceCode");
					Date2=rs.getString("Date2");
					Staff_ID=rs.getString("StaffID");
					Sname=rs.getString("StaffName");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					LeaveType=rs.getString("LeaveType");
					FromDate=rs.getString("FromDate");
					ToDate=rs.getString("ToDate");
					Narration=rs.getString("Narration");
					Status=rs.getString("Status");
					
				//TextField --> Table Name
					txtSNo.setText(Serial);
					txtDat.setText(Date1);
					cmbRefCode.setSelectedItem(ReferenceC);
					txtDt.setText(Date2);
					txtSID.setText(Staff_ID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNarr.setText(Narration);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Go Next !"+se);
				dlg.showMessageDialog(f1,"Unbale To Go Next !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnPrevious)
		{
			try
			{
				if(rs.isFirst())
				{
					dlg.showMessageDialog(f1,"Unbale To Go Prevoius ! This is First Record","EIPRS",1);
				}
				else
				{
					rs.previous();
				//String Name --> rs.getString("Table Name");
					Serial=rs.getString("SerialNo");
					Date1=rs.getString("Date1");
					ReferenceC=rs.getString("ReferenceCode");
					Date2=rs.getString("Date2");
					Staff_ID=rs.getString("StaffID");
					Sname=rs.getString("StaffName");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					LeaveType=rs.getString("LeaveType");
					FromDate=rs.getString("FromDate");
					ToDate=rs.getString("ToDate");
					Narration=rs.getString("Narration");
					Status=rs.getString("Status");
					
				//TextField --> Table Name
					txtSNo.setText(Serial);
					txtDat.setText(Date1);
					cmbRefCode.setSelectedItem(ReferenceC);
					txtDt.setText(Date2);
					txtSID.setText(Staff_ID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNarr.setText(Narration);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Go Previous !"+se);
				dlg.showMessageDialog(f1,"Unbale To Go Previous !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnSearch)
		{
			Serial=dlg.showInputDialog(f1,"Enter Serial No:","EIPRS",0);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("Select * from eiprs_tblLeaveApprov where SerialNo='"+Serial+"'");//Table Namae='"+String Name+"'
				if(rsSearch.next())
				{
				//String Name=rsSearch.getString("Table Name");
					Serial=rsSearch.getString("SerialNo");
					Date1=rsSearch.getString("Date1");
					ReferenceC=rsSearch.getString("ReferenceCode");
					Date2=rsSearch.getString("Date2");
					Staff_ID=rsSearch.getString("StaffID");
					Sname=rs.getString("StaffName");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					LeaveType=rs.getString("LeaveType");
					FromDate=rs.getString("FromDate");
					ToDate=rs.getString("ToDate");
					Narration=rsSearch.getString("Narration");
					Status=rsSearch.getString("Status");
					
				//TextField.setText(String Name);
					txtSNo.setText(Serial);
					txtDat.setText(Date1);
					cmbRefCode.setSelectedItem(ReferenceC);
					txtDt.setText(Date2);
					txtSID.setText(Staff_ID);
					txtSName.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtFDt.setText(FromDate);
					txtTDt.setText(ToDate);
					txtNarr.setText(Narration);
					cmbSts.setSelectedItem(Status);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Search !"+se);
				dlg.showMessageDialog(f1,"Unbale To Search !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnUpdate)
		{
		//String Name --> TextField.getText();
			Serial=txtSNo.getText();
			Date1=txtDat.getText();
			ReferenceC=cmbRefCode.getSelectedItem().toString();
			Date2=txtDt.getText();
			Staff_ID=txtSID.getText();
			Sname=txtSName.getText();
			Gender=txtGen.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			LeaveType=txtLType.getText();
			FromDate=txtFDt.getText();
			ToDate=txtTDt.getText();
			Narration=txtNarr.getText();
			Status=cmbSts.getSelectedItem().toString();
			
			try
			{
				stmtUpdate=conn.createStatement();
				if(Serial.length()==0 || Date1.length()==0 || ReferenceC.length()==0 || Date2.length()==0 || Staff_ID.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || LeaveType.length()==0 || FromDate.length()==0 || ToDate.length()==0 || Narration.length()==0 || Status.length()==0)
				{
					dlg.showMessageDialog(f1,"Unbale To Update Due To Blank Records","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblLeaveApprov set Date1='"+Date1+"',ReferenceCode='"+ReferenceC+"',Date2='"+Date2+"',StaffID='"+Staff_ID+"',StaffName='"+Sname+"',Gender='"+Gender+"',Department='"+Department+"',Designation='"+Designation+"',LeaveType='"+LeaveType+"',FromDate='"+FromDate+"',ToDate='"+ToDate+"',Narration='"+Narration+"',Status='"+Status+"' where SerialNo='"+Serial+"'");//Table='"+String+"'  where Table='"+String+"');
					
					dlg.showMessageDialog(f1,"One Record Update Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Update !"+se);
				dlg.showMessageDialog(f1,"Unbale To Update !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
		//String=TextField.getText();
			Serial=txtSNo.getText();
			try
			{
				if(txtSNo.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure To Delete...?","Alert",2);
					if(choice==0)//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete From eiprs_tblLeaveApprov where SerialNo='"+Serial+"'");
						dlg.showMessageDialog(f1,"One Record Delete Successfully","EIPRS",1);
					}
					else
					{
						dlg.showMessageDialog(f1,"Thankful Data not Delete","EIPRS",1);
					}
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Delete ! Try Agin Latter."+se);
				dlg.showMessageDialog(f1,"Unbale To Delete ! Try Again Latter.","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnClose)
		{
			dlg.showMessageDialog(f1,"Sure to Close Program...?","EIPRS",1);
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
 class Eiprs_LeaveApproval
 {
	public static void main(String s[])
	{
		Eiprs_LeaveApprovApp la=new Eiprs_LeaveApprovApp();
	}
 }