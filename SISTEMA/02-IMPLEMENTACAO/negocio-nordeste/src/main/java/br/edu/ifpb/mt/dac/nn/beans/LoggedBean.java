package br.edu.ifpb.mt.dac.nn.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@RequestScoped
public class LoggedBean extends AbstractBean {

	private static final long serialVersionUID = -7437667367775973347L;

	public static void logout() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.invalidate();
			ec.redirect(ec.getApplicationContextPath() + "/busca.xhtml?faces-redirect=true");
		} catch (Exception e) {
			MessageUtils.messageError("Ocorreu um erro ao durante a realizar do logout.");
		}
	}

}