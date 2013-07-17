package com.bets.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Ayarlar implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String tip;
	private String deger;

	public Ayarlar() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getDeger() {
		return deger;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
