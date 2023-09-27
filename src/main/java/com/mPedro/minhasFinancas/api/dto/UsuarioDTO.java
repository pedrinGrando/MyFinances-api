package com.mPedro.minhasFinancas.api.dto;

public class UsuarioDTO {

	private String email;
	private String senha;
	private String nome;

	public UsuarioDTO(String email, String senha, String nome) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}

	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
