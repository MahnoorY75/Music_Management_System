package application;

import javafx.scene.image.Image;


public class User {
	
	private String id;
	private String full_name;
	private String email;
	private String username;
	private String password;
	private String usertype;
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	
	
	public User(String id, String full_name, String email, String username, String password, String usertype) {
		
		this.id= id;
		this.full_name = full_name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

}
