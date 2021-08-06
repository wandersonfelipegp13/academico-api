package com.ifurutai.academico.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ifurutai.academico.domain.model.Professor;
import com.ifurutai.academico.domain.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorService profService;
	
	@GetMapping
	public List<Professor> listar() {
		return profService.buscarProfessores();
	}
	
	// Singleton resources
	@GetMapping("/{profId}") // path variable
	public ResponseEntity<Professor> buscar(@PathVariable Long profId) {
		if(!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		Professor profRes = profService.buscarProfessorPorId(profId);
		return ResponseEntity.ok(profRes);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Professor adicionar(@Valid @RequestBody Professor prof) {
		return profService.inserirProfessor(prof);
	}
	
	@PutMapping("/{profId}")
	public ResponseEntity<Professor> atualizar(@Valid @PathVariable Long profId, @RequestBody Professor prof){
		if(!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		Professor profRes = profService.atualizarProfessor(profId, prof);
		return ResponseEntity.ok(profRes);
	}
	
	@DeleteMapping("/{profId}")
	public ResponseEntity<Void> remover(@PathVariable Long profId){
		if(!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		profService.excluirProfessor(profId);
		// noContent é o código 204: sucesso, mas sem corpo
		return ResponseEntity.noContent().build();
	}
	
}

