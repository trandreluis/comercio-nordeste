package br.edu.ifpb.mt.dac.nn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ifpb.mt.dac.nn.enumerations.StatusSolicitacao;

@Entity
@Table(name = "SOLICITACAO_NEGOCIO")
public class SolicitacaoNegocio implements Serializable {

	private static final long serialVersionUID = 249848935007L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "CONTRATANTE_COMPRADOR_FK", nullable = false)
	private Anunciante contratanteComprador;

	@OneToOne
	@JoinColumn(name = "ANUNCIO_FK", nullable = false)
	private Anuncio anuncio;

	@OneToOne
	@JoinColumn(name = "VENDEDOR_FORNECEDOR_FK", nullable = false)
	private Anunciante vendedorFornecedor;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private StatusSolicitacao status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Anunciante getContratanteComprador() {
		return contratanteComprador;
	}

	public void setContratanteComprador(Anunciante contratanteComprador) {
		this.contratanteComprador = contratanteComprador;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public Anunciante getVendedorFornecedor() {
		return vendedorFornecedor;
	}

	public void setVendedorFornecedor(Anunciante vendedorFornecedor) {
		this.vendedorFornecedor = vendedorFornecedor;
	}

	public StatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacao status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anuncio == null) ? 0 : anuncio.hashCode());
		result = prime * result + ((contratanteComprador == null) ? 0 : contratanteComprador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vendedorFornecedor == null) ? 0 : vendedorFornecedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoNegocio other = (SolicitacaoNegocio) obj;
		if (anuncio == null) {
			if (other.anuncio != null)
				return false;
		} else if (!anuncio.equals(other.anuncio))
			return false;
		if (contratanteComprador == null) {
			if (other.contratanteComprador != null)
				return false;
		} else if (!contratanteComprador.equals(other.contratanteComprador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		if (vendedorFornecedor == null) {
			if (other.vendedorFornecedor != null)
				return false;
		} else if (!vendedorFornecedor.equals(other.vendedorFornecedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SolicitacaoNegocio [id=" + id + ", contratanteComprador=" + contratanteComprador + ", anuncio="
				+ anuncio + ", vendedorFornecedor=" + vendedorFornecedor + ", status=" + status + "]";
	}

}