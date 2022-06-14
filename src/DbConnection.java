import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	static Connection connect()
	{
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sbibank", "root","");
			System.out.println("Connection Established.......");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
		
	}

}
