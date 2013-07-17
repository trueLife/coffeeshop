package com.bets.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.commons.codec.digest.DigestUtils;
import com.bets.entity.Bayi;
import com.bets.managed.Credentials;
import com.bets.util.EntityUtil;

@ManagedBean
@RequestScoped
public class DeallerService {
	private Bayi bayi = new Bayi();
	// auth altında manage dealler sayfasında bayi düzenleme işlemleri için
	// kuanılıyor
	private Bayi bayi_temp = new Bayi();
	private String sifre;
	private String bayiKodu;
	private EntityManager em;
	private EntityTransaction tx;
	@ManagedProperty(value = "#{credentialsBean}")
	private Credentials credentials = null;
	private String sifreKontrol;
	private String eskiSifre;

	@PostConstruct
	public void initialized() {
		System.out.println("dellarservis bean olustu");
		Random r = new Random();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			temp.append((char) (r.nextInt(10) + 48));
		}
		setSifre(temp.toString());
		temp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			temp.append((char) (r.nextInt(10) + 48));
		}
		setBayiKodu(temp.toString());

	}

	public String changePrinter() {

		try {
			em = EntityUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Bayi temp = em.find(Bayi.class, credentials.getActorId());
			if (temp == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(
								"Bayi Bulunamadı, İşlem Gerçekleştirilemedi"));

			} else {
				temp.setYazici(bayi.getYazici());
				em.merge(temp);
				tx.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Yazıcınız : " + temp.getYazici()
										+ " 'lı olarak ayarlandı", null));
			}

		} catch (Exception e) {
			tx.rollback();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bir Hata Oluştu"));

		}
		return null;
	}

	public String changePassword() {
		try {
			em = EntityUtil.getEntityManager();
			tx = em.getTransaction();

			Bayi bayi = em.find(Bayi.class, credentials.getActorId());

			if (bayi == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Bayi Bulunamadı İşlem Gerçekleşrilemedi!",
								null));

			} else if (!encodeToMd5(getSifre()).equals(
					encodeToMd5(getSifreKontrol()))) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Şifreler Eşleşmiyor!", null));
				return null;

			}
			if (!bayi.getSifre().equals(encodeToMd5(getEskiSifre()))) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Eski Şifrenizi, Hatalı Girdiniz!", null));

			} else {
				tx.begin();
				bayi.setSifre(encodeToMd5(getSifre()));
				try {
					em.merge(bayi);
					tx.commit();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"İşlem Başarı ile Gerçekleştirildi", null));

				} catch (Exception ex) {
					tx.rollback();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"İşlem Gerçekleştirilemedi!", null));

				}

			}

		} catch (Exception ex2) {
			tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"bir hata oluştu!", null));

		}
		return null;
	}

	private String encodeToMd5(String pass) {
		return DigestUtils.md5Hex(pass);
	}

	public String persistDealler() {
		try {
			if (!checkUserName(bayi.getKodu())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Lütfen Başka Bayikodu giriniz!", null));
				return null;

			}
			em = EntityUtil.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			bayi.setSifre(encodeToMd5(sifre));
			EntityUtil.getEntityManager().persist(bayi);
			tx.commit();
			bayi = new Bayi();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Bayi başarı ile kaydedildi", null));

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Bayi eklenemedi", null));
		}

		return null;
	}

	public void deleteDealler() {

		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.find(Bayi.class, credentials.getTemp_bayiId()));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Silme İşlemi Başarı ile Gerçekleştirildi", null));
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"Silme İşlemi  Gerçekleştirilemedi", null));
		}

	}

	public List<Bayi> getDeallers() {
		List<Bayi> deallers = new ArrayList<Bayi>();
		em = EntityUtil.getEntityManager();
		try {
			deallers = em.createQuery("from  Bayi").getResultList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bayiler getirilemiyor.", null));
		}
		em.clear();
		return deallers;

	}

	public void updateDealler(){
		credentials.setTemp_bayiId(-1);
		credentials.setDeallerContent("/auth/dealleradd.xhtml");
		
		System.out.println("validation fail: "+FacesContext.getCurrentInstance().isValidationFailed());
		System.out.println("bayi id : " +credentials.getTemp_bayiId());
		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		try {
			tx.begin();
			Bayi temp = em.find(Bayi.class, credentials.getTemp_bayiId());
			System.out.println("bayi kodu :" + bayi_temp.getKodu());
			temp.setAciklama(bayi_temp.getAciklama());
			temp.setAdres(bayi_temp.getAdres());
			temp.setAdSoyad(bayi_temp.getAdSoyad());
			temp.setBakiye(bayi_temp.getBakiye());
			temp.setBanka(bayi_temp.getBanka());
			temp.setBayiAktif(bayi_temp.isBayiAktif());
			temp.setBorc(bayi_temp.getBorc());
			temp.setCepTel1(bayi_temp.getCepTel1());
			temp.setCepTel2(bayi_temp.getCepTel2());
			temp.setHesapNo(bayi_temp.getHesapNo());
			temp.setIlce(bayi_temp.getIlce());
			temp.setIsTel(bayi_temp.getIsTel());
			temp.setKodu(bayi_temp.getKodu());
			temp.setKuponKarPayi(bayi_temp.getKuponKarPayi());
			temp.setKuponYapma(bayi_temp.isKuponYapma());
			temp.setOlusturulmaTarihi(bayi_temp.getOlusturulmaTarihi());
			temp.setReferans(bayi_temp.getReferans());
			temp.setSehir(bayi_temp.getSehir());
			temp.setSifre(encodeToMd5(getSifre()));
			temp.setTanim(bayi_temp.getTanim());
			temp.setTcNo(bayi_temp.getTcNo());
			temp.setToplamKarPayi(bayi_temp.getToplamKarPayi());
			temp.setUlke(bayi_temp.getUlke());
			temp.setUyeler(bayi_temp.getUyeler());
			temp.setYazici(bayi_temp.getYazici());
			tx.commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bayi Başarıyla Güncellendi"));
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bayi Güncelleme İşlemi Başarısız oldu!"));

		}
		em.clear();
	}

	public void removeDealler() {

		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.find(Bayi.class, credentials.getTemp_bayiId()));
			credentials.setTemp_bayiId(-1);
			credentials.setDeallerContent("/auth/memberadd.xhtml");

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bayi  Başarıyla Silindi!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Bayi Silme İşlemi Başarısız oldu!"));
			if (tx.isActive())
				tx.rollback();

		}
	}

	public boolean checkUserName(String username) {
		em = EntityUtil.getEntityManager();
		try {
			List result = em.createQuery("from Bayi  where kodu =:arg0")
					.setParameter("arg0", username).getResultList();
			if (result == null || result.isEmpty())
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public void setBayi(Bayi bayi) {
		this.bayi = bayi;
	}

	public Bayi getBayi() {
		return bayi;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getSifre() {
		return sifre;
	}

	public void setBayiKodu(String bayiKodu) {
		this.bayiKodu = bayiKodu;
	}

	public String getBayiKodu() {
		return bayiKodu;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityTransaction getTx() {
		return tx;
	}

	public void setTx(EntityTransaction tx) {
		this.tx = tx;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void setSifreKontrol(String sifreKontrol) {
		this.sifreKontrol = sifreKontrol;
	}

	public String getSifreKontrol() {
		return sifreKontrol;
	}

	public void setEskiSifre(String eskiSifre) {
		this.eskiSifre = eskiSifre;
	}

	public String getEskiSifre() {
		return eskiSifre;
	}

	public void setBayi_temp(Bayi bayi_temp) {
		this.bayi_temp = bayi_temp;
	}

	public Bayi getBayi_temp() {
		if (credentials.getTemp_bayiId() == -1) {
			return bayi_temp;
		} else {
			em = EntityUtil.getEntityManager();
			return em.find(Bayi.class, credentials.getTemp_bayiId());
		}
	}
}
