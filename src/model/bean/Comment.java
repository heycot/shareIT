package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private int iduser;
	private String username;
	private int idnews;
	private String news;
	private String content;
	private Timestamp create_at;
	private int status;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdnews() {
		return idnews;
	}
	public void setIdnews(int idnews) {
		this.idnews = idnews;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	
	public Comment(int id, int iduser, String username, int idnews, String news, String content, Timestamp create_at,
			int status) {
		super();
		this.id = id;
		this.iduser = iduser;
		this.username = username;
		this.idnews = idnews;
		this.news = news;
		this.content = content;
		this.create_at = create_at;
		this.status = status;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
