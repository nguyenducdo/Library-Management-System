package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBConnection {
	public static String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
	public static Connection open() {
		Connection cnn = null;
		try {
			String driver = DEFAULT_DRIVER;
			String url = "jdbc:mysql://localhost:3306/qltv";
			String user = "root";
			String pass = "1098";
			if(driver == null) driver = DEFAULT_DRIVER;
			Class.forName(driver);
			cnn = (Connection) DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot open database access");
		}
		
		return cnn;
			
	}
	
	public static void close(ResultSet rs, PreparedStatement ps, Connection cnn) {
		try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close db access");
        }
	}
	
}
