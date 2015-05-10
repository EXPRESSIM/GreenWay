package com.stardust.express.models;

public class DataModel {
	
	final public static String STATUS_ACTIVE = "A";
	final public static String STATUS_WITHDRAW = "W";
	
	protected long id = -1;
	protected String status = DataModel.STATUS_ACTIVE;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
