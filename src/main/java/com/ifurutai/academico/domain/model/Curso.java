package com.ifurutai.academico.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.ifurutai.academico.domain.ValidationGroup;
import com.ifurutai.academico.domain.exception.NegocioException;

@Entity
public class Curso {
	
	@NotNull(groups = ValidationGroup.CursoId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	private String descricao;

	@NotNull
	private BigDecimal preco;

	@NotBlank
	@Size(max = 20)
	private String nivel;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusCurso status;

	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroup.ProfessorId.class)
	@NotNull
	@ManyToOne
	private Professor professor;

	public Curso() {
	}

	public Curso(Long id, @NotBlank @Size(max = 60) String nome, @NotBlank String descricao, @NotNull BigDecimal preco,
			@NotBlank @Size(max = 20) String nivel, StatusCurso status, @NotNull Professor professor) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.nivel = nivel;
		this.status = status;
		this.professor = professor;
	}

	public boolean podeSerFinalizada() {
		return StatusCurso.ABERTA.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
	
	public void finalizar() {
		if(naoPodeSerFinalizada())
			throw new NegocioException("Curso não pode ser finalizado!");
		setStatus(StatusCurso.FINALIZADA);
	}
	
	public boolean podeSerCancelada() {
		return StatusCurso.ABERTA.equals(getStatus());
	}
	
	public boolean naoPodeSerCancelada() {
		return !podeSerCancelada();
	}
	
	public void cancelar() {
		if(naoPodeSerCancelada())
			throw new NegocioException("Curso não pode ser cancelado!");
		setStatus(StatusCurso.CANCELADA);
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

	public StatusCurso getStatus() {
		return status;
	}

	public void setStatus(StatusCurso status) {
		this.status = status;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", nivel="
				+ nivel + ", status=" + status + ", professor=" + professor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Curso other = (Curso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
