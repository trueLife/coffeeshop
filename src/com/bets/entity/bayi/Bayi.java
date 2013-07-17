package com.bets.entity.bayi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import com.bets.entity.uye.Uye;

@Entity
public class Bayi implements Serializable {
 
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String tanim;
	private String kodu;
	private String sifre;
	@Column(nullable=true)
	private float bakiye;
	private float borc;
	private float kuponKarPayi;
	private float toplamKarPayi;
	//extra information
	private String adSoyad;
	private String tcNo;
	private String cepTel1;
	private String cepTel2;
	private String isTel;
	private String sehir;
	private String adres;
	private String yazici;
	private String ilce;
	private String ulke;
	private String hesapNo;
	private String referans;
	private String aciklama;
	private String banka;
	private Date olusturulmaTarihi;
	private boolean kuponYapma=true;
	private boolean bayiAktif=true;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="bayisi")
	private List<Uye> uyeler = new ArrayList<Uye>();
	
	 @PostConstruct
	 public void generateRandomPass(){
		 System.out.println("bayi nesnesi olustu random şifre gönderildi");
		 
	 }
	
	public long getId() {
		return id;
	}
	
	

	public float getBakiye() {
		return bakiye;
	}


	public void setBakiye(float bakiye) {
		this.bakiye = bakiye;
	}


	public float getBorc() {
		return borc;
	}


	public void setBorc(float borc) {
		this.borc = borc;
	}
	
	public String getAdSoyad() {
		return adSoyad;
	}


	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}


	public String getTcNo() {
		return tcNo;
	}


	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}


	public String getCepTel1() {
		return cepTel1;
	}


	public void setCepTel1(String cepTel1) {
		this.cepTel1 = cepTel1;
	}


	public String getCepTel2() {
		return cepTel2;
	}


	public void setCepTel2(String cepTel2) {
		this.cepTel2 = cepTel2;
	}


	public String getIsTel() {
		return isTel;
	}


	public void setIsTel(String isTel) {
		this.isTel = isTel;
	}


	public String getSehir() {
		return sehir;
	}


	public void setSehir(String sehir) {
		this.sehir = sehir;
	}


	public String getAdres() {
		return adres;
	}


	public void setAdres(String adres) {
		this.adres = adres;
	}


	public String getYazici() {
		return yazici;
	}


	public void setYazici(String yazici) {
		this.yazici = yazici;
	}


	public String getIlce() {
		return ilce;
	}


	public void setIlce(String ilce) {
		this.ilce = ilce;
	}


	public String getUlke() {
		return ulke;
	}


	public void setUlke(String ulke) {
		this.ulke = ulke;
	}


	public String getHesapNo() {
		return hesapNo;
	}


	public void setHesapNo(String hesapNo) {
		this.hesapNo = hesapNo;
	}


	public String getReferans() {
		return referans;
	}


	public void setReferans(String referans) {
		this.referans = referans;
	}


	public String getAciklama() {
		return aciklama;
	}


	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}


	public Date getOlusturulmaTarihi() {
		return olusturulmaTarihi;
	}


	public void setOlusturulmaTarihi(Date olusturulmaTarihi) {
		this.olusturulmaTarihi = olusturulmaTarihi;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}
	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}



	public void setBorc(int borc) {
		this.borc = borc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Bayi() {
		super();
	}


	public void setBayiAktif(boolean bayiAktif) {
		this.bayiAktif = bayiAktif;
	}

	public boolean isBayiAktif() {
		return bayiAktif;
	}

	public void setKuponYapma(boolean kuponYapma) {
		this.kuponYapma = kuponYapma;
	}

	public boolean isKuponYapma() {
		return kuponYapma;
	}

	public void setUyeler(List<Uye> uyeler) {
		this.uyeler = uyeler;
	}

	public List<Uye> getUyeler() {
		return uyeler;
	}

	public float getKuponKarPayi() {
		return kuponKarPayi;
	}

	public void setKuponKarPayi(float kuponKarPayi) {
		this.kuponKarPayi = kuponKarPayi;
	}
	

	public void setKodu(String kodu) {
		this.kodu = kodu;
	}

	public String getKodu() {
		return kodu;
	}

	public void setToplamKarPayi(float toplamKarPayi) {
		this.toplamKarPayi = toplamKarPayi;
	}

	public float getToplamKarPayi() {
		return toplamKarPayi;
	}

	public void setBanka(String banka) {
		this.banka = banka;
	}

	public String getBanka() {
		return banka;
	}



}
