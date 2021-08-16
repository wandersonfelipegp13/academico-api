package com.ifurutai.academico.domain.model;

import javax.validation.constraints.NotNull;

public class TurmaIdInput {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
