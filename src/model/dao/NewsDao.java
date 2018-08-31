package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectionDBLibrary;
import model.bean.Category;
import model.bean.News;

public class NewsDao {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public ArrayList<News> getNewLimit(int start, int end) {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat " + 
		"LEFT JOIN user ON news.id_user_create = user.id_user WHERE status = 1 ORDER BY news.id_news DESC LIMIT ?, ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, start);
			pst.setInt(2, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}

		return listNews;
	}
	
	public ArrayList<News> getNewsTopPoint() {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE status = 1 ORDER BY point DESC LIMIT 0, 10";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"), rs.getInt("id_user_create"), null, rs.getInt("point"), rs.getTimestamp("create_at"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}

		return listNews;
	}
	

	public ArrayList<News> getNewByIdCat(int cid, int start, int end) {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1 LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2, start);
			pst.setInt(3, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"), rs.getInt("id_user_create"), null, rs.getInt("point"), rs.getTimestamp("create_at"));
				
				listNews.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}

		return listNews;
	}
	
	public ArrayList<News> getAllNewByIdCat(int cid) {
		ArrayList<News> listNews = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"), rs.getInt("id_user_create"), null, rs.getInt("point"), rs.getTimestamp("create_at"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}

		return listNews;
	}

	public News getNewByIdNews(int nid) {
		News news = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat " + 
				"LEFT JOIN user ON news.id_user_create = user.id_user WHERE  news.id_news = ?";
					
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, nid);
			rs = pst.executeQuery();
			if (rs.next()) {
				news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return news;
	}

	public ArrayList<News> getNewsSameIdCat(int cid, int  nid) {
		ArrayList<News> list = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND status = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), null, rs.getInt("status"), rs.getInt("id_user_create"), null, rs.getInt("point"), rs.getTimestamp("create_at"));
				
				if ((news.getId() != nid) && (list.size() < 3)) {
					list.add(news);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public News getNewByIdNewDefault() {
		News news = null;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat " + 
				"LEFT JOIN user ON news.id_user_create = user.id_user WHERE status = 1 ORDER BY news.id_news DESC LIMIT 0, 1";
				
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return news;
	}

	public ArrayList<News> getNews() {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat " 
				+ "LEFT JOIN user ON news.id_user_create = user.id_user WHERE status = 1 ORDER BY news.id_news ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}
	
	

	public int addNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "INSERT INTO news(name, preview, detail, id_cat, picture, status, id_user_create, point) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getId_cat());
			pst.setString(5, news.getPicture());
			pst.setInt(6, 1);
			pst.setInt(7, news.getId_user());
			pst.setInt(8, news.getPoint());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int deleteNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET status = 0 WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, news.getId());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public int editNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET name = ?, preview = ?, detail = ?, id_cat = ?, picture = ? WHERE id_news = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPreview());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getId_cat());
			pst.setString(5, news.getPicture());
			pst.setInt(6, news.getId());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}
	
	public int editPointNews(News news) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET point = ? WHERE id_news = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, news.getPoint());
			pst.setInt(2, news.getId());
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public ArrayList<News> getNewsSearch(String[] arrsearch) {
		ArrayList<News> list = new ArrayList<>();

		conn = ConnectionDBLibrary.getConnection();

		try {
			for (int i = 0; i < arrsearch.length; i++) {
				String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat LEFT JOIN user ON news.id_user_create = user.id_user WHERE status = 1 AND (news.name LIKE '% " + arrsearch[i] +" %' OR news.name LIKE '" + arrsearch[i] + " %')";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
					if(checkNews(list, news) == false) {
						list.add(news);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}

		return list;
	}
	
	public static boolean checkNews(ArrayList<News> list, News news) {
		for (News news1 : list) {
			if(news1.getId() == news.getId()) return true;
		}
		return false;
	}

	public ArrayList<News> getNewsByName(int id, String name) {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT *, news.name as nname, category.name as cname FROM news LEFT JOIN category ON category.id_cat = news.id_cat " 
				+ "LEFT JOIN user ON news.id_user_create = user.id_user WHERE news.status = 1 AND news.name LIKE ? AND news.id_news != ? ";
		try {
			pst =  conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("nname"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), rs.getString("cname"), rs.getInt("status"), rs.getInt("id_user_create"), rs.getString("username"), rs.getInt("point"), rs.getTimestamp("create_at"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}

	public int NoShowNews(int cid) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET status = ? WHERE id_news = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, cid);
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public ArrayList<News> getNewByIdUser(int uid) {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * from news WHERE id_user_create = ?";
		try {
			pst =  conn.prepareStatement(sql);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), "", rs.getInt("status"), rs.getInt("id_user_create"), "", rs.getInt("point"), rs.getTimestamp("create_at"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}

	public int EditNewUserCreatAt(int id) {
		int kq = 0;
		conn = ConnectionDBLibrary.getConnection();
		String sql = "UPDATE news SET id_user_create = ? WHERE id_news = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setInt(2, id);
			kq = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, pst, conn);
		}
		return kq;
	}

	public ArrayList<News> getNewByStatis() {
		ArrayList<News> list = new ArrayList<>();
		conn = ConnectionDBLibrary.getConnection();
		String sql = "SELECT * from news";
		try {
			pst =  conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				News news = new News(rs.getInt("id_news"), rs.getString("name"), rs.getString("preview"), rs.getString("detail"), rs.getInt("id_cat"), rs.getString("picture"), "", rs.getInt("status"), rs.getInt("id_user_create"), "", rs.getInt("point"), rs.getTimestamp("create_at"), rs.getTimestamp("die_at"));
				list.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDBLibrary.close(rs, st, conn);
		}
		return list;
	}

	

}
