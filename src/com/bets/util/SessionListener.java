package com.bets.util;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bets.pojo.SessionInfo;


@ManagedBean(name="sessionListener")
@ApplicationScoped
public class SessionListener  implements HttpSessionListener{
@ManagedProperty(value="#{sessionCount}")
	private SessionInfo  info;


@Override
public void sessionCreated(HttpSessionEvent event) {	
	
}

@Override
public void sessionDestroyed(HttpSessionEvent arg0) {
		
		info.setActive(info.getActive()-1);
	   System.out.println("destroy  active : "+info.getActive());
	
	
}

}
