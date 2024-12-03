import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class SalWordApp
{
	String ones[]={"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
	String tens[]={"Ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninty"};
	String elevs[]={"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	String wds="";
	int n;
	int d1,d2,d3,d4,d5,d6;
	public SalWordApp()
	{
		n=54321;
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

		System.out.println(wds);
	}
}
class SalWord
{
	public static void main(String[] agrs)
	{
		SalWordApp fd=new SalWordApp();
	}
}