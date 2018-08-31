package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Users;

public class UsersDao {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	
	public Users getUser(String name, String pass) {
		ArrayList<Users> listUsers = new ArrayList<>();
		Users user = null;
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Users users = new Users(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("id_roles"), "", rs.getString("email"));
				listUsers.add(users);
			}
			for (Users users : listUsers) {
				if(name.equals(users.getUsername()) && pass.equals(users.getPassword())){
					user = users;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return user;
		
	}

	public ArrayList<Users> getUsers() {
		ArrayList<Users> listUsers = new ArrayList<>();
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM user INNER JOIN roles ON user.id_roles = roles.id_roles ORDER BY id_user DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Users users = new Users(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("user.id_roles"), rs.getString("name"), rs.getString("email"));
				listUsers.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return listUsers;
	}

	public int addUser(Users user) {
		int kq = 0;
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "INSERT INTO user(username, password, fullname, email, id_roles) VALUES (?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getEmail());
			pst.setInt(5, user.getId_roles());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public Users getUserById(int uid) {
		Users users = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM user INNER JOIN roles ON user.id_roles = roles.id_roles ORDER BY id_user DESC";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()) {
				users = new Users(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("user.id_roles"), rs.getString("name"), rs.getString("email"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return users;
	}

	public int editUser(Users user) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE user SET  password = ?, fullname = ?, id_roles =  ? WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getFullname());
			pst.setInt(3, user.getId_roles());
			pst.setInt(4, user.getId());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int deleteUser(int uid) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "DELETE FROM user WHERE id_user = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public ArrayList<Users> getUserByStatis() {
		ArrayList<Users> listUsers = new ArrayList<>();
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM user ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Users users = new Users(rs.getInt("id_user"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("user.id_roles"), "", rs.getString("email"), rs.getTimestamp("create_at"));
				listUsers.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return listUsers;
	}

}
