package com.ifurutai.academico.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
public class Turma {

	@NotNull(groups = ValidationGroup.TurmaId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 255)
	private String turno;

	@NotNull
	@Column(name = "data_inicio")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataInicio;

	@Column(name = "data_fim")
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFim;

	@NotBlank
	@Size(max = 255)
	private String horario;

	@NotNull
	@Column(name = "qtd_vagas")
	private Long qtdVagas;

	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroup.CursoId.class)
	@NotNull
	@ManyToOne
	private Curso curso;

	public Turma() {
	}

	public Turma(Long id, String turno, OffsetDateTime dataInicio, OffsetDateTime dataFim, String horario,
			Long qtdVagas, Curso curso) {
		super();
		this.id = id;
		this.turno = turno;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horario = horario;
		this.qtdVagas = qtdVagas;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public OffsetDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(OffsetDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public OffsetDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(OffsetDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Long getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(Long qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qtdVagas == null) ? 0 : qtdVagas.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		Turma other = (Turma) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (qtdVagas == null) {
			if (other.qtdVagas != null)
				return false;
		} else if (!qtdVagas.equals(other.qtdVagas))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", turno=" + turno + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", horario=" + horario + ", qtdVagas=" + qtdVagas + ", curso=" + curso + "]";
	}

}
