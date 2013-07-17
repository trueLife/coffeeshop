package com.bets.managed;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.bets.entity.Bahis;

@ManagedBean
@SessionScoped
public class Coupon {
	
	private int macSayisi;
	private float toplamOran;
	private float toplamKazanc;
	private float yatirilanPara=1;
	private List<Bahis> bahisler;
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
	public List<Bahis> getBahisler() {
		return bahisler;
	}
	public void setBahisler(List<Bahis> bahisler) {
		this.bahisler = bahisler;
	}

}
