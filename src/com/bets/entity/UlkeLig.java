package com.bets.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class UlkeLig implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String ulke;
	private String lig;
	public UlkeLig() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUlke() {
		return ulke;
	}
	public void setUlke(String ulke) {
		this.ulke = ulke;
	}
	public String getLig() {
		return lig;
	}
	public void setLig(String lig) {
		this.lig = lig;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
