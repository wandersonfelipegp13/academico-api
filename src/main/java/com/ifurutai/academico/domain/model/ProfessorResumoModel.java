package com.ifurutai.academico.domain.model;

public class ProfessorResumoModel {

	private Long id;
	private String nome;
	private String titulacao;

	public ProfessorResumoModel() {
		super();
	}
	
	public ProfessorResumoModel(Long id, String nome, String titulacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.titulacao = titulacao;
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

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

}
