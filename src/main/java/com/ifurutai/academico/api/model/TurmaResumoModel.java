package com.ifurutai.academico.api.model;

import java.time.OffsetDateTime;

public class TurmaResumoModel {

	private Long id;
	private String turno;
	private OffsetDateTime dataInicio;
	private OffsetDateTime dataFim;
	private String horario;
	private Long qtdVagas;
	private CursoModel curso;

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

	public CursoModel getCurso() {
		return curso;
	}

	public void setCurso(CursoModel curso) {
		this.curso = curso;
	}

}
