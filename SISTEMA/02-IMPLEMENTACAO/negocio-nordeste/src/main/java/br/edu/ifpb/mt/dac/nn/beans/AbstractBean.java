package br.edu.ifpb.mt.dac.nn.beans;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 7876760017468149L;

	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}

	public String getUsernameUsuarioLogado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}

		return userPrincipal.getName();
	}

}