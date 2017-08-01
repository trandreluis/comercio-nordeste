package br.edu.ifpb.mt.dac.nn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoVisualizacao;

@Entity
@Table(name = "VISUALIZACAO")
@NamedQueries({
	@NamedQuery(name = "Visualizacao.buscarPorData", query = "SELECT v FROM Visualizacao v WHERE v.dataVisualizacao = :dataVisualizacao"),
	@NamedQuery(name = "Visualizacao.buscarPorTipo", query = "SELECT v FROM Visualizacao v WHERE v.tipo = :tipo"),
	@NamedQuery(name = "Visualizacao.buscarPorAnuncio", query = "SELECT v FROM Visualizacao v WHERE v.anuncio = :anuncio") })
public class Visualizacao implements Serializable {

	private static final long serialVersionUID = 2832989330047L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VISUALIZACAO", nullable = false)
	private Date dataVisualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private TipoVisualizacao tipo;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ANUNCIO_FK")
	private Anunciante anuncio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVisualizacao() {
		return dataVisualizacao;
	}

	public void setVisualizacao(Date dataVisualizacao) {
		this.dataVisualizacao = dataVisualizacao;
	}

	public Anunciante getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anunciante anuncio) {
		this.anuncio = anuncio;
	}
	
	public TipoVisualizacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoVisualizacao tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anuncio == null) ? 0 : anuncio.hashCode());
		result = prime * result + ((dataVisualizacao == null) ? 0 : dataVisualizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Visualizacao other = (Visualizacao) obj;
		if (anuncio == null) {
			if (other.anuncio != null)
				return false;
		} else if (!anuncio.equals(other.anuncio))
			return false;
		if (dataVisualizacao == null) {
			if (other.dataVisualizacao != null)
				return false;
		} else if (!dataVisualizacao.equals(other.dataVisualizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visualizacao [id=" + id + ", dataNascimento=" + dataVisualizacao + ", tipo=" + tipo + ", anuncio="
				+ anuncio + "]";
	}

}