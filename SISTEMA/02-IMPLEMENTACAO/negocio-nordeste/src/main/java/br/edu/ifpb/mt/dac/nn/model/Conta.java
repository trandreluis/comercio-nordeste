package br.edu.ifpb.mt.dac.nn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.mt.dac.nn.enumerations.TipoUsuario;

public abstract class Conta {
	
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO", nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
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

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

}