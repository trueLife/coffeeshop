package com.bets.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import com.bets.entity.Admin;
import com.bets.entity.Bayi;
import com.bets.entity.Uye;
import com.bets.managed.Credentials;

@ManagedBean(name = "authenticationBean")
@RequestScoped
public class AuthenticationBean {
	// javax.faces.application.ViewExpiredException hatası handle edilecek
	// javax.el.PropertyNotFoundException
	@ManagedProperty(value = "#{credentialsBean}")
	private Credentials credentials = null;

	private String encodeToMd5(String pass) {
		return DigestUtils.md5Hex(pass);

	}

	private Object obtaionActor(String userName, String password) {
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
			credentials.setActorId(bayi.getId());
		} catch (Exception e) {

			query = EntityUtil
					.getEntityManager()
					.createQuery(
							"select u from  Uye  u WHERE  u.kullaniciAd = :arg0 and u.sifre = :arg1");
			query.setParameter("arg0", userName);
			query.setParameter("arg1", encodeToMd5(password));
			try {
				uye = (Uye) query.getSingleResult();
				credentials.setActorId(uye.getId());
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

		actor = obtaionActor(credentials.getUsername(),
				credentials.getPassword());

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
			// FacesContext.getCurrentInstance().getExternalContext()
			// .getSessionMap().put("credentials", credentials);
			return "home?faces-redirect=true";
		} else if (actor instanceof Bayi) {
			// actor is an dealer
			// member is successfully logged in
			credentials.setIsloggedin(true);
			credentials.setRole("bayi");
			credentials.setPassword(null);
			// FacesContext.getCurrentInstance().getExternalContext()
			// .getSessionMap().put("credentials", credentials);
			return "home?faces-redirect=true";
		} else {
			credentials.setIsloggedin(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"tanınmayan giriş denemesi ", null));

			return null;
		}
	}

	private Admin obtaionAuth(String userName, String password) {
		Admin auth = null;
		Query query = EntityUtil
				.getEntityManager()
				.createQuery(
						"Select ad from Admin  ad  where  ad.kullaniciAdi = :arg0 and ad.sifre = :arg1");
		query.setParameter("arg0", userName);
		query.setParameter("arg1", encodeToMd5(password));
		try {
			auth = (Admin) query.getSingleResult();
			credentials.setActorId(auth.getId());

		} catch (Exception e) {
			return null;

		}

		return auth;

	}

	public String authLogin() {
		Object actor = null;
		if (credentials.getUsername() == null)
			return "auth/login?faces-redirect=true";
		if (credentials.getPassword() == null)
			return "auth/login?faces-redirect=true";

		Admin auth = obtaionAuth(credentials.getUsername(),
				credentials.getPassword());

		if (auth == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"kullanız adı yada şifre hatalı", null));
			credentials.setIsloggedin(false);
			return null;
		} else {
			credentials.setIsloggedin(true);
			credentials.setRole(auth.getRol());
			credentials.setPassword(null);
			// FacesContext.getCurrentInstance().getExternalContext()
			// .getSessionMap().put("credentials", credentials);
			return "/auth/index?faces-redirect=true";
		}
	}

	public String logout() {

		credentials.setIsloggedin(false);
		credentials.setUsername(null);
		credentials.setRole(null);
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		// getSession(false);

		// if (session != null) {
		// session.invalidate();
		// sessionCount.setActive(sessionCount.getActive() - 1);
		// }
		// System.out
		// .println("logout yapti active :  " + sessionCount.getActive());
		System.out.println("normal kullanıcı çıkış yaptı");
		return "/login?faces-redirect=true";
	}

	public String authLogout() {
		credentials.setIsloggedin(false);
		credentials.setUsername(null);
		credentials.setRole(null);
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		// getSession(false);

		// if (session != null) {
		// session.invalidate();
		// sessionCount.setActive(sessionCount.getActive() - 1);
		// }
		// System.out
		// .println("logout yapti active :  " + sessionCount.getActive());
		return "/auth/login?faces-redirect=true";
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}

}
