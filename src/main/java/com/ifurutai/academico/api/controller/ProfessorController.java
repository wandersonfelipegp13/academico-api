package com.ifurutai.academico.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// Singleton resources
	@GetMapping("/professores/{profId}") // path variable
	public ResponseEntity<Professor>  buscar(@PathVariable Long profId) {
		Optional<Professor> prof = profRepository.findById(profId);
		// verifica se cliente tem algum valor (não nulo)
		if(prof.isPresent())
			return ResponseEntity.ok(prof.get());
		return ResponseEntity.notFound().build();
	}
	
}

