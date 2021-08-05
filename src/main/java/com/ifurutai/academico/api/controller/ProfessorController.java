package com.ifurutai.academico.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ifurutai.academico.domain.model.Professor;
import com.ifurutai.academico.domain.repository.ProfessorRepository;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository profRepository;
	
	@GetMapping
	public List<Professor> listar() {
		return profRepository.findAll();
	}
	
	// Singleton resources
	@GetMapping("/{profId}") // path variable
	public ResponseEntity<Professor> buscar(@PathVariable Long profId) {
		Optional<Professor> prof = profRepository.findById(profId);
		// verifica se cliente tem algum valor (não nulo)
		if(prof.isPresent())
			return ResponseEntity.ok(prof.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Professor adicionar(@RequestBody Professor prof) {
		return profRepository.save(prof);
	}
	
	@PutMapping("/{profId}")
	public ResponseEntity<Professor> atualizar(@PathVariable Long profId, @RequestBody Professor prof){
		if(!profRepository.existsById(profId))
			return ResponseEntity.notFound().build();
		prof.setId(profId); // garante que seja executado um update e não um insert
		prof = profRepository.save(prof);
		return ResponseEntity.ok(prof);
	}
	
	@DeleteMapping("/{profId}")
	public ResponseEntity<Void> remover(@PathVariable Long profId){
		if(!profRepository.existsById(profId))
			return ResponseEntity.notFound().build();
		profRepository.deleteById(profId);
		return ResponseEntity.noContent().build();
	}
	
}

