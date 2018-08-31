package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Comment;

public class CommentDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private java.sql.PreparedStatement pst;
	
	public ArrayList<Comment> getComments(){
		ArrayList<Comment> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT comments.id_cmt as id, comments.id_user as id_user, user.username as username, comments.id_news as id_news, news.name as name, comments.content, comments.create_at, comments.status as status FROM comments INNER JOIN news ON comments.id_news = news.id_news INNER JOIN user ON comments.id_user = user.id_user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Comment cmt =  new Comment(rs.getInt("id"), rs.getInt("id_user"), rs.getString("username"), rs.getInt("id_news"), rs.getString("name"), rs.getString("content"), rs.getTimestamp("create_at"), rs.getInt("status"));
				list.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		
		return list;
	}

	public int activeComment(int i, int id) {
		int kq = 0;
		 conn = ConnectionDBLibrary.getConnection();
		 String sql = "update comments set status = ? where id_cmt = ?";
		 try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, id);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
				 
		return kq;
	}

	public int deleteComment(int id) {
		int kq = 0;
		 conn = ConnectionDBLibrary.getConnection();
		 String sql = "update comments set status = ? where id_cmt = ?";
		 try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, id);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
				 
		return kq;
	}

	public int addComment(Comment cmt) {
		int kq = 0;
		 conn = ConnectionDBLibrary.getConnection();
		 String sql = "insert into comments(id_news, id_user, content, status) values(?, ?, ?, ?)";
		 try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cmt.getIdnews());
			pst.setInt(2, cmt.getIduser());
			pst.setString(3, cmt.getContent());
			pst.setInt(4, cmt.getStatus());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
				 
		return kq;
	}

	public ArrayList<Comment> getCommentsByIdNews(int id) {
		ArrayList<Comment> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT comments.id_cmt as id, comments.id_user as id_user, user.username as username, comments.id_news as id_news, news.name as name, comments.content, comments.create_at, comments.status as status FROM comments INNER JOIN news ON comments.id_news = news.id_news INNER JOIN user ON comments.id_user = user.id_user where comments.id_news = ? AND comments.status = 1 ORDER BY comments.id_cmt DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Comment cmt =  new Comment(rs.getInt("id"), rs.getInt("id_user"), rs.getString("username"), rs.getInt("id_news"), rs.getString("name"), rs.getString("content"), rs.getTimestamp("create_at"), rs.getInt("status"));
				list.add(cmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		
		return list;
	}
}
