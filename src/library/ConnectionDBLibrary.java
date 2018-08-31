package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ConnectionDBLibrary {
	private static final String URL = "jdbc:mysql://localhost:3306/shareit?useUnicode=yes&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		//nạp driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tao chuỗi kết nối
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs, Statement st, Connection cn) {
		close(rs);
		close(st);
		close(cn);
	}
	public static void close(ResultSet rs, PreparedStatement pst, Connection cn) {
		close(rs);
		close(pst);
		close(cn);
	}
}
