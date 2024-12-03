import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Eiprs_DeptApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3;
	GridLayout gl42,gl31;
	JButton btnClear,btnSave,btnNext,btnPrevious,btnSearch,btnDelete,btnUpdate,btnClose;
	JLabel lblDCode,lblDName,lblPhone,lblHOD,lblHead;
	JTextField txtDName,txtDCode,txtPhone,txtHOD;
	FlowLayout flw;
	ImageIcon imgNext,imgSave,imgPrevious,imgClear,imgSearch,imgDelete,imgClose,imgUpdate;
	Color red,black;
	Box hb,vb;
	
	Connection conn;
	Statement stmtInsert,stmtSelect,stmtDelete,stmtUpdate,stmtSearch,stmtDupDcode;
	ResultSet rs,rsSearch,rsDupDcode;
	Font fnt1,fnt2;
	JOptionPane dlg;
	int choice;
	String Dcode,Dname,Phone,HOD; //CLG;
	//Table DCode,DName,DPhone,hod; Clg;
	
//Constructor start
	public Eiprs_DeptApp()
	{
		f1=new JFrame("BPUTM Department Register");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		
		gl42=new GridLayout(4,2);
		gl31=new GridLayout(3,1);
		
		flw=new FlowLayout();
		
		dlg=new JOptionPane();
		
	//Color 
		red=new Color(125,0,0);
		black=new Color(200,200,200);
		
	//Label Declaration
		lblHead=new JLabel("DEPARTMENT  INFORMATION");
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,26);
		fnt2=new Font("Copperplate Gothic Bold",Font.PLAIN,26);
		lblHead.setFont(fnt1);
		lblHead.setFont(fnt2);
		lblHead.setForeground(Color.red);
		lblHead.setBackground(Color.black);
		
		lblDCode=new JLabel("Department Code:");
		lblDName=new JLabel("Department Name:");
		lblPhone=new JLabel("Phone Number:");
		lblHOD=new JLabel("Head of Department:");
		
		/*lblDCode.setForeground(Color.white);
		lblDName.setForeground(Color.white);
		lblPhone.setForeground(Color.white);
		lblHOD.setForeground(Color.white);*/
		
	//TextField Declaration
		txtDCode=new JTextField(50);
		txtDName=new JTextField(50);
		txtPhone=new JTextField(50);
		txtHOD=new JTextField(50);
		
		
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
				//btnClear.setBackground(Color.red);

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
		
		txtDCode.setToolTipText("Enter Department Code...");
		txtDName.setToolTipText("Enter Department Name...");
		txtPhone.setToolTipText("Enter Phone Number of Department...");
		txtHOD.setToolTipText("Enter HOD Name...");
		
	//Adding Action Listener in Button & TextField
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnClose.addActionListener(this);
		
		txtDCode.addActionListener(this);
		txtDName.addActionListener(this);
		txtPhone.addActionListener(this);
		txtHOD.addActionListener(this);

		
	//Horizontal & Vertical Box oblect creating
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//set Panel
		p1.setLayout(flw);
		//p1.setBackground(Color.black);//--> It works
		p1.add(lblHead);
		
		p2.setLayout(gl42);
		//p2.setBackground(Color.black);//--> It works
		p2.add(lblDCode);
		p2.add(txtDCode);
		p2.add(lblDName);
		p2.add(txtDName);
		p2.add(lblPhone);
		p2.add(txtPhone);
		p2.add(lblHOD);
		p2.add(txtHOD);
		
		p3.setLayout(flw);
		//p3.setBackground(Color.black);//--> It works
		p3.add(btnClear);
		p3.add(btnSave);
		p3.add(btnNext);
		p3.add(btnPrevious);
		p3.add(btnDelete);
		p3.add(btnSearch);
		p3.add(btnUpdate);
		p3.add(btnClose);
		
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p1);
		vb.add(p2);
		vb.add(Box.createVerticalStrut(20));
		vb.add(p3);
		vb.add(Box.createVerticalStrut(5));
		vb.setForeground(Color.black);

		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		//hb.setBackground(Color.black);
		
	//Set Frame
		f1.setLayout(new BorderLayout());
		f1.add(hb,BorderLayout.CENTER);
		f1.setForeground(Color.black);
		//f1.setResizable(false);//Never be Full Screen
		doConnect();
		f1.setBounds(230,210,800,400);
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
			rs=stmtSelect.executeQuery("select * from eiprs_tblDepartment");
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
			if(txtDCode.getText().length()>0)
			{
				txtDCode.setText("");
				txtDName.setText("");
				txtPhone.setText("");
				txtHOD.setText("");
				
				dlg.showMessageDialog(f1,"Cleared the Records","BPUTM",0);
				txtDCode.requestFocus();
			}
		}
			else if(ae.getSource()==txtDCode)
			{
				txtDName.requestFocus();
			}
			else if(ae.getSource()==txtDName)
			{
				txtPhone.requestFocus();
			}
			else if(ae.getSource()==txtPhone)
			{
				txtHOD.requestFocus();
			}
		
		else if(ae.getSource()==btnClose)
		{
			//dlg.showMessageDialog(f1,"Sure to Close The Program...?","Alert",2);
			try
			{
				conn.close();
			}
			catch(SQLException se)
			{
				System.out.println("Error in Closing The Programs...");
			}
			f1.setVisible(false);
			f1.dispose();
		}
		else if(ae.getSource()==btnSave)
		{
			Dcode=txtDCode.getText();
			Dname=txtDName.getText();
			Phone=txtPhone.getText();
			HOD=txtHOD.getText();
			try{
				stmtInsert=conn.createStatement();
				stmtDupDcode=conn.createStatement();
				rsDupDcode=stmtDupDcode.executeQuery("select * from eiprs_tblDepartment where Dcode='"+Dcode+"'");
				if(rsDupDcode.next() || Dcode.length()==0 || Dname.length()==0 || Phone.length()==0 || HOD.length()==0)
				{
					dlg.showMessageDialog(f1,"Duplicate Dcode or Blank Records","EIPRS",2);
				}
				else
				{
					stmtInsert.executeUpdate("insert into eiprs_tblDepartment values('"+Dcode+"','"+Dname+"','"+	Phone+"','"+HOD+"')");
				
					dlg.showMessageDialog(f1,"One Record Saved Successfully","EIPRS",1);
				}
			}
			catch(SQLException se){
				System.out.println("Unable to Save Records !"+se);
				dlg.showMessageDialog(f1,"Unable to Save Records !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnNext)
		{
			try{
				if(rs.isLast())
				{
					dlg.showMessageDialog(f1,"No More  Records !","EIPRS",1);	
				}
				else
				{
					rs.next();
				//String name--->Table Name
					Dcode=rs.getString("Dcode");
					Dname=rs.getString("Dname");
					Phone=rs.getString("Phone");
					HOD=rs.getString("HOD");
				
					txtDCode.setText(Dcode);
					txtDName.setText(Dname);
					txtPhone.setText(Phone);
					txtHOD.setText(HOD);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to go Next !"+se);
				dlg.showMessageDialog(f1,"No More  Records !","EIPRS",1);
			}
		}
		else if(ae.getSource()==btnPrevious)
		{
			try
			{
				if(rs.isFirst())
				{
					dlg.showMessageDialog(f1,"Unable to Go Previous. This is First Record","BPUTM",1);
				}
				else
				{
					rs.previous();
					Dcode=rs.getString("Dcode");
					Dname=rs.getString("Dname");
					Phone=rs.getString("Phone");
					HOD=rs.getString("HOD");

					txtDCode.setText(Dcode);
					txtDName.setText(Dname);
					txtPhone.setText(Phone);
					txtHOD.setText(HOD);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Go Previous");
				dlg.showMessageDialog(f1,"Unable to Go Previous. This is First Record","",1);

			}
		}
		else if(ae.getSource()==btnSearch)
		{
			Dcode=dlg.showInputDialog(f1,"Enter Department Code:","EIPRS",1);
			try
			{
				stmtSearch=conn.createStatement();
				rsSearch=stmtSearch.executeQuery("select * from eiprs_tblDepartment where DCode='"+Dcode+"'");
				if(rsSearch.next())
				{
					Dcode=rsSearch.getString("Dcode");
					Dname=rsSearch.getString("Dname");
					Phone=rsSearch.getString("Phone");
					HOD=rsSearch.getString("HOD");
				
					txtDCode.setText(Dcode);
					txtDName.setText(Dname);
					txtPhone.setText(Phone);
					txtHOD.setText(HOD);
				}
				else
				{
					dlg.showMessageDialog(f1,"Search Item Not Found...","EIPRS",2);
				}
			}
			catch(SQLException se)
			{
				System.out.println("Unable to Search"+se);
				dlg.showMessageDialog(f1,"Unable to Search","EIPRS",2);
			}
		}
		else if(ae.getSource()==btnDelete)
		{
			Dname=txtDName.getText();
			try
			{
				if(txtDCode.getText().length()>0)
				{
					choice=dlg.showConfirmDialog(f1,"Sure to Delete ?","Alert",1);
					if(choice==0)//0 means OK Button & 1 means Cancel Button in Delete Button
					{
						stmtDelete=conn.createStatement();
						stmtDelete.executeUpdate("Delete From eiprs_tblDepartment where DName='"+Dname+"'");
						dlg.showMessageDialog(f1,"One Record Deleted Successfully","EIPRS",3);
					}
					else
					{
						//System.out.println("Your Data Not Deleting"+se);
						dlg.showMessageDialog(f1,"ThankFul Data Not Deleting","EIPRS",2);
					}
				}
			}
			catch(SQLException se){
				dlg.showMessageDialog(f1,"Unable to Delete. Try again later","EIPRS",2);
			}
		}
		else if(ae.getSource()==btnUpdate)
		{	
			Dcode=txtDCode.getText();
			Dname=txtDName.getText();
			Phone=txtPhone.getText();
			HOD=txtHOD.getText();
			try
			{
				stmtUpdate=conn.createStatement();
				if(Dname.length()==0 || Dcode.length()==0 || Phone.length()==0 || HOD.length()==0)
				{ 
					dlg.showMessageDialog(f1,"Blank Statement Can't Update","EIPRS",1);
				}
				else
				{
					stmtUpdate.executeUpdate("Update eiprs_tblDepartment set Dname='"+Dname+"',Phone='"+Phone+"',HOD='"+HOD+"' where Dcode='"+Dcode+"'");
					
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
class Eiprs_Department
{
	public static void main(String []s)
	{
		Eiprs_DeptApp ed;
		ed=new Eiprs_DeptApp();
	}
}
