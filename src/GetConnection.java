

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	public static Connection getConnection(String driver, String url, 
			String username, String password) {
		
        // JDBCドライバのロード
        try {
        	Connection con = null;
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
        
	}
}
