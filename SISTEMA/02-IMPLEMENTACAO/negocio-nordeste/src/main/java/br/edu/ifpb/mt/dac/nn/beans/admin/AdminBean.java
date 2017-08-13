package br.edu.ifpb.mt.dac.nn.beans.admin;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.nn.beans.AbstractBean;
import br.edu.ifpb.mt.dac.nn.beans.LoggedBean;
import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;
import br.edu.ifpb.mt.dac.nn.model.Conta;
import br.edu.ifpb.mt.dac.nn.model.Operador;
import br.edu.ifpb.mt.dac.nn.services.AnuncianteService;
import br.edu.ifpb.mt.dac.nn.services.ContaService;
import br.edu.ifpb.mt.dac.nn.util.mensagens.MessageUtils;

@Named
@ViewScoped
public class AdminBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 19855772597465L;

	@Inject
	private ContaService contaService;
	@Inject
	private AnuncianteService anuncianteService;
	private List<Conta> contas;
	private Conta contaLogada;

	private Operador admin;

	public void preRenderView() {
		try {
			contaLogada = contaService.buscarPorUsername(getUsernameUsuarioLogado());
			contas = contaService.buscarTodos();
			admin = contaLogada.getAdmin();
		} catch (Exception e) {
			MessageUtils.messageError("A conta não pôde ser obtida.");
			e.printStackTrace();
		}
	}

	public void excluir(Conta conta) {
		try {
			if(conta.getTipo() == TipoUsuario.ADMIN) {
				contaService.deletar(conta);
			} else {
				anuncianteService.deletar(conta.getAnunciante());
			}
			if(contaService.buscarPorID(conta.getId()) == null) {
				MessageUtils.messageSucess("Conta deletada.");				
			} else {
				MessageUtils.messageError("Não foi possível excluir a conta.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.messageError(e.getMessage());
		}
	}

	public Conta getConta() {
		return contaLogada;
	}

	public void setConta(Conta conta) {
		this.contaLogada = conta;
	}

	public Operador getAdmin() {
		return admin;
	}

	public void setAdmin(Operador admin) {
		this.admin = admin;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}