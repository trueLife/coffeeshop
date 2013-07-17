package com.bets.managed;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.model.SelectableDataModel;

import com.bets.entity.Uye;
import com.bets.util.EntityUtil;

@ManagedBean
@RequestScoped
public class ServiceBean {

	@ManagedProperty(value = "#{credentialsBean}")
	private Credentials credentials;
	
	private EntityManager em;
	private Uye selectedMember = new Uye();
	private EntityTransaction tx;

	public String emptyForm() {
		return "addmember";

	}

	public String navigateHome() {
		System.out.println("method calisti");
		HttpServletResponse res = (HttpServletResponse) (FacesContext
				.getCurrentInstance().getExternalContext().getResponse());
		HttpServletRequest req = (HttpServletRequest) (FacesContext
				.getCurrentInstance().getExternalContext().getRequest());
		if (credentials.getRole().equals("admin")
				|| getCredentials().getRole().equals("superadmin")
				|| getCredentials().getRole().equals("uye")
				|| getCredentials().getRole().equals("bayi")) {
			System.out.println("if calisti");
			try {
				res.sendRedirect(req.getContextPath() + "/ausf/dfsshome.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				res.sendRedirect(req.getContextPath() + "/home.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("else  calisti");
		}
		return null;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Credentials getCredentials() {
		return credentials;
	}




	public void setSelectedMember(Uye selectedMember) {
		this.selectedMember = selectedMember;
	}

	public Uye getSelectedMember() {
		return selectedMember;
	}

	

}
