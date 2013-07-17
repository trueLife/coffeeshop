package com.bets.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Duyuru implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String content;
	private Date baslangisTarihi;
	private Date bitisTarihi;
	private boolean aktif;
	private short derece;
	
	public Duyuru() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBaslangisTarihi() {
		return baslangisTarihi;
	}

	public void setBaslangisTarihi(Date baslangisTarihi) {
		this.baslangisTarihi = baslangisTarihi;
	}

	public Date getBitisTarihi() {
		return bitisTarihi;
	}

	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public short getDerece() {
		return derece;
	}

	public void setDerece(short derece) {
		this.derece = derece;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
