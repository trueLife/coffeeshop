package com.bets.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class BahisTip implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private int tipNo;
	private String aciklama;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BahisTip() {
		super();
	}


	public int getTipNo() {
		return tipNo;
	}

	public void setTipNo(int tipNo) {
		this.tipNo = tipNo;
	}

}
