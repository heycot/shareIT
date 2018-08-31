package model.bean;

import java.sql.Timestamp;

public class Adver {
	private int id;
	private String name;
	private String picture;
	private String link;
	private Timestamp creat_at;
	private int status;
	private int money;
	private String preview;
	private Timestamp die_at;
	
	
	public Timestamp getDie_at() {
		return die_at;
	}
	public void setDie_at(Timestamp die_at) {
		this.die_at = die_at;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public Adver(int id, String name, String picture, String link, Timestamp creat_at, int status, int money,
			String preview) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.link = link;
		this.creat_at = creat_at;
		this.status = status;
		this.money = money;
		this.preview = preview;
	}
	
	
	public Adver(int id, String name, String picture, String link, Timestamp creat_at, int status, int money,
			String preview, Timestamp die_at) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.link = link;
		this.creat_at = creat_at;
		this.status = status;
		this.money = money;
		this.preview = preview;
		this.die_at = die_at;
	}
	public Adver() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
