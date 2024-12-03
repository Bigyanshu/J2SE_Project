import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.sql.*;

class SalarySlipApp implements ActionListener
{
	JFrame f1;
	JPanel p1,p2,p3,p4,p5,p6;
	GridLayout gl51,gl74,gl38,gl61,gl11;
	FlowLayout flw;
	JLabel lHead,lOrgn,lLocality,lCity,lState,lAllow,lDeduc;
	JLabel lSlipNo,lSlipNoVal,lDate,lDateVal,lMonName,lMonNameVal,lYear,lYearVal,lSID,lSIDVal,lSName,lSNameVal,lGen,lGenVal,lDept,lDeptVal,lDegn,lDegnVal,lBSalary,lBSalaryVal,lDa,lDaVal,lAmt1,lAmt1Val,lPf,lPfVal,lAmt2,lAmt2Val,lHra,lHraVal,lAmt3,lAmt3Val,lTax,lTaxVal,lAmt4,lAmt4Val,lGSal,lGSalVal,lNSal,lNSalVal,lWord,lTail;
	JButton btnCancel;
	Box vb,hb;
	
	Font fnt1,fnt2,fnt3;
	int slno,rw,tot;
	Connection conn;
	JTable tblData;
	JScrollPane jspData;
	Statement stmtSelect;
	ResultSet rs;
	JOptionPane dlg;
	String SlipNum,Date,MonthOf,Year,Staff_ID,StaffName,Gender,Department,Designation,BasicSalary,Da,Amount1,		Pf,Amount2,Hra,Amount3,Tax,Amount4,GrossSalary,NetSalary;
	String Word1,Word2;
	
	String ones[]={"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
	String tens[]={"Ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninty"};
	String elevs[]={"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	String wds="";
	int n;
	int d1,d2,d3,d4,d5,d6;
	
	public SalarySlipApp()
	{
		f1=new JFrame("Report of Staff Salary Slip Information");
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		
		gl51=new GridLayout(5,1);
		gl74=new GridLayout(7,4);
		gl38=new GridLayout(3,8);
		gl61=new GridLayout(6,1);
		gl11=new GridLayout(1,1);
		
		flw=new FlowLayout();
		
		lHead=new JLabel("Staff Salary Slip Information",JLabel.CENTER);//JLabel.CENTER
		fnt1=new Font("Copperplate Gothic Bold",Font.BOLD,20);
		lHead.setFont(fnt1);
		lHead.setForeground(Color.red);
		
		lOrgn=new JLabel("    BP University of Technology & Management",JLabel.CENTER);
		lLocality=new JLabel("    Bhubaneswar",JLabel.CENTER);
		lCity=new JLabel("    Khurda-753001",JLabel.CENTER);
		lState=new JLabel("    Odisha",JLabel.CENTER);
		
		lSlipNo=new JLabel("Slip No. :");
		lSlipNoVal=new JLabel("");
		lDate=new JLabel("Date:");
		lDateVal=new JLabel("");
		lMonName=new JLabel("Month Name :");
		lMonNameVal=new JLabel("");
		lYear=new JLabel("Year :");
		lYearVal=new JLabel("");
		lSID=new JLabel("Staff ID :");
		lSIDVal=new JLabel("");
		lSName=new JLabel("Staff Name :");
		lSNameVal=new JLabel("");
		lGen=new JLabel("Gender :");
		lGenVal=new JLabel("");
		lDept=new JLabel("Department :");
		lDeptVal=new JLabel("");
		lDegn=new JLabel("Designaton :");
		lDegnVal=new JLabel("");
		lBSalary=new JLabel("Basic Salary :");
		lBSalaryVal=new JLabel("");
		lAllow=new JLabel("Allowances :");
		fnt2=new Font("Times New Roman",Font.BOLD,20);
		lAllow.setFont(fnt2);
		lAllow.setForeground(Color.red);
		lDa=new JLabel("D.A. %ge :");
		lDaVal=new JLabel("");
		lAmt1=new JLabel("Amount :");
		lAmt1Val=new JLabel("");
		lDeduc=new JLabel("Deductions :");
		fnt3=new Font("Times New Roman",Font.BOLD,20);
		lDeduc.setFont(fnt3);
		lDeduc.setForeground(Color.red);
		lPf=new JLabel("P.F. %ge :");
		lPfVal=new JLabel("");
		lAmt2=new JLabel("Amount :");
		lAmt2Val=new JLabel("");
		lHra=new JLabel("H.R.A. %ge :");
		lHraVal=new JLabel("");
		lAmt3=new JLabel("Amount :");
		lAmt3Val=new JLabel("");
		lTax=new JLabel("TAX %ge :");
		lTaxVal=new JLabel("");
		lAmt4=new JLabel("Amount :");
		lAmt4Val=new JLabel("");
		lGSal=new JLabel("Gross Salary :");
		lGSalVal=new JLabel("");
		lNSal=new JLabel("Net Salary :");
		lNSalVal=new JLabel("");
		lWord=new JLabel("Rupees In Word...U Must to Convert...!");
		lWord.setForeground(Color.red);
		lTail=new JLabel("B.P. University of Technology & Management",JLabel.RIGHT);
		lTail.setFont(fnt3);
	//Button Declaration
		btnCancel=new JButton("Close");
		btnCancel.setMnemonic('X');
		btnCancel.addActionListener(this);
		btnCancel.setToolTipText("Close The Report");
		btnCancel.setForeground(Color.red);
		
		vb=Box.createVerticalBox();
		hb=Box.createHorizontalBox();
		
	//Set Panel
		p1.setLayout(gl51);
		p1.add(lHead);
		p1.add(lOrgn);
		p1.add(lLocality);
		p1.add(lCity);
		p1.add(lState);
		
		p2.setLayout(gl74);
		p2.add(lSlipNo);
		p2.add(lSlipNoVal);
		p2.add(lDate);
		p2.add(lDateVal);
		p2.add(lMonName);
		p2.add(lMonNameVal);
		p2.add(lYear);
		p2.add(lYearVal);
		p2.add(lSID);
		p2.add(lSIDVal);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lSName);
		p2.add(lSNameVal);
		p2.add(lGen);
		p2.add(lGenVal);
		p2.add(lDept);
		p2.add(lDeptVal);
		p2.add(lDegn);
		p2.add(lDegnVal);
		p2.add(lBSalary);
		p2.add(lBSalaryVal);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(lAllow);
		p2.add(new JLabel(""));
		p2.add(lDeduc);
		p2.add(new JLabel(""));
		
		p3.setLayout(gl38);
		p3.add(lDa);
		p3.add(lDaVal);
		p3.add(lAmt1);
		p3.add(lAmt1Val);
		p3.add(lPf);
		p3.add(lPfVal);
		p3.add(lAmt2);
		p3.add(lAmt2Val);
		p3.add(lHra);
		p3.add(lHraVal);
		p3.add(lAmt3);
		p3.add(lAmt3Val);
		p3.add(lTax);
		p3.add(lTaxVal);
		p3.add(lAmt4);
		p3.add(lAmt4Val);
		p3.add(lGSal);
		p3.add(lGSalVal);
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(lNSal);
		p3.add(lNSalVal);
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));

		p4.setLayout(flw);
		p4.add(lWord);
		//p4.add(new Border());
		//p4.setBorder(BorderFactory.createLineBorder(Color.black););
		
		p5.setLayout(gl11);
		p5.add(lTail);
		
		p6.setLayout(flw);
		p6.add(btnCancel);
		
		vb.add(Box.createVerticalStrut(10));
		vb.add(p2);
		vb.add(p3);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p4);
		vb.add(p5);
		vb.add(Box.createVerticalStrut(10));
		vb.add(p6);
		vb.add(Box.createVerticalStrut(10));
		
		hb.add(Box.createHorizontalStrut(20));
		hb.add(vb);
		hb.add(Box.createHorizontalStrut(20));
		
		doConnect();
		
	//Set Frame
		f1.setLayout(new BorderLayout());
		f1.add(p1,BorderLayout.NORTH);
		f1.add(hb,BorderLayout.CENTER);
		f1.add(p6,BorderLayout.SOUTH);
		f1.setBounds(0,0,1280,750);
		f1.setVisible(true);
	}
	
	public String toWords(int n)
	{
		d1=d2=d3=d4=d5=d6=0;
		
		d6=n%10;
		n=n/10;
		d5=n%10;
		n=n/10;
		d4=n%10;
		n=n/10;
		d3=n%10;
		n=n/10;
		d2=n%10;
		n=n/10;
		d1=n%10;
		
		
		if(d1>0)
			wds=wds+ones[d1-1]+" Lacs ";
		if(d2==0 && d3>0)
			wds=wds+ones[d3-1]+" Thousands ";
		else if(d2>0 && d3==0)
			wds=wds+tens[d2-1]+" Thousands ";
		else if(d2==1 && d3>0)
			wds=wds+elevs[d3-1]+" Thousands ";
		else if(d2>1 && d3>0)
			wds=wds+tens[d2-1]+ones[d3-1]+" Thousands ";
		
		if(d4>0)
			wds=wds+ones[d4-1]+" Hundreds ";
		
		if(d5==0 && d6>0)
			wds=wds+ones[d6-1];
		else if(d5>0 && d6==0)
			wds=wds+tens[d5-1];
		else if(d5==1 && d6>0)
			wds=wds+elevs[d6-1];
		else if(d5>1 && d6>0)
			wds=wds+tens[d5-1]+ones[d6-1];

		return(wds);
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
			SlipNum=dlg.showInputDialog(f1,"Enter SlipNo :","Detail Report",1);
			//lHead.setText("Staff Salary Slip Information ["+SlipNum+"]");
			rs=stmtSelect.executeQuery("select * from eiprs_tblsalslip where SlipNum='"+SlipNum+"'");
			
			slno=1;
			rw=0;
			if(rs.next())
			{
			//String =("String")
				Date=rs.getString("Date");
				MonthOf=rs.getString("MonthOf");
				Year=rs.getString("Year");
				Staff_ID=rs.getString("Staff_ID");
				StaffName=rs.getString("StaffName");
				Gender=rs.getString("Gender");
				Department=rs.getString("Department");
				Designation=rs.getString("Designation");
				BasicSalary=rs.getString("BasicSalary");
				Da=rs.getString("Da");
				Amount1=rs.getString("Amount1");
				Pf=rs.getString("Pf");
				Amount2=rs.getString("Amount2");
				Hra=rs.getString("Hra");
				Amount3=rs.getString("Amount3");
				Tax=rs.getString("Tax");
				Amount4=rs.getString("Amount4");
				GrossSalary=rs.getString("GrossSalary");
				NetSalary=rs.getString("NetSalary");
				String ssal;
                ssal=NetSalary.substring(0,NetSalary.indexOf(".")); 
				int sal;
				sal=Integer.parseInt(ssal);
	          wds=toWords(sal);
			//set labels value.(String) 
				lSlipNoVal.setText(SlipNum);
				lDateVal.setText(Date);
				lMonNameVal.setText(MonthOf);
				lYearVal.setText(Year);
				lSIDVal.setText(Staff_ID);
				lSNameVal.setText(StaffName);
				lGenVal.setText(Gender);
				lDeptVal.setText(Department);
				lDegnVal.setText(Designation);
				lBSalaryVal.setText(BasicSalary);
				lDaVal.setText(Da);
				lAmt1Val.setText(Amount1);
				lPfVal.setText(Pf);
				lAmt2Val.setText(Amount2);
				lHraVal.setText(Hra);
				lAmt3Val.setText(Amount3);
				lTaxVal.setText(Tax);
				lAmt4Val.setText(Amount4);
				lGSalVal.setText(GrossSalary);//(Float.toString(lGSalVal));
				lNSalVal.setText(NetSalary);//(Float.toString(lNSalVal));
				wds="--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Rupees "+wds+" Only  ";
				lWord.setText(wds);
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
		if(ae.getSource()==btnCancel)
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
class SalarySlip
{
	public static void main(String[] s)
	{
		SalarySlipApp rp=new SalarySlipApp();
	}
}