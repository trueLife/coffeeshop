package com.bets.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity

public class Mac implements Serializable {

	
	private static final long serialVersionUID = 1L;
@Id
private  long id;
private String macKodu;
private String evSahibiTakım;
private String konukTakım;
private Date Tarih;
private boolean canlimi;
@ManyToOne(fetch=FetchType.LAZY)
private UlkeLig ulkeLig;


	public Mac() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMacKodu() {
		return macKodu;
	}


	public void setMacKodu(String macKodu) {
		this.macKodu = macKodu;
	}


	public String getEvSahibiTakım() {
		return evSahibiTakım;
	}


	public void setEvSahibiTakım(String evSahibiTakım) {
		this.evSahibiTakım = evSahibiTakım;
	}


	public String getKonukTakım() {
		return konukTakım;
	}


	public void setKonukTakım(String konukTakım) {
		this.konukTakım = konukTakım;
	}


	public Date getTarih() {
		return Tarih;
	}


	public void setTarih(Date tarih) {
		Tarih = tarih;
	}


	public boolean isCanlimi() {
		return canlimi;
	}


	public void setCanlimi(boolean canlimi) {
		this.canlimi = canlimi;
	}


	public UlkeLig getUlkeLig() {
		return ulkeLig;
	}


	public void setUlkeLig(UlkeLig ulkeLig) {
		this.ulkeLig = ulkeLig;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
