import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class Eiprs_StaffApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4;
	GridLayout gl51,gl22,gl94;
	FlowLayout flw;
	JLabel lblHName,lblSID,lblSName,lblGender,lblFName,lblMName,lblDOB,lblLoc,lblCity,lblState,
			lblPIN,lblPhone,lblEmail,lblDOJoin,lblDept,lblDegn,lblBSalary,lblDA,lblHRA,lblTax,lblPF;
			
	JLabel b1,b2,b3,b4;		
	
	JTextField txtSID,txtSName,txtFName,txtMName,txtDOB,txtLoc,txtCity,txtState,
			txtPIN,txtPhone,txtEmail,txtDOJoin,txtBSalary,txtDA,txtHRA,txtTax,txtPF;
	JComboBox cmbGender,cmbDept,cmbDegn;
	JButton btnClear,btnSave,btnNext,btnPrevious,btnSearch,btnDelete,btnUpdate,btnClose;
	Box vb,hb;
	ImageIcon imgNext,imgSave,imgClose,imgClear,imgPrevious,imgDelete,imgSearch,imgUpdate;
	Color red,black;
	Font fnt1,fnt2;
	
	Connection conn;
	Statement stmtInsert,stmtSelect,stmtDelete,stmtUpdate,stmtSearch,stmtDept,stmtData,stmtDupDcode;
	JOptionPane dlg;
	ResultSet rs,rsSearch,rsDept,rsData,rsDupDcode;
	int choice;
	String SID,Sname,Gender,Fname,Mname,DOB,Locality,City,State,PIN,Phone,Email,DJoin,Department,
			Designation,Bsalary,DA,HRA,PF,TAX;
	//Table SID,Sname,Gender,Fname,Mname,DOB,Locality,City,State,PIN,Phone,Email,DJoin,Department,Designation,Bsalary,DA,HRA,PF,TAX;

//Constructor Start
	public Eiprs_StaffApp()
	{
		f1=new JFrame("BPCTM Staff Information Register");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();

		gl51=new GridLayout(5,1);
		gl22=new GridLayout(2,3);//We can set different values in GrideLayout
		gl94=new GridLayout(9,4);
		
		flw=new FlowLayout();
		
		dlg=new JOptionPane();
		
	//Color Creation
		red=new Color(125,0,0);
		black=new Color(200,200,200);
		
	//Label Declaration
		lblHName=new JLabel("STAFF  INFORMATION");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,26);
		fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,26);
		lblHName.setFont(fnt1);
		lblHName.setFont(fnt2);
		lblHName.setForeground(Color.red);
		lblHName.setBackground(Color.black);
		
		lblSID=new JLabel("Staff ID:");		
		lblSName=new JLabel("Staff Name:");
		
		b1=new JLabel("");
		b2=new JLabel("");
		b3=new JLabel("");
		b4=new JLabel("");
		
		lblGender=new JLabel("Gender:");
		lblDOB=new JLabel("    Date Of Birth:");
		lblFName=new JLabel("Father's Name:");		
		lblMName=new JLabel("    Mother's Name:");
		lblLoc=new JLabel("Locality:");
		lblCity=new JLabel("    City:");
		lblState=new JLabel("State:");		
		lblPIN=new JLabel("    PIN:");
		lblPhone=new JLabel("Phone No:");
		lblEmail=new JLabel("    Email:");
		lblDOJoin=new JLabel("Date of Join:");		
		lblDept=new JLabel("    Department:");
		lblDegn=new JLabel("Designation:");
		lblBSalary=new JLabel("    Basic Salary:");
		lblDA=new JLabel("D.A %:");
		lblHRA=new JLabel("H.R.A %:");		
		lblTax=new JLabel("    Tax %:");
		lblPF=new JLabel("    P.F %:");
		
	//TextField Declaration
		txtSID=new JTextField(50);		
		txtSName=new JTextField(50);
		txtDOB=new JTextField(50);
		txtFName=new JTextField(50);		
		txtMName=new JTextField(50);
		txtLoc=new JTextField(50);
		txtCity=new JTextField(50);
		txtState=new JTextField(50);		
		txtPIN=new JTextField(20);
		txtPhone=new JTextField(20);
		txtEmail=new JTextField(50);
		txtDOJoin=new JTextField(20);		
		txtBSalary=new JTextField(20);
		txtDA=new JTextField(20);
		txtHRA=new JTextField(20);		
		txtTax=new JTextField(20);
		txtPF=new JTextField(20);
		
	//Combo Dclaration
		cmbGender=new JComboBox();
		cmbGender.addItem("-Select Gender-");
		cmbGender.addItem("Male");
		cmbGender.addItem("Female");
		cmbGender.addItem("Other");
		
		cmbDept=new JComboBox();
		cmbDept.addItem("-Select Department-");
		//Item should comes from Department Table
		
		cmbDegn=new JComboBox();
		cmbDegn.addItem("-Select Designation-");
		cmbDegn.addItem("Principal");
		cmbDegn.addItem("HOD");
		cmbDegn.addItem("Senior Lecture");
		cmbDegn.addItem("Junior Lecture");
		cmbDegn.addItem("Lab Assistant");
		cmbDegn.addItem("Lab Store Keeper");
		cmbDegn.addItem("Clerk");
		
	//ImageIcon Declaration
		imgClear=new ImageIcon("JClear1.png");//3
		imgSave=new ImageIcon("JSave.png");//2
		imgDelete=new ImageIcon("JDelete.png");//2
		imgNext=new ImageIcon("JNext.png");//
		imgPrevious=new ImageIcon("JPrevious.png");//
		imgClose=new ImageIcon("IClose1.png");
		imgSearch=new ImageIcon("ISearch.png");
		imgUpdate=new ImageIcon("IEdit1.png");
		
	//Button Declaration & Image Adding on Button
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
	//ToolTipText with Button
		btnClear.setToolTipText("Clear the Fields...");
		btnSave.setToolTipText("Save Records...");
		btnNext.setToolTipText("Go to Next Records...");
		btnPrevious.setToolTipText("Go to Previous Records...");
		btnSearch.setToolTipText("Search The SID...");
		btnDelete.setToolTipText("Delete Records...");
		btnUpdate.setToolTipText("Update Records/TextField...");
		btnClose.setToolTipText("Close The GUI Application...");
	//ToolTipText with TextField
		txtSID.setToolTipText("Enter Staff ID...");
		txtSName.setToolTipText("Enter Staff Name...");
		txtFName.setToolTipText("Enter Father Name...");
		txtMName.setToolTipText("Enter Mother Name...");
		txtDOB.setToolTipText("Enter Date Of Birth...");
		txtLoc.setToolTipText("Enter Locality of Staff...");
		txtCity.setToolTipText("Enter City of Staff...");
		txtState.setToolTipText("Enter State of Staff...");
		txtPIN.setToolTipText("Enter PIN Code of Stsff...");
		txtPhone.setToolTipText("Enter Phone Number of Staff...");
		txtEmail.setToolTipText("Enter Email ID...");
		txtDOJoin.setToolTipText("Enter When Staff Join...");
		txtBSalary.setToolTipText("Enter Basic Salary of Staff...");
		txtDA.setToolTipText("Enter DA...");
		txtHRA.setToolTipText("Enter HRA...");
		txtTax.setToolTipText("Enter TAX...");
		txtPF.setToolTipText("Enter Provisional Funds...");
		cmbDegn.setToolTipText("Enter Designation of Staff...");
		cmbDept.setToolTipText("Enter Department of Staff...");
		cmbGender.setToolTipText("Enter Gender of Staff...");
		
	//Action Listener Add in Buttons
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
	//Adding Action Listener in TextField
		txtSID.addActionListener(this);
		txtSName.addActionListener(this);
		txtFName.addActionListener(this);
		txtMName.addActionListener(this);
		txtDOB.addActionListener(this);
		txtLoc.addActionListener(this);
		txtCity.addActionListener(this);
		txtState.addActionListener(this);
		txtPIN.addActionListener(this);
		txtPhone.addActionListener(this);
		txtEmail.addActionListener(this);
		txtDOJoin.addActionListener(this);
		txtBSalary.addActionListener(this);
		txtDA.addActionListener(this);
		txtHRA.addActionListener(this);
		txtTax.addActionListener(this);
		txtPF.addActionListener(this);
		
	//Horizontal & Vertical Box object create
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//set Panel
		p1.setLayout(flw);
		p1.add(lblHName);
		
		p2.setLayout(gl22);
		p2.add(lblSID);
		p2.add(txtSID);
		p2.add(b1);//p2.add(new JLabel(""));
		p2.add(b2);//p2.add(new JLabel(""));
		p2.add(lblSName);
		p2.add(txtSName);
		p2.add(b3);//p2.add(new JLabel(""));
		p2.add(b4);//p2.add(new JLabel(""));
		
		p3.setLayout(gl94);
		p3.add(lblGender);
		p3.add(cmbGender);
		p3.add(lblDOB);
		p3.add(txtDOB);
		p3.add(lblFName);
		p3.add(txtFName);
		p3.add(lblMName);
		p3.add(txtMName);
		p3.add(lblLoc);
		p3.add(txtLoc);
		p3.add(lblCity);
		p3.add(txtCity);
		p3.add(lblState);
		p3.add(txtState);
		p3.add(lblPIN);
		p3.add(txtPIN);
		p3.add(lblPhone);
		p3.add(txtPhone);
		p3.add(lblEmail);
		p3.add(txtEmail);
		p3.add(lblDOJoin);
		p3.add(txtDOJoin);
		p3.add(lblDept);
		p3.add(cmbDept);
		p3.add(lblDegn);
		p3.add(cmbDegn);
		p3.add(lblBSalary);
		p3.add(txtBSalary);
		p3.add(lblDA);
		p3.add(txtDA);
		p3.add(lblPF);
		p3.add(txtPF);
		p3.add(lblHRA);
		p3.add(txtHRA);
		p3.add(lblTax);
		p3.add(txtTax);
		
		p4.setLayout(flw);
		p4.add(btnClear);
		p4.add(btnSave);
		p4.add(btnNext);
		p4.add(btnPrevious);
		p4.add(btnSearch);
		p4.add(btnDelete);
		p4.add(btnUpdate);
		p4.add(btnClose);
		
	//Adding Panel in Box
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(p3);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p4);
		vb.add(Box.createVerticalStrut(10));

		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
	//Set Frame
		f1.setLayout(new BorderLayout());//~
		f1.add(hb,BorderLayout.CENTER);
		f1.setResizable(false);//Never be Full Screen
		f1.setBounds(120,100,1000,600);
		doConnect();
		fillDept();
		f1.setVisible(true);
	}
	public void fillDept()
	{
		try
		{
			stmtDept=conn.createStatement();
			rsDept=stmtDept.executeQuery("select * from eiprs_tblDepartment");
			while(rsDept.next())
			{
				Department=rsDept.getString("Dname");
				cmbDept.addItem(Department);
			}
		}
		catch(SQLException se)
		{
			//System.out.println("Unable to Add Department"+se);
			dlg.showMessageDialog(f1,"Unable to Add Department !","EIPRS",1);
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
			rs=stmtSelect.executeQuery("select * from eiprs_tblStaffInfo");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data:"+se);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnClear)//~
		{
			if(txtSID.getText().length()>0)
			{
				txtSID.setText("");
				txtSName.setText("");
				cmbGender.setSelectedIndex(0);
				txtFName.setText("");
				txtMName.setText("");
				txtDOB.setText("");
				txtLoc.setText("");
				txtCity.setText("");
				txtState.setText("");
				txtPIN.setText("");
				txtPhone.setText("");
				txtEmail.setText("");
				txtDOJoin.setText("");
				cmbDept.setSelectedIndex(0);
				cmbDegn.setSelectedIndex(0);
				txtBSalary.setText("");
				txtDA.setText("");
				txtHRA.setText("");
				txtPF.setText("");
				txtTax.setText("");
				
				dlg.showMessageDialog(f1,"Cleared All Records","BPUTM",0);
				txtSID.requestFocus();
			}
		}
			else if(ae.getSource()==txtSID)
			{
				txtSName.requestFocus();
			}
			else if(ae.getSource()==txtSName)
			{
				cmbGender.requestFocus();
			}
			else if(ae.getSource()==cmbGender)
			{
				txtDOB.requestFocus();
			}
			else if(ae.getSource()==txtDOB)
			{
				txtFName.requestFocus();
			}
			else if(ae.getSource()==txtFName)
			{
				txtMName.requestFocus();
			}
			else if(ae.getSource()==txtMName)
			{
				txtLoc.requestFocus();
			}
			else if(ae.getSource()==txtLoc)
			{
				txtCity.requestFocus();
			}
			else if(ae.getSource()==txtCity)
			{
				txtState.requestFocus();
			}
			else if(ae.getSource()==txtState)
			{
				txtPIN.requestFocus();
			}
			else if(ae.getSource()==txtPIN)
			{
				txtPhone.requestFocus();
			}
			else if(ae.getSource()==txtPhone)
			{
				txtEmail.requestFocus();
			}
			else if(ae.getSource()==txtEmail)
			{
				txtDOJoin.requestFocus();
			}
			else if(ae.getSource()==txtDOJoin)
			{
				cmbDept.requestFocus();
			}
			else if(ae.getSource()==cmbDept)
			{
				cmbDegn.requestFocus();
			}
			else if(ae.getSource()==cmbDept)
			{
				cmbDegn.requestFocus();
			}
			else if(ae.getSource()==cmbDegn)
			{
				txtBSalary.requestFocus();
			}
			else if(ae.getSource()==txtBSalary)
			{
				txtDA.requestFocus();
			}
			else if(ae.getSource()==txtDA)
			{
				txtHRA.requestFocus();
			}
			else if(ae.getSource()==txtHRA)
			{
				txtPF.requestFocus();
			}
			else if(ae.getSource()==txtPF)
			{
				txtTax.requestFocus();
			}

		else if(ae.getSource()==btnSave)
		{
			SID=txtSID.getText();
			Sname=txtSName.getText();
			Gender=cmbGender.getSelectedItem().toString();
			Fname=txtFName.getText();
			Mname=txtMName.getText();
			DOB=txtDOB.getText();
			Locality=txtLoc.getText();
			City=txtCity.getText();
			State=txtState.getText();
			PIN=txtPIN.getText();
			Phone=txtPhone.getText();
			Email=txtEmail.getText();
			DJoin=txtDOJoin.getText();
			Department=cmbDept.getSelectedItem().toString();
			Designation=cmbDegn.getSelectedItem().toString();
			Bsalary=txtBSalary.getText();
			DA=txtDA.getText();
			HRA=txtHRA.getText();
			PF=txtPF.getText();
			TAX=txtTax.getText();
			try
			{
				stmtInsert=conn.createStatement();
				stmtDupDcode=conn.createStatement();
				rsDupDcode=stmtDupDcode.executeQuery("select *from eiprs_tblDepartment where Dcode='"+Department+"'");//~
				
				if(rsDupDcode.next() || Department.length()==0 || SID.length()==0 || Sname.length()==0 || Gender.length()==0 || Fname.length()==0 || Mname.length()==0 || DOB.length()==0 || Locality.length()==0 || City.length()==0 || State.length()==0 || PIN.length()==0 || Phone.length()==0 || Email.length()==0 || DJoin.length()==0 || Designation.length()==0 || Bsalary.length()==0 || DA.length()==0 || HRA.length()==0 || PF.length()==0 || TAX.length()==0)//~ 
				{
					dlg.showMessageDialog(f1,"Duplicate or Blank Records","EIPRS",1);
				}
				else
				{
					stmtInsert.executeUpdate("insert into eiprs_tblStaffInfo values('"+SID+"','"+Sname+"','"+Gender+"','"+DOB+"','"+Fname+"','"+Mname+"','"+Locality+"','"+City+"','"+State+"','"+PIN+"','"+Phone+"','"+Email+"','"+DJoin+"','"+Department+"','"+Designation+"','"+Bsalary+"','"+DA+"','"+HRA+"','"+PF+"','"+TAX+"')");
					
					dlg.showMessageDialog(f1,"One Record Saved Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Save Records !");
				dlg.showMessageDialog(f1,"Unable to Save Records Due to Duplicacy or Blank Records","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnNext)
		{
			try
			{
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"Last Record ! Unable To Go Next","EIPRS",1);
				}
				else
				{
					rs.next();
				//String Name--->Table name
					SID=rs.getString("SID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Fname=rs.getString("Fname");
					Mname=rs.getString("Mname");
					DOB=rs.getString("DOB");
					Locality=rs.getString("Locality");
					City=rs.getString("City");
					State=rs.getString("State");
					PIN=rs.getString("PIN");
					Phone=rs.getString("Phone");
					Email=rs.getString("Email");
					DJoin=rs.getString("DOJoin");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					Bsalary=rs.getString("Bsalary");
					DA=rs.getString("DA");
					HRA=rs.getString("HRA");
					PF=rs.getString("PF");
					TAX=rs.getString("TAX");
					
					txtSID.setText(SID);//TextField to setText(Table) 
					txtSName.setText(Sname);
					cmbGender.setSelectedItem(Gender);
					txtFName.setText(Fname);
					txtMName.setText(Mname);
					txtDOB.setText(DOB);
					txtLoc.setText(Locality);
					txtCity.setText(City);
					txtState.setText(State);
					txtPIN.setText(PIN);
					txtPhone.setText(Phone);
					txtEmail.setText(Email);
					txtDOJoin.setText(DJoin);
					cmbDept.setSelectedItem(Department);
					cmbDegn.setSelectedItem(Designation);
					txtBSalary.setText(Bsalary);
					txtDA.setText(DA);
					txtHRA.setText(HRA);
					txtPF.setText(PF);
					txtTax.setText(TAX);
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
					dlg.showMessageDialog(f1,"First Record !.Unable To Go Previous","EIPRS",1);
				}
				else
				{
					rs.previous();
				//String Name--->Table name
					SID=rs.getString("SID");
					Sname=rs.getString("Sname");
					Gender=rs.getString("Gender");
					Fname=rs.getString("Fname");
					Mname=rs.getString("Mname");
					DOB=rs.getString("DOB");
					Locality=rs.getString("Locality");
					City=rs.getString("City");
					State=rs.getString("State");
					PIN=rs.getString("PIN");
					Phone=rs.getString("Phone");
					Email=rs.getString("Email");
					DJoin=rs.getString("DOJoin");
					Department=rs.getString("Department");
					Designation=rs.getString("Designation");
					Bsalary=rs.getString("Bsalary");
					DA=rs.getString("DA");
					HRA=rs.getString("HRA");
					PF=rs.getString("PF");
					TAX=rs.getString("TAX");
					
					txtSID.setText(SID);
					txtSName.setText(Sname);
					cmbGender.setSelectedItem(Gender);
					txtFName.setText(Fname);
					txtMName.setText(Mname);
					txtDOB.setText(DOB);
					txtLoc.setText(Locality);
					txtCity.setText(City);
					txtState.setText(State);
					txtPIN.setText(PIN);
					txtPhone.setText(Phone);
					txtEmail.setText(Email);
					txtDOJoin.setText(DJoin);
					cmbDept.setSelectedItem(Department);
					cmbDegn.setSelectedItem(Designation);
					txtBSalary.setText(Bsalary);
					txtDA.setText(DA);
					txtHRA.setText(HRA);
					txtPF.setText(PF);
					txtTax.setText(TAX);	
				}
			}
			catch(SQLException se)
			{
				//System.out.pritln("Unable to go previous"+se);
				dlg.showMessageDialog(f1,"Unable to Go Previous","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnSearch)
		{
			SID=dlg.showInputDialog(f1,"Enter Staff ID:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("select * from eiprs_tblStaffInfo where SID='"+SID+"'"); //where Table Name='"+String Name+"'
				if(rsSearch.next())
				{
				//String Name --> Table Name
					SID=rsSearch.getString("SID");
					Sname=rsSearch.getString("Sname");
					Gender=rsSearch.getString("Gender");
					DOB=rsSearch.getString("DOB");
					Fname=rsSearch.getString("Fname");
					Mname=rsSearch.getString("Mname");
					Locality=rsSearch.getString("Locality");
					City=rsSearch.getString("City");
					State=rsSearch.getString("State");
					PIN=rsSearch.getString("PIN");
					Phone=rsSearch.getString("Phone");
					Email=rsSearch.getString("Email");
					DJoin=rsSearch.getString("DOJoin");
					Department=rsSearch.getString("Department");
					Designation=rsSearch.getString("Designation");
					Bsalary=rsSearch.getString("Bsalary");
					DA=rsSearch.getString("DA");
					HRA=rsSearch.getString("HRA");
					PF=rsSearch.getString("PF");
					TAX=rsSearch.getString("TAX");
					
					txtSID.setText(SID);
					txtSName.setText(Sname);
					cmbGender.setSelectedItem(Gender);
					txtFName.setText(Fname);
					txtMName.setText(Mname);
					txtDOB.setText(DOB);
					txtLoc.setText(Locality);
					txtCity.setText(City);
					txtState.setText(State);
					txtPIN.setText(PIN);
					txtPhone.setText(Phone);
					txtEmail.setText(Email);
					txtDOJoin.setText(DJoin);
					cmbDept.setSelectedItem(Department);
					cmbDegn.setSelectedItem(Designation);
					txtBSalary.setText(Bsalary);
					txtDA.setText(DA);
					txtHRA.setText(HRA);
					txtPF.setText(PF);
					txtTax.setText(TAX);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Search"+se);
				dlg.showMessageDialog(f1,"unable to Search","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
			Sname=txtSName.getText();
			try
			{
				if(txtSID.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure to Delete...?","Alert",1);
					if(choice==0)
					//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete from eiprs_tblStaffInfo where SName='"+Sname+"'");
						dlg.showMessageDialog(f1,"One Record Delete Successfully","EIPRS",2);
					}
					else
					{
						//System.out.println("Your Data Not Deleting");
						dlg.showMessageDialog(f1,"Thankful Data not Delete","EIPRS",1);
					}
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable to Delete.Try Again Latter","EIPRS",2);
				System.out.println("Unable to Delete.Try Again Latter"+se);
			}
		}
		else if(ae.getSource()==btnUpdate)//~
		{
			SID=txtSID.getText();
			Sname=txtSName.getText();
			Gender=cmbGender.getSelectedItem().toString();
			Fname=txtFName.getText();
			Mname=txtMName.getText();
			DOB=txtDOB.getText();
			Locality=txtLoc.getText();
			City=txtCity.getText();
			State=txtState.getText();
			PIN=txtPIN.getText();
			Phone=txtPhone.getText();
			Email=txtEmail.getText();
			DJoin=txtDOJoin.getText();
			Department=cmbDept.getSelectedItem().toString();
			Designation=cmbDegn.getSelectedItem().toString();
			Bsalary=txtBSalary.getText();
			DA=txtDA.getText();
			HRA=txtHRA.getText();
			PF=txtPF.getText();
			TAX=txtTax.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				
				if(SID.length()==0 || Sname.length()==0 || Gender.length()==0 || Fname.length()==0 || Mname.length()==0 || DOB.length()==0 || Locality.length()==0 || City.length()==0 || State.length
				()==0 || PIN.length()==0 || Phone.length()==0 || Email.length()==0 || DJoin.length()==0 || Department.length()==0 || Designation.length()==0 || Bsalary.length()==0 || DA.length()==0 || HRA.length()==0 || PF.length()==0 || TAX.length()==0)
				{
					dlg.showMessageDialog(f1,"Blank Record Can't Update","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblStaffInfo set SID='"+SID+"',Sname='"+Sname+"',Gender='"+Gender+"',Fname='"+Fname+"',Mname='"+Mname+"',DOB='"+DOB+"',Locality='"+Locality+"',City='"+City+"',State='"+State+"',PIN='"+PIN+"',Phone='"+Phone+"',Email='"+Email+"',DOJoin='"+DJoin+"',Department='"+Department+"',Designation='"+Designation+"',Bsalary='"+Bsalary+"',DA='"+DA+"',HRA='"+HRA+"',PF='"+PF+"',TAX='"+TAX+"' where SID='"+SID+"'");//~
					
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
				System.out.println("Error in Closing the Program...");
			}
			f1.setVisible(false);
			f1.dispose();
		}
	}
}
class Eiprs_StaffInfo
{
	public static void main(String s[])
	{
		Eiprs_StaffApp es;
		es=new Eiprs_StaffApp();
	}
}