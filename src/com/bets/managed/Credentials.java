package com.bets.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "credentialsBean")
@SessionScoped
public class Credentials {
private String username="bayi";
private String password="123123";
private boolean isloggedin=false;
private String role;

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


}
