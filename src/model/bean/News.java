package model.bean;

import java.sql.Timestamp;

public class News {
	private int id;
	private String name;
	private String preview;
	private String detail;
	private int id_cat;
	private String picture;
	private String cname;
	private int status;
	private int id_user;
	private String username;
	private int point;
	private Timestamp die_at;
	
	
	public Timestamp getDie_at() {
		return die_at;
	}
	public void setDie_at(Timestamp die_at) {
		this.die_at = die_at;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private Timestamp creat_at;
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public Timestamp getCreat_at() {
		return creat_at;
	}
	public void setCreat_at(Timestamp creat_at) {
		this.creat_at = creat_at;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	public News(int id, String name, String preview, String detail, int id_cat, String picture, String cname,
			int status, int id_user, String username, int point, Timestamp creat_at) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.id_cat = id_cat;
		this.picture = picture;
		this.cname = cname;
		this.status = status;
		this.id_user = id_user;
		this.username = username;
		this.point = point;
		this.creat_at = creat_at;
	}
	
	
	public News(int id, String name, String preview, String detail, int id_cat, String picture, String cname,
			int status, int id_user, String username, int point, Timestamp die_at, Timestamp creat_at) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.id_cat = id_cat;
		this.picture = picture;
		this.cname = cname;
		this.status = status;
		this.id_user = id_user;
		this.username = username;
		this.point = point;
		this.die_at = die_at;
		this.creat_at = creat_at;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
