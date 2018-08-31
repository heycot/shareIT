package model.bean;

import java.sql.Timestamp;

public class Users {
	private int id;
	private String username;
	private String password;
	private String fullname;
	private int id_roles;
	private String nameroles;
	private String email;
	private Timestamp create_at;
	
	
	public Timestamp getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}
	public String getNameroles() {
		return nameroles;
	}
	public void setNameroles(String nameroles) {
		this.nameroles = nameroles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_roles() {
		return id_roles;
	}
	public void setId_roles(int id_roles) {
		this.id_roles = id_roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public Users(int id, String username, String password, String fullname, int id_roles, String nameroles,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.id_roles = id_roles;
		this.nameroles = nameroles;
		this.email = email;
	}
	
	public Users(int id, String username, String password, String fullname, int id_roles, String nameroles,
			String email, Timestamp create_at) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.id_roles = id_roles;
		this.nameroles = nameroles;
		this.email = email;
		this.create_at = create_at;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
