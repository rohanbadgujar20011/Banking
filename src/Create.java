import java.util.*;
import java.sql.*;

public class Create {
	Scanner sc=new Scanner(System.in);
	int acc,balance;
	String name;
	
	void addUser(){
		Connection con=DbConnection.connect();
		Scanner sc=new Scanner(System.in);
		try{
			
			
			String s="insert into users values(?,?,?,?,?,?,?)";
			System.out.println("Enter the account no ");
			int a=sc.nextInt();
			System.out.println("Enter first name");
			String f_n=sc.next();
			System.out.println("Enter Middle name");
			String s_n=sc.next();
			System.out.println("Enter Last name");
			String l_n=sc.next();
			System.out.println("Enter Mobile Number");
			String m_n=sc.next();
			System.out.println("Enter Pin");
			String P_n=sc.next();
			System.out.println("Enter Balance");
			int sal=sc.nextInt();
			PreparedStatement pstmt=con.prepareStatement(s);
			pstmt.setInt(1, a);
			pstmt.setString(2,f_n);
			pstmt.setString(3,s_n);
			pstmt.setString(4,l_n);
			pstmt.setString(5,m_n);
			pstmt.setString(6,P_n);
			pstmt.setInt(7,sal);
			pstmt.executeUpdate();
			System.out.println("Inserted Succesfully!!!");
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}
	void viewUser(){
		int u,f=1;
		String v;
		
		Connection con=DbConnection.connect();
		try{
			String s="select * from users where ac_id=";
			System.out.println("Enter the account Number");
			u=sc.nextInt();
			System.out.println("Enter the pin");
			v=sc.next();
			
			s=s+u;
			s=s+" and pin="+v;
			System.out.println(s);
			
			
			Statement stmt=con.createStatement();
			
		
		ResultSet rt=stmt.executeQuery(s);
		while(rt.next()){
			System.out.println("\n\nID:"+rt.getInt(1)+"\nFirst Name:"+rt.getString(2)+"\nMiddle Name:"+rt.getString(3)+"\nLast Name:"+rt.getString(4)+"\nMobile_no:"+rt.getString(5)+"\nBalance:"+rt.getString(7) );
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	void checkBalance(){
		System.out.println(balance);
	}

}
