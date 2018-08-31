package model.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Adver;

public class AdverDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private java.sql.PreparedStatement pst;
	
	public ArrayList<Adver> getAdvers(){
		ArrayList<Adver> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM Adver";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Adver adver = new Adver(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getTimestamp("create_at"), rs.getInt("status"), rs.getInt("money"), rs.getString("preview"));
				
				list.add(adver);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return list;
	}

	public int activeComment(int i, int id) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "update adver set status = ? where id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, id);
			kq = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return kq;
	}

	public Adver getAdverById(int id) {
		Adver adver = new Adver();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM Adver WHere id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				adver = new Adver(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getTimestamp("create_at"), rs.getInt("status"), rs.getInt("money"), rs.getString("preview"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		
		return adver;
	}

	public ArrayList<Adver> getAdverByName(int id, String name) {
		ArrayList<Adver> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM Adver WHere status = 1 AND name LIKE ? AND id != ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Adver adver = new Adver(rs.getInt("id"), rs.getString("name"), rs.getString("picture"), rs.getString("link"), rs.getTimestamp("create_at"), rs.getInt("status"), rs.getInt("money"), rs.getString("preview"));
				System.out.println(adver.getName());
				list.add(adver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return list;
	}

	public int editAdver(Adver adver2) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "update adver set name = ?, link = ?, money = ?, picture = ? where id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, adver2.getName());
			pst.setString(2, adver2.getLink());
			pst.setInt(3, adver2.getMoney());
			pst.setString(4, adver2.getPicture());
			pst.setInt(5, adver2.getId());
			kq = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return kq;
	}

	public int addAdver(Adver adver) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "insert into adver(name, link, money, picture, status, preview) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, adver.getName());
			pst.setString(2, adver.getLink());
			pst.setInt(3, adver.getMoney());
			pst.setString(4, adver.getPicture());
			pst.setInt(5, 0);
			pst.setString(6, adver.getPreview());
			kq = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return kq;
	}

	public ArrayList<Adver> getAdversStatic() {
		ArrayList<Adver> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM Adver";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Adver adver = new Adver(rs.getInt("id"), "", "", "", rs.getTimestamp("create_at"), rs.getInt("status"), rs.getInt("money"), "", rs.getTimestamp("die_at"));
				list.add(adver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return list;
	}




}
