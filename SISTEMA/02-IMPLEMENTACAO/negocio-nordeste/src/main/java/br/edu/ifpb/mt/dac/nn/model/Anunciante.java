package br.edu.ifpb.mt.dac.nn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.mt.dac.nn.enumerations.NivelAnunciante;

@Entity
@Table(name = "ANUNCIANTE")
@DiscriminatorValue("Anunciante")
@NamedQueries({
		@NamedQuery(name = "Anunciante.buscarPorEmail", query = "SELECT a FROM Anunciante a WHERE LOWER(a.email) LIKE LOWER(:email)"),
		@NamedQuery(name = "Anunciante.buscarPorNome", query = "SELECT a FROM Anunciante a WHERE LOWER(a.nome) LIKE LOWER(:nome)"),
		@NamedQuery(name = "Anunciante.buscarPorUsername", query = "SELECT a FROM Anunciante a WHERE LOWER(a.username) LIKE LOWER(:username)"),
		@NamedQuery(name = "Anunciante.buscarPorNivel", query = "SELECT a FROM Anunciante a WHERE LOWER(a.nivel) LIKE LOWER(:nivel)")
})
public class Anunciante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotBlank
	@Column(name = "NOME", nullable=false)
	private String nome;

	@Column(name = "SOBRENOME", nullable=false)
	private String sobrenome;

	@Column(name = "USERNAME", nullable=false)
	private String username;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO", nullable=false)
	private Date dataNascimento;

	@Column(name = "EMAIL", nullable=false)
	private String email;

	@Column(name = "SENHA", nullable=false)
	private String senha;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "anunciante")
	private List<Anuncio> anuncios;

	@Enumerated(EnumType.STRING)
	@Column(name = "NIVEL")
	private NivelAnunciante nivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public NivelAnunciante getNivel() {
		return nivel;
	}

	public void setNivel(NivelAnunciante nivel) {
		this.nivel = nivel;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}