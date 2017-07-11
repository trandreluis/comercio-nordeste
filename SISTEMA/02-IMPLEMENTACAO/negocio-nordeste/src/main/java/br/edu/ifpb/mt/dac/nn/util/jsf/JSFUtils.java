package br.edu.ifpb.mt.dac.nn.util.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class JSFUtils implements Serializable {

	private static final long serialVersionUID = 22089974L;

	public static void rederTo(String pagina) {
	      try {
	            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
	            FacesContext.getCurrentInstance().responseComplete();
	        } catch (IOException ex) {
	            throw new FacesException(ex);
	        }
	}

	public static void setParam(String tag, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(tag, obj);
	}

	public static Object getParam(String tag) {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(tag);
	}
}