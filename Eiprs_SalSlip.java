import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class Eiprs_SalSlipApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4,p5;
	GridLayout gl51,gl64,gl28,gl14;
	FlowLayout flw;
	JLabel lHead,lSlipN,lDt,lMon,lYr,lSiD,lSN,lGen,lDept,lDegn,lBSal,lDa,lAmt1,lPf,lAmt2,lHra,lAmt3,lTax,lAmt4,		lGSal,lNSal;
	JTextField txtSlipN,txtDt,txtSN,txtGen,txtDept,txtDegn,txtBSal,txtDa,txtAmt1,txtPf,txtAmt2,txtHra,txtAmt3,		txtTax,txtAmt4,txtGSal,txtNSal;
	JComboBox cmbMon,cmbYr,cmbSiD;
	JButton btnClear, btnSave, btnNext, btnPrevious, btnUpdate, btnClose,btnSearch, btnDelete;
	Box vb,hb;
	Font fnt1;
	ImageIcon imgClear,imgSave,imgNext,imgPrevious,imgUpdate,imgSearch,imgDelete,imgClose;
	
	Connection conn;
	int choice;
	JOptionPane dlg;
	ResultSet rs,rsSearch,rsData,rsDupSlipNo,rsfillSID;
	Statement stmtInsert,stmtSelect,stmtData,stmtSearch,stmtDelete,stmtUpdate,stmtDupSlipNo,stmtfillSID;
	
	float BSalary1,da1,daamt,pf1,pfamt,hra1,hraamt,tax1,taxamt,gross1,grossamt,net1;
	
	String SlipNo,Date,Month,Year,StaffID,Sname,Gender,Department,Designation,BSalary,Da,Amount1,Pf,Amount2,Hra,	Amount3,Tax,Amount4,GrossSal,NetSal;
	//Table(SlipNum,Date,MonthOf,,Year,Staff_ID,StaffName,Gender,Department,Designation,BasicSalary,Da,Amount1,		Pf,Amount2,Hra,Amount3,Tax,Amount4,GrossSalary,NetSalary)
	
//Constructor Begin
	public Eiprs_SalSlipApp()
	{
		f1=new JFrame("BPCTM Staff Salary Register");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		
		gl51=new GridLayout(5,1);
		gl64=new GridLayout(6,4);
		gl28=new GridLayout(2,8);
		gl14=new GridLayout(1,4);
		
		flw=new FlowLayout();
		
	//Label Declared
		lHead=new JLabel("Salary Slip Preparation");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,30);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		p1.setBackground(Color.white);
		
		lSlipN=new JLabel("Slip No:");
		lDt=new JLabel("    Date:");
		lMon=new JLabel("Month Of:");
		lYr=new JLabel("    Year:");
		lSiD=new JLabel("Staff ID:");
		lSN=new JLabel("Staff Name:");
		lGen=new JLabel("    Gender:");
		lDept=new JLabel("Department:");
		lDegn=new JLabel("    Designation:");
		lBSal=new JLabel("Basic Salary:");
		lDa=new JLabel("DA %ge:");
		lAmt1=new JLabel("    Amount:");
		lPf=new JLabel("    PF %ge:");
		lAmt2=new JLabel("    Amount:");
		lHra=new JLabel("HRA %ge:");
		lAmt3=new JLabel("    Amount:");
		lTax=new JLabel("    TAX %ge:");
		lAmt4=new JLabel("    Amount:");
		lGSal=new JLabel("Gross Salary:");
		lNSal=new JLabel("    Net Salary:");
		
	//TextField Declared
		txtSlipN=new JTextField(50);
		txtDt=new JTextField(50);
		txtSN=new JTextField(50);
		txtGen=new JTextField(50);
		txtDept=new JTextField(50);
		txtDegn=new JTextField(50);
		txtBSal=new JTextField(50);
		txtDa=new JTextField(50);
		txtAmt1=new JTextField(50);
		txtPf=new JTextField(50);
		txtAmt2=new JTextField(50);
		txtHra=new JTextField(50);
		txtAmt3=new JTextField(50);
		txtTax=new JTextField(50);
		txtAmt4=new JTextField(50);
		txtGSal=new JTextField(50);
		txtNSal=new JTextField(50);
		
	//ComboBox Declared & Add
		cmbMon=new JComboBox();
		cmbMon.addItem("-Select Month-");
		cmbMon.addItem("January");
		cmbMon.addItem("February");
		cmbMon.addItem("March");
		cmbMon.addItem("April");
		cmbMon.addItem("May");
		cmbMon.addItem("June");
		cmbMon.addItem("July");
		cmbMon.addItem("August");
		cmbMon.addItem("September");
		cmbMon.addItem("October");
		cmbMon.addItem("November");
		cmbMon.addItem("December");
		
		cmbYr=new JComboBox();
		cmbYr.addItem("-Select Year-");
		cmbYr.addItem("2001");
		cmbYr.addItem("2002");
		cmbYr.addItem("2003");
		cmbYr.addItem("2004");
		cmbYr.addItem("2005");
		cmbYr.addItem("2006");
		cmbYr.addItem("2007");
		cmbYr.addItem("2008");
		cmbYr.addItem("2009");
		cmbYr.addItem("2010");
		cmbYr.addItem("2011");
		cmbYr.addItem("2012");
		cmbYr.addItem("2013");
		cmbYr.addItem("2014");
		cmbYr.addItem("2015");
		cmbYr.addItem("2016");
		cmbYr.addItem("2017");
		cmbYr.addItem("2018");
		cmbYr.addItem("2019");
		cmbYr.addItem("2020");
		cmbYr.addItem("2021");
		cmbYr.addItem("2022");
		cmbYr.addItem("2023");
		cmbYr.addItem("2024");
		cmbYr.addItem("2025");
		cmbYr.addItem("2026");
		
		cmbSiD=new JComboBox();
		cmbSiD.addItem("-Select Staff ID-");
	
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
		
	//Adding ToolTip in Button
		btnClear.setToolTipText("Clear The Records");
		btnSave.setToolTipText("Save The Records");
		btnNext.setToolTipText("Go To Next Records");
		btnPrevious.setToolTipText("Go to Previous Records");
		btnSearch.setToolTipText("Search Staff ID");
		btnDelete.setToolTipText("Delete Records");
		btnUpdate.setToolTipText("Update The Records");
		btnClose.setToolTipText("Close The GUI Application");
	//Tooltip Adding in TextField
		txtSlipN.setToolTipText("Enter Slip No. of Staff");
		txtDt.setToolTipText("Enter Date");
		txtSN.setToolTipText("Enter Staff Name");
		txtGen.setToolTipText("Enter Gender of Staff");
		txtDept.setToolTipText("Enter Department of Staff");
		txtDegn.setToolTipText("Enter Designation of Staff");
		txtBSal.setToolTipText("Enter Basic Salary of Staff");
		txtDa.setToolTipText("Enter DA ");
		txtAmt1.setToolTipText("DA Amount Auto Filled");
		txtPf.setToolTipText("Enter PF");
		txtAmt2.setToolTipText("PF Amount Auto Filled");
		txtHra.setToolTipText("Enter HRA");
		txtAmt3.setToolTipText("HRA Amount Auto Filled");
		txtTax.setToolTipText("Enter Tax");
		txtAmt4.setToolTipText("Tax Amount Auto Filled");
		txtGSal.setToolTipText("Auto Fill Gross Salary");
		txtNSal.setToolTipText("Auto Fill Net Salary");
	//Adding ActionListener to Button
		btnClear.addActionListener(this);
		btnClose.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		cmbSiD.addActionListener(this);
	//Adding ActionListener to TextField
		txtSlipN.addActionListener(this);
		txtDt.addActionListener(this);
		txtSN.addActionListener(this);
		txtGen.addActionListener(this);
		txtDept.addActionListener(this);
		txtDegn.addActionListener(this);
		txtBSal.addActionListener(this);
		txtDa.addActionListener(this);
		txtAmt1.addActionListener(this);
		txtPf.addActionListener(this);
		txtAmt2.addActionListener(this);
		txtHra.addActionListener(this);
		txtAmt3.addActionListener(this);
		txtTax.addActionListener(this);
		txtAmt4.addActionListener(this);
		txtGSal.addActionListener(this);
		txtNSal.addActionListener(this);
	//V & H Box
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(flw);
		p1.add(lHead);
		
		p2.setLayout(gl64);
		p2.add(lSlipN);
		p2.add(txtSlipN);
		p2.add(lDt);
		p2.add(txtDt);
		p2.add(lMon);
		p2.add(cmbMon);
		p2.add(lYr);
		p2.add(cmbYr);
		p2.add(lSiD);
		p2.add(cmbSiD);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lSN);
		p2.add(txtSN);
		p2.add(lGen);
		p2.add(txtGen);
		p2.add(lDept);
		p2.add(txtDept);
		p2.add(lDegn);
		p2.add(txtDegn);
		p2.add(lBSal);
		p2.add(txtBSal);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		
		p3.setLayout(gl28);
		p3.add(lDa);
		p3.add(txtDa);
		p3.add(lAmt1);
		p3.add(txtAmt1);
		p3.add(lPf);
		p3.add(txtPf);
		p3.add(lAmt2);
		p3.add(txtAmt2);
		p3.add(lHra);
		p3.add(txtHra);
		p3.add(lAmt3);
		p3.add(txtAmt3);
		p3.add(lTax);
		p3.add(txtTax);
		p3.add(lAmt4);
		p3.add(txtAmt4);
		
		p5.setLayout(gl14);
		p5.add(lGSal);
		p5.add(txtGSal);
		p5.add(lNSal);
		p5.add(txtNSal);
		
		p4.setLayout(flw);
		p4.add(btnClear);
		p4.add(btnSave);
		p4.add(btnNext);
		p4.add(btnPrevious);
		p4.add(btnSearch);
		p4.add(btnDelete);
		p4.add(btnUpdate);
		p4.add(btnClose);
		
		vb.add(Box.createVerticalStrut(05));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(p3);
		vb.add(p5);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p4);
		vb.add(Box.createVerticalStrut(20));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		doConnect();
		fillSID();
		f1.setBounds(40,80,1200,600);
		f1.setVisible(true);
	}
	public void fillSID()
	{
		try
		{
			stmtData=conn.createStatement();
			rsData=stmtData.executeQuery("select * from eiprs_tblStaffInfo");
			while(rsData.next())
			{
				StaffID=rsData.getString("SID");//String=rsData.getString("LeavePrroval String Name");
				cmbSiD.addItem(StaffID);
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
			rs=stmtSelect.executeQuery("Select * from eiprs_tblsalslip");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==cmbSiD && cmbSiD.getSelectedIndex()>0)
		{
			StaffID=cmbSiD.getSelectedItem().toString();
			try
			{
				stmtData=conn.createStatement();
				rsData=stmtData.executeQuery("Select * from Eiprs_tblStaffInfo where SID='"+StaffID+"'");//Previous Prog(LeavePrroval) String='"+Current Prog(SalSlip) String+"'
				rsData.next();
				
			//String=rsData.getString("Table");
				Sname=rsData.getString("Sname");
				Gender=rsData.getString("Gender");
				Department=rsData.getString("Department");
				Designation=rsData.getString("Designation");
				BSalary=rsData.getString("Bsalary");
				Da=rsData.getString("DA");
				Pf=rsData.getString("PF");
				Hra=rsData.getString("HRA");
				Tax=rsData.getString("TAX");
				
				BSalary1=Float.parseFloat(BSalary);
				
				da1=Float.parseFloat(Da);
				daamt=BSalary1*da1/100;
				//System.out.println("DA Amount="+daamt);
				
				pf1=Float.parseFloat(Pf);
				pfamt=BSalary1*pf1/100;
				//System.out.println("PF Amount="+pfamt);
				
				hra1=Float.parseFloat(Hra);
				hraamt=BSalary1*hra1/100;
				//System.out.println("HRA Amount="+hraamt);
				
				tax1=Float.parseFloat(Tax);
				taxamt=BSalary1*tax1/100;
				//System.out.println("TAX Amount="+taxamt);
				
				gross1=(BSalary1+daamt+hraamt);
				
				net1=gross1-(pfamt+taxamt);
				
				txtSN.setText(Sname);
				txtGen.setText(Gender);
				txtDept.setText(Department);
				txtDegn.setText(Designation);
				txtBSal.setText(BSalary);
				txtDa.setText(Da);
				txtAmt1.setText(String.valueOf(daamt));
				txtPf.setText(Pf);
				txtAmt2.setText(String.valueOf(pfamt));
				txtHra.setText(Hra);
				txtAmt3.setText(String.valueOf(hraamt));
				txtTax.setText(Tax);
				txtAmt4.setText(String.valueOf(taxamt));
				txtGSal.setText(String.valueOf(gross1));
				txtNSal.setText(String.valueOf(net1));
				
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Show Data."+se);
				dlg.showMessageDialog(f1,"Unbale To Show Data.","EIPRS",1);
			}
			txtSN.requestFocus();//Auto GOTO NextField
		}
		else if(ae.getSource()==btnClose)
		{
			dlg.showMessageDialog(f1,"Sure To Close Programe...?","Alert",2);
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
		else if(ae.getSource()==btnClear)
		{
			if(txtSlipN.getText().length()>0)
			{
				txtSlipN.setText("");
				txtDt.setText("");
				cmbMon.setSelectedItem("");
				cmbYr.setSelectedItem("");
				cmbSiD.setSelectedItem("");
				txtSN.setText("");
				txtGen.setText("");
				txtDept.setText("");
				txtDegn.setText("");
				txtBSal.setText("");
				txtDa.setText("");
				txtAmt1.setText("");
				txtPf.setText("");
				txtAmt2.setText("");
				txtHra.setText("");
				txtAmt3.setText("");
				txtTax.setText("");
				txtAmt4.setText("");
				txtGSal.setText("");
				txtNSal.setText("");
				dlg.showMessageDialog(f1,"Cleared The Data","TextField",1);
				txtSlipN.requestFocus();
			}
		}
			else if(ae.getSource()==txtSlipN)
			{
				txtDt.requestFocus();
			}
			else if(ae.getSource()==txtDt)
			{
				cmbMon.requestFocus();
			}
			else if(ae.getSource()==cmbMon)
			{
				cmbYr.requestFocus();
			}
			else if(ae.getSource()==cmbYr)
			{
				cmbSiD.requestFocus();
			}
			else if(ae.getSource()==cmbSiD)
			{
				txtSN.requestFocus();
			}
			else if(ae.getSource()==txtSN)
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
				txtBSal.requestFocus();
			}
			else if(ae.getSource()==txtBSal)
			{
				txtDa.requestFocus();
			}
			else if(ae.getSource()==txtDa)
			{
				txtAmt1.requestFocus();
			}
			else if(ae.getSource()==txtAmt1)
			{
				txtPf.requestFocus();
			}
			else if(ae.getSource()==txtPf)
			{
				txtAmt2.requestFocus();
			}
			else if(ae.getSource()==txtAmt2)
			{
				txtHra.requestFocus();
			}
			else if(ae.getSource()==txtHra)
			{
				txtAmt3.requestFocus();
			}
			else if(ae.getSource()==txtAmt3)
			{
				txtTax.requestFocus();
			}
			else if(ae.getSource()==txtTax)
			{
				txtAmt4.requestFocus();
			}
			else if(ae.getSource()==txtAmt4)
			{
				txtGSal.requestFocus();
			}
			else if(ae.getSource()==txtGSal)
			{
				txtNSal.requestFocus();
			}
		else if(ae.getSource()==btnSave)
		{
		//String Name --> TextField.getText();
			SlipNo=txtSlipN.getText();
			Date=txtDt.getText();
			Month=cmbMon.getSelectedItem().toString();
			Year=cmbYr.getSelectedItem().toString();
			StaffID=cmbSiD.getSelectedItem().toString();
			Sname=txtSN.getText();
			Gender=txtGen.getText();
			Department=txtDept.getText();
			Designation=txtDegn.getText();
			BSalary=txtBSal.getText();
			Da=txtDa.getText();
			Amount1=txtAmt1.getText();
			Pf=txtPf.getText();
			Amount2=txtAmt2.getText();
			Hra=txtHra.getText();
			Amount3=txtAmt3.getText();
			Tax=txtTax.getText();
			Amount4=txtAmt4.getText();
			GrossSal=txtGSal.getText();
			NetSal=txtNSal.getText();
			try
			{
				stmtInsert=conn.createStatement();
				stmtDupSlipNo=conn.createStatement();
				rsDupSlipNo=stmtDupSlipNo.executeQuery("Select * from eiprs_tblsalslip where SlipNum='"+SlipNo+"'");//Table='"+String+"'
				if(rsDupSlipNo.next() || SlipNo.length()==0 || Date.length()==0 || Month.length()==0 || Year.length()==0 || StaffID.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || BSalary.length()==0 || Da.length()==0 || Amount1.length()==0 || Pf.length()==0 || Amount2.length()==0 || Hra.length()==0 || Amount3.length()==0 || Tax.length()==0 || Amount4.length()==0 || GrossSal.length()==0 || NetSal.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate Record or Blank Record ! Can't Save","EIPRS",1);
				}
				else
				{
					stmtInsert.executeUpdate("Insert into eiprs_tblsalslip values ('"+SlipNo+"','"+Date+"','"+Month+"','"+Year+"','"+StaffID+"','"+Sname+"','"+Gender+"','"+Department+"','"+Designation+"','"+BSalary+"','"+Da+"','"+Amount1+"','"+Pf+"','"+Amount2+"','"+Hra+"','"+Amount3+"','"+Tax+"','"+Amount4+"','"+GrossSal+"','"+NetSal+"')");
					
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
					dlg.showMessageDialog(f1,"This is Last Record. Unable To GO Next","EIPRS",0);
				}
				else
				{
					rs.next();
				//String Name --> rs.getString("Table Name");
					SlipNo=rs.getString("SlipNum");
					Date=rs.getString("Date");
					Month=rs.getString("MonthOf");
					Year=rs.getString("Year");
					StaffID=rs.getString("Staff_ID");
					Sname=rs.getString("StaffName");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					BSalary=rs.getString("BasicSalary");
					Da=rs.getString("Da");
					Amount1=rs.getString("Amount1");
					Pf=rs.getString("Pf");
					Amount2=rs.getString("Amount2");
					Hra=rs.getString("Hra");
					Amount3=rs.getString("Amount3");
					Tax=rs.getString("Tax");
					Amount4=rs.getString("Amount4");
					GrossSal=rs.getString("GrossSalary");
					NetSal=rs.getString("NetSalary");
					
				//TextField.setText(String Name); or ComboBox.setSelectedItem(String Name);
					txtSlipN.setText(SlipNo);
					txtDt.setText(Date);
					cmbMon.setSelectedItem(Month);
					cmbYr.setSelectedItem(Year);
					cmbSiD.setSelectedItem(StaffID);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtBSal.setText(BSalary);
					txtDa.setText(Da);
					txtAmt1.setText(Amount1);
					txtPf.setText(Pf);
					txtAmt2.setText(Amount2);
					txtHra.setText(Hra);
					txtAmt3.setText(Amount3);
					txtTax.setText(Tax);
					txtAmt4.setText(Amount4);
					txtGSal.setText(GrossSal);
					txtNSal.setText(NetSal);
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
					dlg.showMessageDialog(f1,"Unable To Go Previous.This is First Record.","EIPRS",1);
				}
				else 
				{	
					rs.previous();
				//String Name --> rs.getString("Table Name");
					SlipNo=rs.getString("SlipNum");
					Date=rs.getString("Date");
					Month=rs.getString("MonthOf");
					Year=rs.getString("Year");
					StaffID=rs.getString("Staff_ID");
					Sname=rs.getString("StaffName");
					Gender=rs.getString("Gender");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					BSalary=rs.getString("BasicSalary");
					Da=rs.getString("Da");
					Amount1=rs.getString("Amount1");
					Pf=rs.getString("Pf");
					Amount2=rs.getString("Amount2");
					Hra=rs.getString("Hra");
					Amount3=rs.getString("Amount3");
					Tax=rs.getString("Tax");
					Amount4=rs.getString("Amount4");
					GrossSal=rs.getString("GrossSalary");
					NetSal=rs.getString("NetSalary");
					
				//TextField.setText(String Name); or ComboBox.setSelectedItem(String Name);
					txtSlipN.setText(SlipNo);
					txtDt.setText(Date);
					cmbMon.setSelectedItem(Month);
					cmbYr.setSelectedItem(Year);
					cmbSiD.setSelectedItem(StaffID);
					txtSN.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtBSal.setText(BSalary);
					txtDa.setText(Da);
					txtAmt1.setText(Amount1);
					txtPf.setText(Pf);
					txtAmt2.setText(Amount2);
					txtHra.setText(Hra);
					txtAmt3.setText(Amount3);
					txtTax.setText(Tax);
					txtAmt4.setText(Amount4);
					txtGSal.setText(GrossSal);
					txtNSal.setText(NetSal);
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
			SlipNo=dlg.showInputDialog(f1,"Enter Slip No:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("Select * from eiprs_tblsalslip where SlipNum='"+SlipNo+"'");//Table Namae='"+String Name+"'
				if(rsSearch.next())
				{
				//String Name -->rsSearch.getString("Table Name");
					SlipNo=rsSearch.getString("SlipNum");
					Date=rsSearch.getString("Date");
					Month=rsSearch.getString("MonthOf");
					Year=rsSearch.getString("Year");
					StaffID=rsSearch.getString("Staff_ID");
					Sname=rsSearch.getString("StaffName");
					Gender=rsSearch.getString("Gender");
					Department=rsSearch.getString("Department");
					Designation=rsSearch.getString("Designation");
					BSalary=rsSearch.getString("BasicSalary");
					Da=rsSearch.getString("Da");
					Amount1=rsSearch.getString("Amount1");
					Pf=rsSearch.getString("Pf");
					Amount2=rsSearch.getString("Amount2");
					Hra=rsSearch.getString("Hra");
					Amount3=rsSearch.getString("Amount3");
					Tax=rsSearch.getString("Tax");
					Amount4=rsSearch.getString("Amount4");
					GrossSal=rsSearch.getString("GrossSalary");
					NetSal=rsSearch.getString("NetSalary");
					
				//TextField.setText(String Name);
					txtSlipN.setText(SlipNo);
					txtDt.setText(Date);
					cmbMon.setSelectedItem(Month);
					cmbYr.setSelectedItem(Year);
					cmbSiD.setSelectedItem(StaffID);
					txtSN.setText(Sname);
					txtGen.setText(Gender);
					txtDept.setText(Department);
					txtDegn.setText(Designation);
					txtBSal.setText(BSalary);
					txtDa.setText(Da);
					txtAmt1.setText(Amount1);
					txtPf.setText(Pf);
					txtAmt2.setText(Amount2);
					txtHra.setText(Hra);
					txtAmt3.setText(Amount3);
					txtTax.setText(Tax);
					txtAmt4.setText(Amount4);
					txtGSal.setText(GrossSal);
					txtNSal.setText(NetSal);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Search !"+se);
				dlg.showMessageDialog(f1,"Unbale To Search !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
			SlipNo=txtSlipN.getText();
			try
			{
				if(txtSlipN.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure To Delete ?","EIPRS",1);
					if(choice==0)//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete from eiprs_tblsalslip where SlipNum='"+SlipNo+"'");
						dlg.showMessageDialog(f1,"One Recorde Delete Successfully","EIPRS",2);
					}
					else 
					{
						dlg.showMessageDialog(f1,"Thanksgiven Data Not Deleted","EIPRS",3);
					}
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable To Delete.Try Again Latter","EIPRS",0);
				System.out.println("Unable To Delete.Try Again Latter"+se);
			}
		}
		else if(ae.getSource()==btnUpdate)
		{
		//String Name --> TextField.getText();
				SlipNo=txtSlipN.getText();
				Date=txtDt.getText();
				Month=cmbMon.getSelectedItem().toString();
				Year=cmbYr.getSelectedItem().toString();
				StaffID=cmbSiD.getSelectedItem().toString();
				Sname=txtSN.getText();
				Gender=txtGen.getText();
				Department=txtDept.getText();
				Designation=txtDegn.getText();
				BSalary=txtBSal.getText();
				Da=txtDa.getText();
				Amount1=txtAmt1.getText();
				Pf=txtPf.getText();
				Amount2=txtAmt2.getText();
				Hra=txtHra.getText();
				Amount3=txtAmt3.getText();
				Tax=txtTax.getText();
				Amount4=txtAmt4.getText();
				GrossSal=txtGSal.getText();
				NetSal=txtNSal.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				if(SlipNo.length()==0 || Date.length()==0 || Month.length()==0 || Year.length()==0 || StaffID.length()==0 || Sname.length()==0 || Gender.length()==0 || Department.length()==0 || Designation.length()==0 || BSalary.length()==0 || Da.length()==0 || Amount1.length()==0 || Pf.length()==0 || Amount2.length()==0 || Hra.length()==0 || Amount3.length()==0 || Tax.length()==0 || Amount4.length()==0 || GrossSal.length()==0 || NetSal.length()==0)
				{
					dlg.showMessageDialog(f1,"Blank Records So Unable To Update","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblsalslip set SlipNum='"+SlipNo+"',Date='"+Date+"',MonthOf='"+Month+"',Year='"+Year+"',Staff_ID='"+StaffID+"',StaffName='"+Sname+"',Gender='"+Gender+"',Department='"+Department+"',Designation='"+Designation+"',BasicSalary='"+BSalary+"',Da='"+Da+"',Amount1='"+Amount1+"',Pf='"+Pf+"',Amount2='"+Amount2+"',Hra='"+Hra+"',Amount3='"+Amount3+"',Tax='"+Tax+"',Amount4='"+Amount4+"',GrossSalary='"+GrossSal+"',NetSalary='"+NetSal+"' where SlipNum='"+SlipNo+"'");//Table='"+String+"'  where Table='"+String+"');
					dlg.showMessageDialog(f1,"One Record Update Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unbale To Update !"+se);
				dlg.showMessageDialog(f1,"Unbale To Update !","EIPRS",1);
			}
		}
	}
}
class Eiprs_SalSlip
{
	public static void main(String s[])
	{
		Eiprs_SalSlipApp ss=new Eiprs_SalSlipApp();
	}
}