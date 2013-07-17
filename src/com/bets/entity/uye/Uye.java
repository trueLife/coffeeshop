package com.bets.entity.uye;

import java.io.Serializable;
import javax.persistence.*;

import com.bets.entity.bayi.Bayi;


@Entity
public class Uye implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String kullaniciAd;
	private String sifre;
	private String adSoyad;
	private String tel;
	private String sehir;
	private float bakiye=0;
	private boolean aktif=true;
	@ManyToOne(fetch = FetchType.EAGER)
	private Bayi bayisi;

	public Uye() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKullaniciAdi() {
		return kullaniciAd;
	}

	public void setKullaniciAdi(String kullaniciAd) {
		this.kullaniciAd = kullaniciAd;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSehir() {
		return sehir;
	}

	public void setSehir(String sehir) {
		this.sehir = sehir;
	}

	public float getBakiye() {
		return bakiye;
	}

	public void setBakiye(float bakiye) {
		this.bakiye = bakiye;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getSifre() {
		return sifre;
	}

	public void setBayisi(Bayi bayisi) {
		this.bayisi = bayisi;
	}

	public Bayi getBayisi() {
		return bayisi;
	}

	public String getKullaniciAd() {
		return kullaniciAd;
	}

	public void setKullaniciAd(String kullaniciAd) {
		this.kullaniciAd = kullaniciAd;
	}

}
