package com.bets.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Bahis implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	@OneToOne(fetch = FetchType.LAZY)
	private Mac mac;
	private long macKodu;
	private float oran;
	@OneToOne
	private BahisTip tipi;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Kupon> kuponu;

	public Bahis() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMacKodu() {
		return macKodu;
	}

	public void setMacKodu(long macKodu) {
		this.macKodu = macKodu;
	}

	public float getOran() {
		return oran;
	}

	public void setOran(float oran) {
		this.oran = oran;
	}

	public BahisTip getTipi() {
		return tipi;
	}

	public void setTipi(BahisTip tipi) {
		this.tipi = tipi;
	}

	public List<Kupon> getKuponu() {
		return kuponu;
	}

	public void setKuponu(List<Kupon> kuponu) {
		this.kuponu = kuponu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
