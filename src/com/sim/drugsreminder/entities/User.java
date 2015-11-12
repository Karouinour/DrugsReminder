package com.sim.drugsreminder.entities;

public class User {
	
	private int id;
	private String name;
	private String icon;
	private String phone;
	private String email;
	
	
	
	
	public User(){
		
	}
	
	public User(int id, String name, String email, String tel, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = tel;
		this.icon = icon;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String tel) {
		this.phone = tel;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	


}
