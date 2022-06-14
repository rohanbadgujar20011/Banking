

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
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con= DbConnection.connect();
		int ac=Integer.parseInt(request.getParameter("ac"));
		int pin=Integer.parseInt(request.getParameter("pin"));
		int ac_2=Integer.parseInt(request.getParameter("ac_2"));
		int amount=Integer.parseInt(request.getParameter("amount"));
		if(amount>0){
			
		
		try{
			String s="select balance from users where ac_id=? and pin=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(s);
			pstmt.setInt(1, ac);
			pstmt.setInt(2,pin);
			ResultSet rt=pstmt.executeQuery();
			if(rt.next()){
		
				int old_balance=rt.getInt("balance");
				if(amount<old_balance){
				old_balance=old_balance-amount;
				

				Statement stmt = con.createStatement();
				int i = stmt.executeUpdate("update users set balance = '"+old_balance+"' where ac_id='"+ac+"' and pin='"+pin+"'");
				if(i>0){
					
					try{
						String s1="select balance from users where ac_id=?";
						PreparedStatement pstmt1=(PreparedStatement) con.prepareStatement(s1);
						pstmt1.setInt(1, ac_2);
						
						ResultSet rt1=pstmt1.executeQuery();
						if(rt1.next()){
							int transfer_am=rt1.getInt("balance");
							transfer_am=transfer_am+amount;

							Statement stmt1 = con.createStatement();
							int j = stmt1.executeUpdate("update users set balance = '"+transfer_am+"' where ac_id='"+ac_2+"'");
							if(j>0){
								System.out.println("Update Successfull");
								response.sendRedirect("index2.html");
							}
							else{
								System.out.println("not");
							}
						}
					
				
						
					
					
				}catch(Exception e){
					e.printStackTrace();
					
				}

					
				}
				else{
					System.out.println("not");
				}
			}
			else{
					System.out.println("You dont have enough Balance");
					response.sendRedirect("transfer.html");
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
			System.out.println("Enetr Valid Amount");
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
