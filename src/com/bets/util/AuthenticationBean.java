package com.bets.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import com.bets.entity.bayi.Bayi;
import com.bets.entity.uye.Uye;
import com.bets.managed.Credentials;
import com.bets.pojo.SessionInfo;

@ManagedBean(name = "authenticationBean")
@RequestScoped
public class AuthenticationBean {
	// javax.faces.application.ViewExpiredException hatası handle edilecek
	// javax.el.PropertyNotFoundException
	@ManagedProperty(value = "#{credentialsBean}")
	private Credentials credentials = null;
	@ManagedProperty(value = "#{sessionCount}")
	private SessionInfo sessionCount;

	// public String login2() {
	// if (credentials.getUsername() != null
	// && credentials.getUsername().equals("admin")
	// && credentials.getPassword() != null
	// && credentials.getPassword().equals("admin")) {
	// credentials.setIsloggedin(true);
	// FacesContext.getCurrentInstance().getExternalContext()
	// .getSessionMap().put("credentials", credentials);
	// System.out.println("index e gonderiliyor");
	// sessionCount.setActive(sessionCount.getActive()+1);
	// System.out.println("login  active : "+sessionCount.getActive());
	//
	// return "index?faces-redirect=true";
	//
	// } else {
	// credentials.setIsloggedin(false);
	// FacesContext.getCurrentInstance().addMessage(
	// null,
	// new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// "Giriş  Başarısız", null));
	// return null;
	//
	// }
	// }
	private String encodeToMd5(String pass) {
		return DigestUtils.md5Hex(pass);

	}

	private Object uyeyiGetir(String userName, String password) {
		Uye uye = null;
		Bayi bayi = null;
		Query query = EntityUtil
				.getEntityManager()
				.createQuery(
						"Select b from Bayi b  where  b.kodu = :arg0 and b.sifre = :arg1");
		query.setParameter("arg0", userName);
		query.setParameter("arg1", encodeToMd5(password));
		try {
			bayi = (Bayi) query.getSingleResult();

		} catch (Exception e) {

			query = EntityUtil
					.getEntityManager()
					.createQuery(
							"select u from  Uye  u WHERE  u.kullaniciAd = :arg0 and u.sifre = :arg1");
			query.setParameter("arg0", userName);
			query.setParameter("arg1", encodeToMd5(password));
			try {
				uye = (Uye) query.getSingleResult();
			} catch (Exception ex) {
				return null;
			}
			return uye;

		}

		return bayi;

		// System.out.println("bayi bilgisi : " + bayi.getAciklama());
		// System.out.println("bayi bilgisi : " + bayi.getAdres());
		// System.out.println("bayi bilgisi : " + bayi.getAdSoyad());
		// System.out.println("bayi bilgisi : " + bayi.getBorc());
		// System.out.println("bayi bilgisi : " + bayi.getCepTel1());
		// System.out.println("bayi bilgisi : " + bayi.getIlce());

	}

	public String login() {
		Object actor;
		if (credentials.getUsername() == null)
			return "login?faces-redirect=true";
		if (credentials.getPassword() == null)
			return "login?faces-redirect=true";

		actor = uyeyiGetir(credentials.getUsername(), credentials.getPassword());
		credentials.setPassword(encodeToMd5(credentials.getPassword()));

		if (actor == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"kullanız adı yada şifre   hatalı", null));
			credentials.setIsloggedin(false);
			return null;

		}

		else if (actor instanceof Uye) {
			// actor is an member
 
			credentials.setIsloggedin(true);
			credentials.setRole("uye");
			credentials.setPassword(null);
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("credentials", credentials);
			return "index?faces-redirect=true";
		} else if (actor instanceof Bayi) {
			// actor is an dealer
			// member is successfully logged in
			credentials.setIsloggedin(true);
			credentials.setRole("bayi");
			credentials.setPassword(null);
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("credentials", credentials);
			return "index?faces-redirect=true";
		} else {
			credentials.setIsloggedin(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"tanınmayan giriş denemesi ", null));

			return null;
		}
	}

	public String logout() {
		credentials.setIsloggedin(false);
		FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap().remove("credentials");
		HttpServletRequest req = ((HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest());
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
			sessionCount.setActive(sessionCount.getActive() - 1);
		}
		System.out
				.println("logout yapti active :  " + sessionCount.getActive());
		return "login?faces-redirect=true";
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setSessionCount(SessionInfo sessionCount) {
		this.sessionCount = sessionCount;
	}

	public SessionInfo getSessionCount() {
		return sessionCount;
	}

}
