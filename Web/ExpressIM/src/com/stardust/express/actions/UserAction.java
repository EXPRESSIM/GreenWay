package com.stardust.express.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.stardust.express.dao.implementations.UserGate;
import com.stardust.express.models.User;

public class UserAction extends  ActionSupport{
	private static final long serialVersionUID = 1359090410097337654L;
	private List<User> users = new ArrayList<User>();
	
	private long userid = 0;
	private String datasource = "";
	
	public void setDatasource(String source) {
		datasource = source;
	}
	
	@JSON(serialize=false)
	public String getDatasource(){
		return datasource;
	}
	
	public void setUserId(long id) {
		userid = id;
	}
	
	@JSON(serialize=false)
	public long getUserId(){
		return userid;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public String loadUser() {
        User user = new User();
        user.setUsername("rivneg");
        user.setPassword("8forxiao");
        //user.setId(getUserId());
        user.setRoleId(1);
        user.setName("Alex Li");
        users.add(user);
        
        UserGate gate = new UserGate(getDatasource());
        gate.add(user);
        User u = (User)gate.find(4);
        users.add(u);
        return SUCCESS;
    }
}
