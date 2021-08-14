package com.ifurutai.academico.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.ifurutai.academico.domain.model.ProfessorInputModel;
import com.ifurutai.academico.domain.model.ProfessorModel;
import com.ifurutai.academico.domain.model.ProfessorResumoModel;
import com.ifurutai.academico.domain.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService profService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<ProfessorResumoModel> listar() {
		return toCollectionModel(profService.buscarProfessores());
	}

	// Singleton resources
	@GetMapping("/{profId}") // path variable
	public ResponseEntity<ProfessorResumoModel> buscar(@PathVariable Long profId) {
		if (!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		Professor profRes = profService.buscarProfessorPorId(profId);
		ProfessorResumoModel profModel = toModel(profRes);
		return ResponseEntity.ok(profModel);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProfessorResumoModel adicionar(@Valid @RequestBody ProfessorInputModel profInputModel) {
		Professor prof = toEntity(profInputModel);
		Professor profRes = profService.inserirProfessor(prof);
		ProfessorResumoModel profModel = toModel(profRes);
		return profModel;
	}

	@PutMapping("/{profId}")
	public ResponseEntity<ProfessorModel> atualizar(@Valid @PathVariable Long profId, @RequestBody ProfessorInputModel profInput) {
		Professor prof = toEntity(profInput);
		if (!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		Professor profRes = profService.atualizarProfessor(profId, prof);
		ProfessorModel profModel = toTestModel(profRes); 
		return ResponseEntity.ok(profModel);
	}

	@DeleteMapping("/{profId}")
	public ResponseEntity<Void> remover(@PathVariable Long profId) {
		if (!profService.existeProfessorPorId(profId))
			return ResponseEntity.notFound().build();
		profService.excluirProfessor(profId);
		// noContent é o código 204: sucesso, mas sem corpo
		return ResponseEntity.noContent().build();
	}

	private ProfessorResumoModel toModel(Professor prof) {
		return modelMapper.map(prof, ProfessorResumoModel.class);
	}
	
	private ProfessorModel toTestModel(Professor prof) {
		return modelMapper.map(prof, ProfessorModel.class);
	}

	private List<ProfessorResumoModel> toCollectionModel(List<Professor> profs) {
		return profs.stream().map(prof -> toModel(prof)).collect(Collectors.toList());
	}

	private Professor toEntity(ProfessorInputModel profModel) {
		return modelMapper.map(profModel, Professor.class);
	}

}
