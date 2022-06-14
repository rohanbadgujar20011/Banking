

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BankingStart
 */
public class BankingStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankingStart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int acc_no=Integer.parseInt(request.getParameter("ac"));
		String fname=request.getParameter("fname");
		String mname=request.getParameter("mname");
		String lname=request.getParameter("lname");
		String mnunmber=request.getParameter("mnunmber");
		String pin=request.getParameter("pin");
		String bal=request.getParameter("bal");
		Connection con=DbConnection.connect();
		try{
			String s="insert into users values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(s);
			pstmt.setInt(1, acc_no);
			pstmt.setString(2,fname);
			pstmt.setString(3,mname);
			pstmt.setString(4,lname);
			pstmt.setString(5,mnunmber);
			pstmt.setString(6,pin);
			pstmt.setString(7,bal);
			int i=pstmt.executeUpdate();
			if(i>0){
				
				System.out.println("Inserted Succesfully!!!");
				response.sendRedirect("index.html");
			}
			}catch(Exception e){
				e.printStackTrace();
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
