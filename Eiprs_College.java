import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;

class Eiprs_CollegeApp implements ActionListener{
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl92,gl31;
	FlowLayout flw;
	JLabel lblHead,lblCName,lblPName,lblPd,lblLoc,lblCt,lblSUT,lblPh,lblEml,lblWeb;
	JTextField txtHead,txtCName,txtPName,txtPd,txtLoc,txtCt,txtPh,txtEml,txtWeb;
	JComboBox cmbSUT;
	JButton btnClear,btnSave,btnNext,btnPrevious,btnSearch,btnDelete,btnUpdate,btnClose;
	//BorderLayout bl1, bl2, bl3;
	ImageIcon imgNext,imgSave,imgClear,imgClose,imgDelete,imgPrevious,imgSearch,imgUpdate;
	Box hb,vb;
	
	Connection conn;
	Statement stmtInsert,stmtSelect,stmtDelete,stmtUpdate,stmtDupCname,stmtSearch;
	String Cname,Pname,Period,Locality,City,SUT,Phone_no,Email_ID,WebSite;
	ResultSet rs,rsDupCname,rsSearch;		
	Font fnt1,fnt2;
	
	Border lineb1, lineb2, lineb3;
	//Color green, mix;
	
	JOptionPane dlg;
	int choice;
	String mid;
	
//Constructor start
	public Eiprs_CollegeApp()
	{
		f1=new JFrame("BPCTM");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		gl92=new GridLayout(9,2);
		gl31=new GridLayout(3,1);
		
		flw=new FlowLayout();
		dlg=new JOptionPane();
		
	//Label Declaration
		lblHead=new JLabel("COLLEGE INFORMATION");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,26);
		//fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,26);//What its work...?
		lblHead.setFont(fnt1);
		lblHead.setForeground(Color.red);
		//p1.setBackground(Color.blue);
		
		lblCName=new JLabel("College Name:");
		lblPName=new JLabel("Name of Principal:");
		lblPd=new JLabel("Period:");
		lblLoc=new JLabel("Locality:");
		lblCt=new JLabel("City:");
		lblSUT=new JLabel("State/U.Teritory:");
		lblPh=new JLabel("Phone Number:");
		lblEml=new JLabel("Email_ID:");
		lblWeb=new JLabel("Web Site:");
		
	//TextField Declaration
		txtCName=new JTextField(50);
		txtPName=new JTextField(50);
		txtPd=new JTextField(50);
		txtLoc=new JTextField(50);
		txtCt=new JTextField(50);
		txtPh=new JTextField(50);
		txtEml=new JTextField(50);
		txtWeb=new JTextField(50);
		
	//Image Creating
		imgClear=new ImageIcon("JClear1.png");//3
		imgSave=new ImageIcon("JSave.png");//2
		imgDelete=new ImageIcon("JDelete.png");//2
		imgNext=new ImageIcon("JNext.png");//
		imgPrevious=new ImageIcon("JPrevious.png");//
		imgClose=new ImageIcon("IClose1.png");
		imgSearch=new ImageIcon("ISearch.png");
		imgUpdate=new ImageIcon("IEdit1.png");
	//Buttons Creating & Image Adding
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
		
	//ToolTip of Button
		btnClear.setToolTipText("Clear the Fields...");
		btnSave.setToolTipText("Save Records...");
		btnNext.setToolTipText("Go to Next Records...");
		btnPrevious.setToolTipText("Go to Previous Records...");
		btnSearch.setToolTipText("Search the Reference No...");
		btnDelete.setToolTipText("Delete Records...");
		btnUpdate.setToolTipText("Update Text Fields...");
		btnClose.setToolTipText("Close The GUI Application...");
	//ToolTip of TextField
		txtCName.setToolTipText("Enter College Name...");
		txtPName.setToolTipText("Enter Principal Name...");
		txtPd.setToolTipText("Enter Time Period of Principal...");
		txtLoc.setToolTipText("Enter Locality of Principal...");
		txtCt.setToolTipText("Enter City OF Principal...");
		txtPh.setToolTipText("Enter Phone Number of Principal...");
		txtEml.setToolTipText("Enter College Email...");
		txtWeb.setToolTipText("Enter WebSite of College...");
		//cmbSUT.setToolTipText("Enter State & Union Teritory...");
		
	//ComboBox adding
		cmbSUT=new JComboBox();
		cmbSUT.addItem("-Select State-");//Populatoins
		cmbSUT.addItem("Andhra Pradesh"); // 53,903,393
		cmbSUT.addItem("Arunachal Pradesh"); // 1,570,458
		cmbSUT.addItem("Assam"); // 35,607,039
		cmbSUT.addItem("Bihar"); // 124,799,926
		cmbSUT.addItem("Chhattishgarh"); // 29,436,231
		cmbSUT.addItem("Goa"); // 1,586,250
		cmbSUT.addItem("Gujurat"); // 63,872,399
		cmbSUT.addItem("Haryana"); // 28,204,692
		cmbSUT.addItem("Himachal Pradesh"); // 7,451,955
		cmbSUT.addItem("Jammu & Kashmir"); // 12,541,302
		cmbSUT.addItem("Jharkhand"); // 38,593,948
		cmbSUT.addItem("Karnataka"); // 67,562,686
		cmbSUT.addItem("Kerala"); // 35,699,443
		cmbSUT.addItem("Madhya Pradesh"); // 85,358,965
		cmbSUT.addItem("Maharastra"); // 123,144,223
		cmbSUT.addItem("Manipur"); // 3,091,545
		cmbSUT.addItem("Meghalaya"); // 3,366,710
		cmbSUT.addItem("Mizoram"); // 1,239,244
		cmbSUT.addItem("Nagaland"); // 2,249,695
		cmbSUT.addItem("Odisha"); // 46,356,334
		cmbSUT.addItem("Punjab"); // 30,141,373
		cmbSUT.addItem("Rajasthan"); // 81,032,689
		cmbSUT.addItem("Sikim"); // 690,251
		cmbSUT.addItem("Tamil Nadu"); // 77,841,267
		cmbSUT.addItem("Telengana"); // 39,362,732
		cmbSUT.addItem("Tripura"); // 4,169,794
		cmbSUT.addItem("Uttarakhand"); // 11,250,858
		cmbSUT.addItem("Uttar Pradesh"); // 237,882,725
		cmbSUT.addItem("WestBengle"); // 99,609,303
		
		//Color adding
		//green=new Color(0,125,0);
		//mix=new Color(125,125,125);
		//lblHead.setBackground(mix);
		//lblHead.setForeground(green);
		
		
	//Border Coloring
		lineb1 = BorderFactory.createLineBorder(Color.orange);
		lineb2 = BorderFactory.createLineBorder(Color.green);
		lineb3 = BorderFactory.createLineBorder(Color.pink);
		
	//Action Listener Adding to Buttons 
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
	//Action Listener Adding to TextFields
		txtCName.addActionListener(this);
		txtPName.addActionListener(this);
		txtPd.addActionListener(this);
		txtLoc.addActionListener(this);
		txtCt.addActionListener(this);
		txtPh.addActionListener(this);
		txtEml.addActionListener(this);
		txtWeb.addActionListener(this);
		cmbSUT.addActionListener(this);
		
	//Horizontal & Vertical Box creating
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//set Panel & vb,hb
		p1.setLayout(flw);
		//p1.setBorder(lineb1);
		p1.add(lblHead);
				
		p2.setLayout(gl92);
		//p2.setBorder(lineb2);
		p2.add(lblCName);
		p2.add(txtCName);
		p2.add(lblPName);
		p2.add(txtPName);
		p2.add(lblPd);
		p2.add(txtPd);
		p2.add(lblLoc);
		p2.add(txtLoc);
		p2.add(lblCt);
		p2.add(txtCt);
		p2.add(lblSUT);
		p2.add(cmbSUT);
		p2.add(lblPh);
		p2.add(txtPh);
		p2.add(lblEml);
		p2.add(txtEml);
		p2.add(lblWeb);
		p2.add(txtWeb);
		
		p3.setLayout(flw);
		//p3.setBorder(lineb3);
		p3.add(btnClear);
		p3.add(btnSave);
		p3.add(btnNext);
		p3.add(btnPrevious);
		p3.add(btnSearch);
		p3.add(btnDelete);
		p3.add(btnUpdate);
		p3.add(btnClose);
		
		
		vb.add(Box.createVerticalStrut(20));
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
		f1.setBounds(210,120,850,500);
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
			System.out.println("Unable to Load Driver");
		}
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/eiprsdb","root","root1");
			stmtSelect=conn.createStatement();
			rs=stmtSelect.executeQuery("select * from eiprs_tblcollege");
		}
		catch(SQLException se)
		{
			System.out.println("Unable to Fetch Data:"+se);
		} 
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==btnClear)
		{
			if(txtCName.getText().length()>0)
			{
				txtCName.setText("");
				txtPName.setText("");
				txtPd.setText("");
				txtLoc.setText("");
				txtCt.setText("");
				txtPh.setText("");
				txtEml.setText("");
				txtWeb.setText("");	
				//cmbSUT.setSlectedIndex(0);
				dlg.showMessageDialog(f1,"Cleared the Records","BPUTM",0);
				
				txtCName.requestFocus();
			}
		}
			else if(ae.getSource()==txtCName)
			{
				txtPName.requestFocus();
			}
			else if(ae.getSource()==txtPName)
			{
				txtPd.requestFocus();
			}
			else if(ae.getSource()==txtPd)
			{
				txtLoc.requestFocus();
			}
			else if(ae.getSource()==txtLoc)
			{
				txtCt.requestFocus();
			}
			else if(ae.getSource()==txtCt)
			{
				cmbSUT.requestFocus();
			}
			else if(ae.getSource()==cmbSUT )//&& cmbSUT.getSelectedIndex())
			{
				txtPh.requestFocus();
			}
			else if(ae.getSource()==txtPh)
			{
				txtEml.requestFocus();
			}
			else if(ae.getSource()==txtEml)
			{
				txtWeb.requestFocus();
			}
		
		else if(ae.getSource()==btnClose)
		{
			//if(txtCName.getText.length()>0)
			try
			{
				//txtCName.setText("");
				//dlg.showMessageDialog(f1,"Sure to Close The Program...?","Alert",2);
				conn.close();
			}
			catch(SQLException se)
			{
				System.out.println("Error in Closing The Program...");
			}
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==btnSave)
		{
			Cname=txtCName.getText();
			Pname=txtPName.getText();
			Period=txtPd.getText();
			Locality=txtLoc.getText();
			City=txtCt.getText();
			SUT=cmbSUT.getSelectedItem().toString();
			Phone_no=txtPh.getText();
			Email_ID=txtEml.getText();
			WebSite=txtWeb.getText();
			try{
				stmtInsert=conn.createStatement();
				stmtDupCname=conn.createStatement();
				rsDupCname=stmtDupCname.executeQuery("select * from eiprs_tblCollege where CName='"+Cname+"'");
				if(rsDupCname.next() || Cname.length()==0 || Pname.length()==0 || Period.length()==0 || Locality.length()==0 || City.length()==0 || SUT.length()==0 || Phone_no.length()==0 || Email_ID.length()==0 || WebSite.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate College or Blank Records","EIPRS",1);
				}
				else
				{
					stmtInsert.executeUpdate("insert into eiprs_tblcollege values('"+Cname+"','"+Pname+"','"+Period+"','"+Locality+"','"+City+"','"+SUT+"','"+Phone_no+"','"+Email_ID+"','"+WebSite+"')");
					
					System.out.println("One Record Saved Successfully");
					dlg.showMessageDialog(f1,"One Record Saved Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Save Records !");
				dlg.showMessageDialog(f1,"Unable to Save Records !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnNext)
		{
			try
			{
				
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"Last Record .Unable To Go Next !","EIPRS",1);
				}
				else
				{
					rs.next();			
					Cname=rs.getString("Cname");
					Pname=rs.getString("Pname");
					Period=rs.getString("Period");
					Locality=rs.getString("Locality");
					City=rs.getString("City");
					SUT=rs.getString("state");
					Phone_no=rs.getString("Phone");
					Email_ID=rs.getString("Email");
					WebSite=rs.getString("WebSite");

					txtCName.setText(Cname);
					txtPName.setText(Pname);
					txtPd.setText(Period);
					txtLoc.setText(Locality);
					txtCt.setText(City);
					cmbSUT.setSelectedItem(SUT);
					txtPh.setText(Phone_no);
					txtEml.setText(Email_ID);
					txtWeb.setText(WebSite);
				}
			}
			catch(SQLException se)
			{
				dlg.showMessageDialog(f1,"Unable to go Next !","EIPRS",1);
				System.out.println("Unable to go Next !"+se);
			}
		}
		else if(ae.getSource()==btnPrevious)
		{
			try
			{
				if(rs.isFirst())
				{
					dlg.showMessageDialog(f1,"First Record .Unable To Go Previous !","EIPRS",1);
				}
				else
				{
					rs.previous();
					Cname=rs.getString("Cname");
					Pname=rs.getString("Pname");
					Period=rs.getString("Period");
					Locality=rs.getString("Locality");
					City=rs.getString("City");
					SUT=rs.getString("state");
					Phone_no=rs.getString("Phone");
					Email_ID=rs.getString("Email");
					WebSite=rs.getString("WebSite");

					txtCName.setText(Cname);
					txtPName.setText(Pname);
					txtPd.setText(Period);
					txtLoc.setText(Locality);
					txtCt.setText(City);
					cmbSUT.setSelectedItem(SUT);
					txtPh.setText(Phone_no);
					txtEml.setText(Email_ID);
					txtWeb.setText(WebSite);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Go Previous");
				dlg.showMessageDialog(f1,"Unable to go Previous !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnSearch)
		{
			Cname=dlg.showInputDialog(f1,"Enter College Name:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("select * from eiprs_tblCollege where Cname='"+Cname+"'");
				if(rsSearch.next())
				{
					Cname=rsSearch.getString("Cname");
					Pname=rsSearch.getString("Pname");
					Period=rsSearch.getString("Period");
					Locality=rsSearch.getString("Locality");
					City=rsSearch.getString("City");
					SUT=rsSearch.getString("state");
					Phone_no=rsSearch.getString("Phone");
					Email_ID=rsSearch.getString("Email");
					WebSite=rsSearch.getString("WebSite");

					txtCName.setText(Cname);
					txtPName.setText(Pname);
					txtPd.setText(Period);
					txtLoc.setText(Locality);
					txtCt.setText(City);
					cmbSUT.setSelectedItem(SUT);
					txtPh.setText(Phone_no);
					txtEml.setText(Email_ID);
					txtWeb.setText(WebSite);
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
			Cname=txtCName.getText();
			try
			{
				if(txtCName.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure to Delete ?","Alert",1);
					if(choice==0)//0 means Yes & 1 for No & 2 means Cancel in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete From eiprs_tblcollege where Cname='"+Cname+"'");
						dlg.showMessageDialog(f1,"One Record Deleted Successfully","EIPRS",3);
					}
				}
				else
				{
					dlg.showMessageDialog(f1,"Blank Record Can't be Deleted...!","EIPRS",3);
				}
			}
			catch(SQLException se){
				System.out.println("Unable to Delete. Try again later"+se);
				dlg.showMessageDialog(f1,"Unable to Delete. Try again later","EIPRS",2);
			}
		}
		else if(ae.getSource()==btnUpdate)
		{	
			Cname=txtCName.getText();
			Pname=txtPName.getText();
			Period=txtPd.getText();
			Locality=txtLoc.getText();
			City=txtCt.getText();
			SUT=cmbSUT.getSelectedItem().toString();
			Phone_no=txtPh.getText();
			Email_ID=txtEml.getText();
			WebSite=txtWeb.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				if(Cname.length()==0 ||Pname.length()==0 || Period.length()==0 ||Locality.length()==0 || City.length()==0 || SUT.length()==0 || Phone_no.length()==0 || Email_ID.length()==0 || WebSite.length()==0)
				{
					dlg.showMessageDialog(f1,"Blank Statement Can't Update","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblcollege set CName='"+Cname+"',Pname='"+Pname+"',Period='"+Period+"',Locality='"+Locality+"',City='"+City+"',State='"+SUT+"',Phone='"+Phone_no+"',Email='"+Email_ID+"',Website='"+WebSite+"' where Email='"+Email_ID+"'");
					
					dlg.showMessageDialog(f1,"One Record Updated Successfully","EIPRS",1);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Update"+se);
				dlg.showMessageDialog(f1,"Unable to Update","EIPRS",1);

			}
		}
	}
}
class Eiprs_College
{
	public static void main(String []args){
		Eiprs_CollegeApp ec;
		ec=new Eiprs_CollegeApp();
	}
}