package com.ifurutai.academico.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifurutai.academico.domain.model.Professor;

@RestController
public class ProfessorController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/professores")
	public List<Professor> listar() {
		return manager.createQuery("from Professor", Professor.class).getResultList();
	}
	
}

