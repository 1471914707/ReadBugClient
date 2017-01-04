package com.readbug.bean;

public class Admins {
	public static final String TABLE_NAME="admins"; 
	Integer id;
	String account;
	String name;
	String password;
	int isactive;
	int type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
