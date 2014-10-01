package br.com.samsung.modelo.bean;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int matricula;
	private String login;
	private String senha;
	private String telefone;
	private String email;
	private Calendar dtCadastro;
	
	
	
	public Calendar getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String usuario) {
		this.login = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
