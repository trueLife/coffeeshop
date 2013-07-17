package com.bets.filtre;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bets.managed.Credentials;

@WebFilter(servletNames = { "Faces Servlet" }, dispatcherTypes = {
		DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.REQUEST,
		DispatcherType.ERROR, DispatcherType.INCLUDE })
// *.xhtml dizin ile ilgili problemleri giderebilir
public class LoginFilter implements Filter {
	private Credentials credentials;
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		credentials = (Credentials) req.getSession(true).getAttribute(
				"credentialsBean");
		HttpServletResponse res = (HttpServletResponse) arg1;
		System.out.println("normal requested uri : " + req.getRequestURI());
		System.out.println("normal requested url : " + req.getRequestURL());
		String loginURL = req.getContextPath() + "/login.xhtml";
		String authLoginURL = req.getContextPath() + "/auth/login.xhtml";

		if (req.getRequestURI().equals(loginURL)) {
			System.out.println("normal " + loginURL + " ' i talep etti");
			if (credentials != null && credentials.isIsloggedin() == true) {
				if(credentials.getRole().equals("admin") || credentials.getRole().equals("superadmin")){
					System.out.println("normal login olmasına rağmen yine logini çağırdı");
					res.sendRedirect(req.getContextPath() + "/auth/index.xhtml");
					return;
					
				}else
				{
				System.out.println("normal login olmasına rağmen yine logini çağırdı");
				res.sendRedirect(req.getContextPath() + "/home.xhtml");
				return;
				}
			} else {
				System.out
						.println("login olmak istiyor faces servlet e yönlendirilecek");
				chain.doFilter(arg0, arg1);
			}
			
		}else if(req.getRequestURI().contains("/auth/")){
			
			System.out.println("/auth/  patternine uyduğundan dofilter calisti");
			chain.doFilter(arg0, arg1);
			
		}
		
		else if (credentials == null) {
			System.out.println("credentials null geliyorrrr");
			res.sendRedirect(req.getContextPath() + "/login.xhtml");

		} else if (credentials.getUsername() != null
				&& credentials.getUsername().length() != 0
				&& credentials.isIsloggedin() == true) {
			System.out.println("credentials null degil");
			System.out.println("login olmus faces servlet'e yönlendiriliyor");
			chain.doFilter(arg0, arg1);

		} else {
			credentials.setIsloggedin(false);
			credentials.setUsername(null);
			credentials.setPassword(null);
			credentials.setRole(null);
			System.out.println("credentials null degil fakat giris basarisiz");
			System.out.println("login olmamis yönlendirilecek");
			res.sendRedirect(req.getContextPath() + "/login.xhtml");

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
