package com.bets.pojo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name="sessionInfo",eager=true)
public class SessionInfo {
	private int active =0;
public void Init(){
	System.out.println("Session ingo initialized");
	
}
	@PostConstruct
	public void init(){
		System.out.println("sessionCount object created");
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return active;
	}
}
