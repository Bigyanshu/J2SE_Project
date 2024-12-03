import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class MenuDashWin implements ActionListener
{
	JFrame f1;
	JMenuBar Mbar;
	JMenu About,Master,Management,SummaryReport,DetailReport,Help,Exit;
	JMenuItem AboutEiprs,UserManual,ForgetPassword,Yes,No;
	Font fnt1;
	
	JMenuItem College,Department,StaffInfo;//Main File
	JMenuItem StaffAttend,StaffLeave,StaffLeaveApproval,StaffSalSlip;//Main File
	JMenuItem RCollege,RDepartment,RStaffInfo,RStaffAttend,RStaffLeave,RStaffLeaveApprove,RStaffSalSlip;//All Report

	JMenuItem RStaffDeptInfo,RStaffDegnInfo,RStaffSIDAttend,RStaffDeptAttend,RStaffDateAttend,RStaffDegnAttend,RStaffSIDLeave,RStaffDeptLeave,RStaffDegnLeave,RStaffLeaveApprovalSID,RStaffLeaveApprovalDept,RStaffLeaveApprovalDegn,RStaffLeaveApprovalDate,RStaffSalSlipSID,RStaffSalSlipDept,RStaffSalSlipDegn,RStaffSalSlipMonthYear;//Detail Report
	ImageIcon img;
	JLabel lblimg;//for image adding

	public MenuDashWin()
	{
		f1=new JFrame("Employee Information Pay Role System");
		
		Mbar=new JMenuBar();
		
		img=new ImageIcon("coll4d.jpg");
		lblimg=new JLabel(img);
		
		About=new JMenu("About");
		Master=new JMenu("Master");
		Management=new JMenu("Management");
		SummaryReport=new JMenu("Summary Report");
		DetailReport=new JMenu("Detail Report");
		Help=new JMenu("Help");
		Exit=new JMenu("Exit");
		
		fnt1=new Font("Times New Roman",Font.BOLD,16);
		About.setFont(fnt1);
		About.setForeground(Color.red);
		Master.setFont(fnt1);
		Master.setForeground(Color.red);
		Management.setFont(fnt1);
		Management.setForeground(Color.red);
		SummaryReport.setFont(fnt1);
		SummaryReport.setForeground(Color.red);
		DetailReport.setFont(fnt1);
		DetailReport.setForeground(Color.red);
		Help.setFont(fnt1);
		Help.setForeground(Color.red);
		Exit.setFont(fnt1);
		Exit.setForeground(Color.red);
		
		AboutEiprs=new JMenuItem("About Eiprs");
		UserManual=new JMenuItem("User Manual");
		ForgetPassword=new JMenuItem("Change Password");
		Yes=new JMenuItem("Yes");
		No=new JMenuItem("No");
		
	//Main File Declaration
		College=new JMenuItem("College Information");
		Department=new JMenuItem("Department Information");
		StaffInfo=new JMenuItem("Staff Information");
	//Main File
		StaffAttend=new JMenuItem("Staff Attendance");
		StaffLeave=new JMenuItem("Leave Application");
		StaffLeaveApproval=new JMenuItem("Leave Approval");
		StaffSalSlip=new JMenuItem("Salary Slip Generation");
		
	//All Report Decalration
		RCollege=new JMenuItem("College Information");
		RDepartment=new JMenuItem("Department Information");
		RStaffInfo=new JMenuItem("Staff Register");
		RStaffAttend=new JMenuItem("Attendance Register");
		RStaffLeave=new JMenuItem("Leave Resister");
		RStaffLeaveApprove=new JMenuItem("Leave Approval Resister");
		RStaffSalSlip=new JMenuItem("Salary Slip Register");
		
	//Assigning Key
		College.setAccelerator(KeyStroke.getKeyStroke("alt A"));
		Department.setAccelerator(KeyStroke.getKeyStroke("alt B"));
		StaffInfo.setAccelerator(KeyStroke.getKeyStroke("alt C"));
		StaffAttend.setAccelerator(KeyStroke.getKeyStroke("alt D"));
		StaffLeave.setAccelerator(KeyStroke.getKeyStroke("alt E"));
		StaffLeaveApproval.setAccelerator(KeyStroke.getKeyStroke("alt F"));
		StaffSalSlip.setAccelerator(KeyStroke.getKeyStroke("alt G"));
		
		RCollege.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		RDepartment.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
		RStaffInfo.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		RStaffAttend.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		RStaffLeave.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
		RStaffLeaveApprove.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		RStaffSalSlip.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		
	//Detail Report Declaration
		RStaffDeptInfo=new JMenuItem("Staff Register [Department]");
		RStaffDegnInfo=new JMenuItem("Staff Register [Designation]");
		RStaffSIDAttend=new JMenuItem("Staff Attendance [Staff ID]");
		RStaffDeptAttend=new JMenuItem("Staff Attendance [Department]");
		RStaffDateAttend=new JMenuItem("Staff Attendance [Date]");
		RStaffDegnAttend=new JMenuItem("Staff Attendance [Designation]");
		RStaffSIDLeave=new JMenuItem("Leave Application [Staff ID]");
		RStaffDeptLeave=new JMenuItem("Leave Application [Department]");
		RStaffDegnLeave=new JMenuItem("Leave Application [Designation]");
		RStaffLeaveApprovalSID=new JMenuItem("Leave Approval [Staff ID]");
		RStaffLeaveApprovalDept=new JMenuItem("Leave Approval [Department]");
		RStaffLeaveApprovalDegn=new JMenuItem("Leave Approval [Designation]");
		RStaffLeaveApprovalDate=new JMenuItem("Leave Approval [Date]");
		RStaffSalSlipSID=new JMenuItem("Salary Slip [Staff ID]");
		RStaffSalSlipDept=new JMenuItem("Salary Slip [Department]");
		RStaffSalSlipDegn=new JMenuItem("Salary Slip [Designation]");
		RStaffSalSlipMonthYear=new JMenuItem("Salary Slip [Month , Year]");
		
	//Assigning Key
		RStaffDeptInfo.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		RStaffDegnInfo.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		RStaffSIDAttend.setAccelerator(KeyStroke.getKeyStroke("ctrl J"));
		RStaffDeptAttend.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));
		RStaffDateAttend.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		RStaffDegnAttend.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
		RStaffSIDLeave.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		RStaffDeptLeave.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		RStaffDegnLeave.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		RStaffLeaveApprovalSID.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
		RStaffLeaveApprovalDept.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		RStaffLeaveApprovalDegn.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		RStaffLeaveApprovalDate.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		RStaffSalSlipSID.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		RStaffSalSlipDept.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		RStaffSalSlipDegn.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
		RStaffSalSlipMonthYear.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		
	//Action Listener in Frame Name
		About.addActionListener(this);
		Master.addActionListener(this);
		Management.addActionListener(this);
		SummaryReport.addActionListener(this);
		DetailReport.addActionListener(this);
		Help.addActionListener(this);
		Exit.addActionListener(this);
		Yes.addActionListener(this);
		ForgetPassword.addActionListener(this);
		AboutEiprs.addActionListener(this);
		UserManual.addActionListener(this);
	//Action Listener Main File
		College.addActionListener(this);
		Department.addActionListener(this);
		StaffInfo.addActionListener(this);
		StaffAttend.addActionListener(this);
		StaffLeave.addActionListener(this);
		StaffLeaveApproval.addActionListener(this);
		StaffSalSlip.addActionListener(this);
	//Action Listener All Report
		RCollege.addActionListener(this);
		RDepartment.addActionListener(this);
		RStaffInfo.addActionListener(this);
		RStaffAttend.addActionListener(this);
		RStaffLeave.addActionListener(this);
		RStaffLeaveApprove.addActionListener(this);
		RStaffSalSlip.addActionListener(this);
	//Action Listener Detail Report
		RStaffDeptInfo.addActionListener(this);
		RStaffDegnInfo.addActionListener(this);
		RStaffSIDAttend.addActionListener(this);
		RStaffDeptAttend.addActionListener(this);
		RStaffDateAttend.addActionListener(this);
		RStaffDegnAttend.addActionListener(this);
		RStaffLeave.addActionListener(this);
		RStaffSIDLeave.addActionListener(this);
		RStaffDeptLeave.addActionListener(this);
		RStaffDegnLeave.addActionListener(this);
		RStaffLeaveApprovalSID.addActionListener(this);
		RStaffLeaveApprovalDept.addActionListener(this);
		RStaffLeaveApprovalDegn.addActionListener(this);
		RStaffLeaveApprovalDate.addActionListener(this);
		RStaffSalSlip.addActionListener(this);
		RStaffSalSlipSID.addActionListener(this);
		RStaffSalSlipDept.addActionListener(this);
		RStaffSalSlipDegn.addActionListener(this);
		RStaffSalSlipMonthYear.addActionListener(this);
		
	//Set Mbar
		Mbar.add(About);
		Mbar.add(Master);
		Mbar.add(Management);
		Mbar.add(SummaryReport);
		Mbar.add(DetailReport);
		Mbar.add(Help);
		Mbar.add(Exit);
		
	//1.1
		About.add(AboutEiprs);
		
		//Assigning Window Button to Program
		About.setMnemonic('A');
		AboutEiprs.setMnemonic('A');
		
	//2.1...
		Master.add(College);
		Master.add(Department);
		Master.add(StaffInfo);
		
		//Assigning Window Button to Program
		Master.setMnemonic('2');
		College.setMnemonic('C');
		Department.setMnemonic('D');
		StaffInfo.setMnemonic('S');
		
	//3.1...
		Management.add(StaffAttend);
		Management.add(StaffLeave);
		Management.add(StaffLeaveApproval);
		Management.add(StaffSalSlip);
		
		//Assigning Window Button to Program
		Management.setMnemonic('M');
		StaffAttend.setMnemonic('A');
		StaffLeave.setMnemonic('L');
		StaffLeaveApproval.setMnemonic('3');
		StaffSalSlip.setMnemonic('S');
		
	//4.1...
		SummaryReport.add(RCollege);
		SummaryReport.add(RDepartment);
		SummaryReport.add(RStaffInfo);
		SummaryReport.add(RStaffAttend);
		SummaryReport.add(RStaffLeave);
		SummaryReport.add(RStaffLeaveApprove);
		SummaryReport.add(RStaffSalSlip);
		
		//Assigning Window Button to Program
		SummaryReport.setMnemonic('S');
		RCollege.setMnemonic('C');
		RDepartment.setMnemonic('D');
		RStaffInfo.setMnemonic('S');
		RStaffAttend.setMnemonic('A');
		RStaffLeave.setMnemonic('L');
		RStaffLeaveApprove.setMnemonic('6');
		RStaffSalSlip.setMnemonic('7');
		
	//5.1
		DetailReport.add(RStaffDeptInfo);
		DetailReport.add(RStaffDegnInfo);
		DetailReport.add(RStaffSIDAttend);
		DetailReport.add(RStaffDeptAttend);
		DetailReport.add(RStaffDateAttend);
		DetailReport.add(RStaffDegnAttend);
		DetailReport.add(RStaffSIDLeave);
		DetailReport.add(RStaffDeptLeave);
		DetailReport.add(RStaffDegnLeave);
		DetailReport.add(RStaffLeaveApprovalSID);
		DetailReport.add(RStaffLeaveApprovalDept);
		DetailReport.add(RStaffLeaveApprovalDegn);
		DetailReport.add(RStaffLeaveApprovalDate);
		DetailReport.add(RStaffSalSlipSID);
		DetailReport.add(RStaffSalSlipDept);
		DetailReport.add(RStaffSalSlipDegn);
		DetailReport.add(RStaffSalSlipMonthYear);
		
		//Assigning Window Button to Program
		DetailReport.setMnemonic('D');
		
		RStaffDeptInfo.setMnemonic('1');
		RStaffDegnInfo.setMnemonic('2');
		
		RStaffSIDAttend.setMnemonic('S');
		RStaffDeptAttend.setMnemonic('3');
		RStaffDateAttend.setMnemonic('4');
		RStaffDegnAttend.setMnemonic('5');
		
		RStaffSIDLeave.setMnemonic('L');
		RStaffDeptLeave.setMnemonic('6');
		RStaffDegnLeave.setMnemonic('7');
		
		RStaffLeaveApprovalSID.setMnemonic('Z');
		RStaffLeaveApprovalDept.setMnemonic('8');
		RStaffLeaveApprovalDegn.setMnemonic('9');
		RStaffLeaveApprovalDate.setMnemonic('0');
		
		RStaffSalSlipSID.setMnemonic('q');
		RStaffSalSlipDept.setMnemonic('w');
		RStaffSalSlipDegn.setMnemonic('e');
		RStaffSalSlipMonthYear.setMnemonic('r');
		
		//6
		Help.add(UserManual);
		Help.add(ForgetPassword);
		
		//Assigning Window Button to Program
		Help.setMnemonic('H');
		UserManual.setMnemonic('u');
		ForgetPassword.setMnemonic('f');
		
		//7
		Exit.add(Yes);
		Exit.add(No);
		
		//Assigning Window Button to Program
		Exit.setMnemonic('E');
		Yes.setMnemonic('Y');
		No.setMnemonic('N');
		
		f1.setLayout(new BorderLayout());
		f1.add(Mbar,BorderLayout.NORTH);
		f1.add(lblimg,BorderLayout.CENTER);//Picture Adding
		f1.setBounds(0,0,1400,1000);
		f1.setVisible(true);
	}//Constructor End
	public void actionPerformed(ActionEvent ae)
	{	
	//Main File
		if(ae.getSource()==College)
		{
			Eiprs_CollegeApp clg=new Eiprs_CollegeApp();
		}
		else if(ae.getSource()==Department)
		{
			Eiprs_DeptApp da=new Eiprs_DeptApp();
		}
		else if(ae.getSource()==StaffInfo)
		{
			Eiprs_StaffApp si=new Eiprs_StaffApp();
		}
		else if(ae.getSource()==StaffAttend)
		{
			StaffAttendApp sa=new StaffAttendApp();
		}
		else if(ae.getSource()==StaffLeave)
		{
			Eiprs_LeaveApp sla=new Eiprs_LeaveApp();
		}
		else if(ae.getSource()==StaffLeaveApproval)
		{
			Eiprs_LeaveApprovApp slaa=new Eiprs_LeaveApprovApp();
		}
		else if(ae.getSource()==StaffSalSlip)
		{
			Eiprs_SalSlipApp sss=new Eiprs_SalSlipApp();
		}
	//All Report 
		else if(ae.getSource()==RCollege)
		{
			RptCollegeApp rc=new RptCollegeApp();
		}
		else if(ae.getSource()==RDepartment)
		{
			RptDepartmentApp rd= new RptDepartmentApp();
		}
		else if(ae.getSource()==RStaffInfo)
		{
			RptStaffInfoApp rsa=new RptStaffInfoApp();
		}
		else if(ae.getSource()==RStaffAttend)
		{
			RptStaffAttendApp vd=new RptStaffAttendApp();
		}
		else if(ae.getSource()==RStaffLeave)
		{
			RptStaffLeaveApp nx=new RptStaffLeaveApp();
		}
		else if(ae.getSource()==RStaffLeaveApprove)
		{
			RptLeaveApprovalApp nm=new  RptLeaveApprovalApp();
		}
		else if(ae.getSource()==RStaffSalSlip)
		{
			RptSalSlipApp rf=new RptSalSlipApp();
		}
	//Detail Report 
		else if(ae.getSource()==RStaffDeptInfo)
		{
			RptStaffDeptInfoApp as=new RptStaffDeptInfoApp();
		}
		else if(ae.getSource()==RStaffDegnInfo)
		{
			RptStaffDegnInfoApp nb=new RptStaffDegnInfoApp();
		}
		else if(ae.getSource()==RStaffSIDAttend)
		{
			RptStaffSIDAttendApp bv=new RptStaffSIDAttendApp();
		}
		else if(ae.getSource()==RStaffDeptAttend)
		{
			RptStaffDeptAttendApp bc=new RptStaffDeptAttendApp();
		}
		else if(ae.getSource()==RStaffDateAttend)
		{
			RptStaffDateAttendApp gh=new RptStaffDateAttendApp();
		}
		else if(ae.getSource()==RStaffDegnAttend)
		{
			RptStaffDegnAttendApp xc=new RptStaffDegnAttendApp();
		}
		else if(ae.getSource()==RStaffSIDLeave)
		{
			RptStaffSIDLeaveApp mo=new RptStaffSIDLeaveApp();
		}
		else if(ae.getSource()==RStaffDeptLeave)
		{
			RptStaffDeptLeaveApp hu=new RptStaffDeptLeaveApp();
		}
		else if(ae.getSource()==RStaffDegnLeave)
		{
			RptStaffDegnLeaveApp joi=new RptStaffDegnLeaveApp();
		}
		else if(ae.getSource()==RStaffLeaveApprovalSID)
		{
			RptLeaveApprovalSIDApp ty=new RptLeaveApprovalSIDApp();
		}
		else if(ae.getSource()==RStaffLeaveApprovalDept)
		{
			RptLeaveApprovalDeptApp es=new RptLeaveApprovalDeptApp();
		}
		else if(ae.getSource()==RStaffLeaveApprovalDegn)
		{
			RptLeaveApprovalDegnApp yt=new RptLeaveApprovalDegnApp();
		}
		else if(ae.getSource()==RStaffLeaveApprovalDate)
		{	
			RptLeaveApprovalDateApp gm=new RptLeaveApprovalDateApp();
		}
		else if(ae.getSource()==RStaffSalSlipSID)
		{
			RptSalSlipSIDApp zo=new RptSalSlipSIDApp();
		}
		else if(ae.getSource()==RStaffSalSlipDept)
		{
			RptSalSlipDeptApp fg=new RptSalSlipDeptApp();
		}
		else if(ae.getSource()==RStaffSalSlipDegn)
		{
			RptSalSlipDegnApp to=new RptSalSlipDegnApp();
		}
		else if(ae.getSource()==RStaffSalSlipMonthYear)
		{
			RptSalSlipMonthYearApp fp=new RptSalSlipMonthYearApp();
		}
		else if(ae.getSource()==Yes)
		{
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==ForgetPassword)
		{
			Eiprs_ResetPasswordApp aw=new Eiprs_ResetPasswordApp();
		}
		else if(ae.getSource()==AboutEiprs)
		{
			Eiprs_AboutApp sd=new Eiprs_AboutApp();
		}
		else if(ae.getSource()==UserManual)
		{
			Eiprs_UserManualApp oi=new Eiprs_UserManualApp();
		}
	}
}
class MenuDashApp{
	public static void main(String args[]){
			MenuDashWin Menu=new MenuDashWin();
	}
}