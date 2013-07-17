package com.bets.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "credentialsBean")
@SessionScoped
public class Credentials {
private String username="bayi";
private String password;
private boolean isloggedin=false;
private String role;
private long actorId;
private String content="/content.xhtml";
private String deallerContent="/auth/dealleradd.xhtml";
private long tempId=-1;
private long temp_bayiId=-1;

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
public boolean isIsloggedin() {
	return isloggedin;
}
public void setIsloggedin(boolean isloggedin) {
	this.isloggedin = isloggedin;
}
public void setRole(String role) {
	this.role = role;
}
public String getRole() {
	return role;
}
public void setActorId(long actorId) {
	this.actorId = actorId;
}
public long getActorId() {
	return actorId;
}
public void setContent(String cont) {
	System.out.println("content in yeni degeri :" + cont);
	this.content = cont;
}
public String getContent() {

	System.out.println("content dönen değer :" + content);
	return content;
}
public void setTempId(long tempId) {
	this.tempId = tempId;
}
public long getTempId() {
	return tempId;
}

public void setTemp_bayiId(long temp_bayiId) {
	this.temp_bayiId = temp_bayiId;
}
public long getTemp_bayiId() {
	return temp_bayiId;
}
public String getDeallerContent() {
	return deallerContent;
}
public void setDeallerContent(String deallerContent) {
	this.deallerContent = deallerContent;
}


}
