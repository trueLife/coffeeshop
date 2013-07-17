package com.bets.util;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//bunun managed bean olması sacma cünkü zaten engine olu applicaiton boyunca hizmet sınıfı olarak kullanıyor
//@ManagedBean(name="sessionListener")
//@ApplicationScoped
//buna  onyüzden  applicationScobe.sessionListener.acitive diye erişme denenecek
public class SessionListener  implements HttpSessionListener{
	@PostConstruct
		public void init(){
		
		//session  info init edilmeli
	}
	//listenerlarda injecxtion calısmaz
//@ManagedProperty(value="#{sessionCount}")
//	private SessionInfo  info;
private long active=0;

@Override
public void sessionCreated(HttpSessionEvent event) {	
	
}

@Override
public void sessionDestroyed(HttpSessionEvent arg0) {
		
		
	  
	
	
}

public void setActive(long active) {
	this.active = active;
}

public long getActive() {
	return active;
}

}
