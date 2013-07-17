package com.bets.crud;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.apache.commons.codec.digest.DigestUtils;
import com.bets.entity.Bayi;
import com.bets.entity.Uye;
import com.bets.managed.Credentials;
import com.bets.util.EntityUtil;

@ManagedBean
@RequestScoped
public class MemberService {
	private EntityManager em;
	private EntityTransaction tx;
	private String sifreKontrol;
	private String eskiSifre;
	private String sifre;
	private Uye uye = new Uye();
	private List<Uye> uyeler;
	@ManagedProperty(value = "#{credentialsBean}")
	private Credentials credentials = null;

	private String encodeToMd5(String pass) {
		return DigestUtils.md5Hex(pass);

	}

	public String getSifreKontrol() {
		return sifreKontrol;
	}

	public void setSifreKontrol(String sifreKontrol) {
		this.sifreKontrol = sifreKontrol;
	}

	public String getEskiSifre() {
		return eskiSifre;
	}

	public void setEskiSifre(String eskiSifre) {
		this.eskiSifre = eskiSifre;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String persistMember() {
		try {
			System.out.println("uye kulad : " + getUye().getKullaniciAd());
			if (!checkUserName(getUye().getKullaniciAd())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Lütfen Başka KullanıcıAdı Giriniz!", null));
				return null;
			}
			em = EntityUtil.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			getUye().setBayisi(em.find(Bayi.class, credentials.getActorId()));
			getUye().setSifre(encodeToMd5(getUye().getSifre()));
			EntityUtil.getEntityManager().persist(getUye());
			tx.commit();
			setUye(new Uye());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Üye başarı ile kaydedildi", null));

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Üye eklenemedi", null));
		}

		return null;
	}

	public boolean checkUserName(String username) {
		if (username == null || username.length() < 0)
			return false;
		em = EntityUtil.getEntityManager();
		try {
			List result = em.createQuery("from Uye where kullaniciAd :arg0")
					.setParameter("arg0", username).getResultList();
			if (result == null || result.isEmpty())
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public String changePassword() {
		try {
			em = EntityUtil.getEntityManager();
			tx = em.getTransaction();

			Uye uye = em.find(Uye.class, credentials.getActorId());

			if (uye == null) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Üye Bulunamadı İşlem Gerçekleşrilemedi!",
										null));

			} else if (!encodeToMd5(getSifre()).equals(
					encodeToMd5(getSifreKontrol()))) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Şifreler Eşleşmiyor!", null));
				return null;

			}
			if (!uye.getSifre().equals(encodeToMd5(getEskiSifre()))) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Eski Şifrenizi, Hatalı Girdiniz!", null));

			} else {
				uye.setSifre(encodeToMd5(getSifre()));
				try {
					tx.begin();
					em.merge(uye);
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
			ex2.printStackTrace();
			tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"bir hata oluştu!", null));

		}
		return null;
	}

	public void deleteSelectedMember(long id) {
		System.out.println("silinen:" + id);
		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		tx.begin();
		try {
			em.remove(em.find(Uye.class, id));
			tx.commit();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("İşlem Başarıyla Gerçekleşti."));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Üye Silme İşlemi Başarısız!"));
			if (tx.isActive())
				tx.rollback();

		}
	}

	public void findMemberForUpdate(long id) {

		System.out.println(id + " li eleman gümcelenecek");
		em = EntityUtil.getEntityManager();
		try {
			setUye(em.find(Uye.class, id));
			em.clear();
			credentials.setContent("/editmember.xhtml");

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Seçilen Uye  bulunamadı!", null));
			credentials.setContent("/members.xhtml");
		}
	}

	public void updateMember() {
		long temp_id = credentials.getTempId();
		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		tx.begin();
		try {
			Uye temp = em.find(Uye.class, temp_id);

			if (!(getUye().getSifre() == null || getUye().getSifre().length() == 0)) {
				System.out.println("sifre " + getUye().getSifre());
				System.out.println("sifre enc "
						+ encodeToMd5(getUye().getSifre()));
				temp.setSifre(encodeToMd5(getUye().getSifre()));
			}
			temp.setAktif(getUye().isAktif());
			temp.setBakiye(getUye().getBakiye());
			temp.setKullaniciAd(getUye().getKullaniciAd());
			temp.setSehir(getUye().getSehir());
			temp.setTel(getUye().getTel());
			tx.commit();
			em.clear();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Kayıt Başarı ile Güncellendi!", null));
			credentials.setTempId(-1);
			
		} catch (Exception rc) {
			if (tx.isActive())
				tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"İşlem Başarısız!", null));

		}
	}

	public void updateMember2() {
		Query query;
		em = EntityUtil.getEntityManager();
		tx = em.getTransaction();
		tx.begin();
		try {
			if (!(getUye().getSifre() == null || getUye().getSifre().length() == 0)) {
				query = em
						.createQuery("update Uye  set  adSoyad =:adSoyad , aktif =:aktif , bakiye =:bakiye , kullaniciAd=:kulad   , sehir =:sehir ,sifre =:sifre ,tel=:tel where  id=:id ");
				query.setParameter("adSoyad", getUye().getAdSoyad());
				query.setParameter("aktif", getUye().isAktif());
				query.setParameter("bakiye", getUye().getBakiye());
				query.setParameter("kulad", getUye().getKullaniciAd());
				query.setParameter("sehir", getUye().getSehir());
				query.setParameter("sifre", getUye().getSifre());
				query.setParameter("tel", getUye().getTel());
				query.setParameter("id", credentials.getTempId());

			} else {
				query = em
						.createQuery("update Uye  set  adSoyad =:adSoyad ,aktif =:aktif , bakiye =:bakiye , kullaniciad=:kulad   , sehir =:sehir  ,tel=:tel where  id=:id");
				query.setParameter("adSoyad", getUye().getAdSoyad());
				query.setParameter("aktif", getUye().isAktif());
				query.setParameter("bakiye", getUye().getBakiye());
				query.setParameter("kulad", getUye().getKullaniciAd());
				query.setParameter("sehir", getUye().getSehir());
				query.setParameter("tel", getUye().getTel());
				query.setParameter("id", credentials.getTempId());
			}

			int effected = query.executeUpdate();

			tx.commit();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, effected
							+ "Kayıt Güncellendi!", null));

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx.isActive())
				tx.rollback();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"İşlem Başarısız!", null));
		} finally {
			credentials.setTempId(-1);
		}
	}

	public void setUyeler(List<Uye> uyeler) {
		this.uyeler = uyeler;
	}

	public List<Uye> getUyeler() {
		System.out.println("üyeler getiriliyor");
		em = EntityUtil.getEntityManager();
		Query q = em.createQuery("from  Uye where bayisi_id=:bayi");
		q.setParameter("bayi", credentials.getActorId());
		uyeler = (List<Uye>) q.getResultList();
		em.clear();
		return uyeler;
	}

	public void setUye(Uye uye) {
		this.uye = uye;
	}

	public Uye getUye() {
		return uye;
	}


}
