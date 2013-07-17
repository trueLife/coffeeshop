package com.bets.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Admin implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String kullaniciAdi;
	private String sifre;
	private String rol;
	
	
	public Admin() {
		
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getKullaniciAdi() {
		return kullaniciAdi;
	}


	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}


	public String getSifre() {
		return sifre;
	}


	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getRol() {
		return rol;
	}
   
}
