package com.bets.entity.admin;

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
	private short rol;
	
	
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


	public short getRol() {
		return rol;
	}


	public void setRol(short rol) {
		this.rol = rol;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
