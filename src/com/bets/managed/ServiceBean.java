package com.bets.managed;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

import com.bets.entity.admin.Admin;
import com.bets.entity.bayi.Bayi;
import com.bets.entity.uye.Uye;

@ManagedBean(name = "serviceBean")
@RequestScoped
public class ServiceBean {

	private EntityManager em = null;
	private EntityTransaction tx = null;
	private Bayi bayi = new Bayi();
	private Uye uye = new Uye();
	private Admin admin = new Admin();
	private String sifre;
	private String bayiKodu;

	public String emptyForm() {
		return "addmember";

	}

	@PostConstruct
	public void initialized() {
		System.out.println("servis bean olustu");
		Random r = new Random();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			temp.append((char) (r.nextInt(10) + 48));
		}
		setSifre(temp.toString());
		temp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			temp.append((char) (r.nextInt(10) + 48));
		}
		setBayiKodu(temp.toString());

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityTransaction getTx() {
		return tx;
	}

	public void setTx(EntityTransaction tx) {
		this.tx = tx;
	}

	public Bayi getBayi() {
		return bayi;
	}

	public void setBayi(Bayi bayi) {
		this.bayi = bayi;
	}

	public Uye getUye() {
		return uye;
	}

	public void setUye(Uye uye) {
		this.uye = uye;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getSifre() {
		return sifre;
	}

	public void setBayiKodu(String bayiKodu) {
		this.bayiKodu = bayiKodu;
	}

	public String getBayiKodu() {
		return bayiKodu;
	}

}
