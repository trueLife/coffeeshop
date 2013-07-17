package com.bets.filtre;

import java.io.IOException;

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

@WebFilter("*.xhtml")
// or *.xhtml extension mapping
public class LoginFiltre implements Filter {

	private Credentials credentials;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		System.out.println("address :" + req.getRemoteAddr());

		// Credentials credentials = (Credentials)
		// ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("credentials");
		// System.out.println("session null değil ");
		// System.out.println("content length: " + req.getContentLength());
		// System.out.println("context path :  " + req.getContextPath());
		// System.out.println("local address: " + req.getLocalAddr());
		// System.out.println("remote address: " + req.getRemoteAddr());
		// System.out.println("method :  " + req.getMethod());
		// System.out.println("Locate :  " + req.getLocale());
		// System.out.println("Local Port :  " + req.getLocalPort());
		// System.out.println("User Principal :  " + req.getUserPrincipal());
		// System.out.println("Request Url :  " + req.getRequestURL());

		credentials = (Credentials) req.getSession(true).getAttribute(
				"credentials");

		String loginURL = req.getContextPath() + "/login.xhtml";
		if (req.getRequestURI().equals(loginURL)) {
			System.out
					.println("login olmak istiyor faces servlet e yönlendirilecek");
			chain.doFilter(arg0, arg1);
			return;
		} else if (credentials == null) {
			System.out.println("credentials null geliyorrrr");
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;

		} else if (credentials.getUsername() != null
				&& credentials.getUsername().length() != 0
				&& credentials.isIsloggedin() == true) {
			System.out.println("credentials null degil");
			System.out.println("login olmus faces servlet'e yönlendiriliyor");
			chain.doFilter(arg0, arg1);
			return;
		} else {
			System.out.println("credentials null degil fakat giris basarisiz");
			System.out.println("login olmamis yönlendirilecek");
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
