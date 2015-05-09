package com.stardust.express.models;

public class User {
	protected long id;
	protected String username;
	protected String name;
	protected String status;
	protected String password;
	protected String email;
	protected long roleId;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
    
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
}
