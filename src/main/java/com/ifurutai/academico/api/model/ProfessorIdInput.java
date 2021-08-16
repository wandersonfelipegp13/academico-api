package com.ifurutai.academico.api.model;

import javax.validation.constraints.NotNull;

public class ProfessorIdInput {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
