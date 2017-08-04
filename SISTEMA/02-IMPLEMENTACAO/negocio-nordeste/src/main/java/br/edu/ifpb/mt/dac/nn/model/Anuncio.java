package br.edu.ifpb.mt.dac.nn.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ANUNCIO")
@NamedQueries({
		@NamedQuery(name = "Anuncio.buscarPorTitulo", query = "SELECT a FROM Anuncio a WHERE LOWER(a.titulo) LIKE LOWER(:titulo)"),
		@NamedQuery(name = "Anuncio.buscarPorDescricao", query = "SELECT a FROM Anuncio a WHERE LOWER(a.descricao) LIKE LOWER(:descricao)"), })
public class Anuncio implements Serializable {

	private static final long serialVersionUID = 638476832749347364L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITULO")
	@NotNull
	private String titulo;

	@Column(name = "DESCRICAO")
	@NotNull
	private String descricao;

	@Column(name = "PRECO")
	@NotNull
	private Double preco;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "estado", column = @Column(name = "ESTADO")),
			@AttributeOverride(name = "cidade", column = @Column(name = "CIDADE")) })
	@NotNull
	private Localizacao localizacao;

	@Column(name = "DATA_PUCLICACAO")
	@NotNull
	private Date dataPublicacao;
	@Lob
	@Column(name = "IMAGEM", columnDefinition = "mediumblob")
	@NotNull
	private byte[] imagem;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ANUNCIANTE_FK")
	@NotNull
	private Anunciante anunciante;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "anuncio")
	@Fetch(FetchMode.SUBSELECT)
	private List<Visualizacao> vizualizacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataHoraPublicacao) {
		this.dataPublicacao = dataHoraPublicacao;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public List<Visualizacao> getVizualizacoes() {
		return vizualizacoes;
	}

	public void setVizualizacoes(List<Visualizacao> vizualizacoes) {
		this.vizualizacoes = vizualizacoes;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPublicacao == null) ? 0 : dataPublicacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(imagem);
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Anuncio other = (Anuncio) obj;
		if (dataPublicacao == null) {
			if (other.dataPublicacao != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (localizacao != other.localizacao)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(imagem, other.imagem))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", preco=" + preco
				+ ", localizacao=" + localizacao + ", dataPublicacao=" + dataPublicacao + ", imagem="
				+ Arrays.toString(imagem) + "]";
	}

}