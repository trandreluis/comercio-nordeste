package br.edu.ifpb.mt.dac.nn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;

@Entity
@Table(name = "CONTA")
@NamedQueries({
		@NamedQuery(name = "Conta.buscarPorUsername", query = "SELECT c FROM Conta c WHERE c.username = :username"),
		@NamedQuery(name = "Conta.buscarPorEmail", query = "SELECT c FROM Conta c WHERE c.email = :email"),
		@NamedQuery(name = "Conta.buscarPorTipo", query = "SELECT c FROM Conta c WHERE c.tipo = :tipo") })
public class Conta implements Serializable {

	private static final long serialVersionUID = -4442335252642678115L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@OneToOne(mappedBy = "conta")
	private Anunciante anunciante;

	@OneToOne(mappedBy = "conta")
	private Operador admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public Operador getAdmin() {
		return admin;
	}

	public void setAdmin(Operador admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((anunciante == null) ? 0 : anunciante.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Conta other = (Conta) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (anunciante == null) {
			if (other.anunciante != null)
				return false;
		} else if (!anunciante.equals(other.anunciante))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipo != other.tipo)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", tipo=" + tipo + ", username=" + username + ", senha=" + senha + ", email=" + email
				+ ", anunciante=" + anunciante + ", admin=" + admin + "]";
	}

}