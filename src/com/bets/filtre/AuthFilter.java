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
import javax.xml.ws.Response;

import com.bets.managed.Credentials;

@WebFilter(urlPatterns = { "/auth/*" }, dispatcherTypes = {
		DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.REQUEST,
		DispatcherType.ERROR, DispatcherType.INCLUDE })
public class AuthFilter implements Filter {
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
		System.out.println("auth requested uri : " + req.getRequestURI());
		System.out.println("auth requested url : " + req.getRequestURL());
		String loginURL = req.getContextPath() + "/auth/login.xhtml";
		
		// login olmusmu ve admin yada superadminden birisimi?
		if (credentials != null && credentials.getUsername() != null
				&& credentials.getUsername().length() != 0
				&& credentials.isIsloggedin() == true) {

			if (credentials.getRole().equals("admin")
					|| credentials.getRole().equals("superadmin")) {

				// login olmus ve buna rağmen login sayfasını mı çağırıyor
				if (req.getRequestURI().equals(loginURL)) {
					System.out
							.println("login olan admin yada superadmin ve login sayfasını çağırdı");
					res.sendRedirect(req.getContextPath()
							+ "/auth/index.xhtml");
					// login olmus ve auth altında herhangi bir kaynağı
					// çağırıyor
				} else {
					System.out
							.println("login alan admin yada superadmin kaynak talep etti");
					chain.doFilter(arg0, arg1);
				}
			} else {
				// login olmuş ancak admin ve superadminden biri değil
				res.sendRedirect(req.getContextPath() + "/login.xhtml");
			}
		} else {
			if (req.getRequestURI().equals(loginURL)) {
				System.out
						.println("admin login olmamış ve login olmak istiyor");
				chain.doFilter(arg0, arg1);
			} else {
				System.out
						.println("login olmamış olmasına rağmen admine ait kaynak talep ediyor /auth/login.xhtml 'e gönderildi");
				res.sendRedirect(req.getContextPath() + "/auth/login.xhtml");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
