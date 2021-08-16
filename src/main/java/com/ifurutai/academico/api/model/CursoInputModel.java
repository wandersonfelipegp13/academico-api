package com.ifurutai.academico.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CursoInputModel {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@NotNull
	private BigDecimal preco;

	@NotBlank
	private String nivel;

	@Valid
	@NotNull
	private ProfessorIdInput professor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public ProfessorIdInput getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorIdInput professor) {
		this.professor = professor;
	}

}
