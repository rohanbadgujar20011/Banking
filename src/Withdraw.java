

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  ac=Integer.parseInt(request.getParameter("ac"));
	    int pin=Integer.parseInt(request.getParameter("pin"));
		int am=Integer.parseInt(request.getParameter("amount"));
		if(am>0){
			Connection con=DbConnection.connect();
			
		try{
			String s="select balance from users where ac_id=? and pin=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(s);
			pstmt.setInt(1, ac);
			pstmt.setInt(2,pin);
			ResultSet rt=pstmt.executeQuery();
			if(rt.next()){
				int old_balance=rt.getInt("balance");
				if(old_balance> am){
					
				
				am =old_balance-am;

				Statement stmt = con.createStatement();
				int i = stmt.executeUpdate("update users set balance = '"+am+"' where ac_id='"+ac+"' and pin='"+pin+"'");
				if(i>0){
					System.out.println("Update Successfull ");
					response.sendRedirect("index2.html");
				}
				else{
					System.out.println("not");
				}
				}
				else{
					response.sendRedirect("withdraw.html");
				}
			}
			else{
				System.err.println("Check your account number and Pin!!");
			
			}
			
		
		
	}catch(Exception e){
		e.printStackTrace();
		
	}

}
		else{
			System.out.println("Enter the valid amount");
		}
	
		
				
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
