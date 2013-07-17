package com.bets.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Kupon implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private long oynayanId;
	private String oynayan;
	private Date tarih;
	private int macSayisi;
	private float toplamOran;
	private float toplamKazanc;
	private float yatirilanPara;
	private String aciklama;
	private String ip;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="kuponu")
	private List<Bahis> bahisler;
	private short durum;
	
	
	
	public Kupon() {
		super();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getOynayanId() {
		return oynayanId;
	}



	public void setOynayanId(long oynayanId) {
		this.oynayanId = oynayanId;
	}



	public String getOynayan() {
		return oynayan;
	}



	public void setOynayan(String oynayan) {
		this.oynayan = oynayan;
	}



	public Date getTarih() {
		return tarih;
	}



	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}



	public int getMacSayisi() {
		return macSayisi;
	}



	public void setMacSayisi(int macSayisi) {
		this.macSayisi = macSayisi;
	}



	public float getToplamOran() {
		return toplamOran;
	}



	public void setToplamOran(float toplamOran) {
		this.toplamOran = toplamOran;
	}



	public float getToplamKazanc() {
		return toplamKazanc;
	}



	public void setToplamKazanc(float toplamKazanc) {
		this.toplamKazanc = toplamKazanc;
	}



	public float getYatirilanPara() {
		return yatirilanPara;
	}



	public void setYatirilanPara(float yatirilanPara) {
		this.yatirilanPara = yatirilanPara;
	}



	public String getAciklama() {
		return aciklama;
	}



	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public List<Bahis> getBahisler() {
		return bahisler;
	}



	public void setBahisler(List<Bahis> bahisler) {
		this.bahisler = bahisler;
	}



	public short getDurum() {
		return durum;
	}



	public void setDurum(short durum) {
		this.durum = durum;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
