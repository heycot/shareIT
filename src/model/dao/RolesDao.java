package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Roles;


public class RolesDao {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	
	public ArrayList<Roles> getRoles(){
		ArrayList<Roles> list = new ArrayList<>();
		
		conn = ConnectionDBLibrary.getConnection();
		String sql = "select * from roles where id_roles != 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Roles roles = new Roles(rs.getInt("id_roles"), rs.getString("name"));
				list.add(roles);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}

}
