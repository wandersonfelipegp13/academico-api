package com.ifurutai.academico.api.model;

import javax.validation.constraints.NotBlank;

public class ProfessorInputModel {

	@NotBlank
	private String nome;

	@NotBlank
	private String titulacao;

	@NotBlank
	private String email;

	@NotBlank
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
