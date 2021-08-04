package com.ifurutai.academico.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifurutai.academico.domain.model.Professor;
import com.ifurutai.academico.domain.repository.ProfessorRepository;

@RestController
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository profRepository;
	
	@GetMapping("/professores")
	public List<Professor> listar() {
		return profRepository.findAll();
	}
	
}

