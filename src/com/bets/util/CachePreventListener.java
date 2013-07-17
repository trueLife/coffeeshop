package com.bets.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;


public class CachePreventListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		 FacesContext facesContext = event.getFacesContext();
	        HttpServletResponse response = (HttpServletResponse) facesContext
	          .getExternalContext().getResponse();
//	        response.addHeader("Pragma", "no-cache");
//	        response.addHeader("Cache-Control", "no-cache");
//	        response.addHeader("Cache-Control", "no-store");
//	        response.addHeader("Cache-Control", "must-revalidate");
//	        response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
	        response.setHeader("Server", "No Server");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}

