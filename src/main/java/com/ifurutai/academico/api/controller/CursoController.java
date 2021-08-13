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

import com.ifurutai.academico.domain.model.Curso;
import com.ifurutai.academico.domain.model.StatusCurso;
import com.ifurutai.academico.domain.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping
	public List<Curso> listar() {
		return cursoService.buscarCursos();
	}

	@GetMapping("/{cursoId}") 
	public ResponseEntity<Curso> buscar(@PathVariable Long cursoId) {
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		Curso cursoRes = cursoService.buscarCursoPorId(cursoId);
		return ResponseEntity.ok(cursoRes);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Curso adicionar(/*@Valid*/ @RequestBody Curso curso) {
		curso.setStatus(StatusCurso.ABERTA);
		return cursoService.inserirCurso(curso);
	}

	@PutMapping("/{cursoId}")
	public ResponseEntity<Curso> atualizar(@Valid @PathVariable Long cursoId, @RequestBody Curso curso) {
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		Curso cursoRes = cursoService.atualizarCurso(cursoId, curso);
		return ResponseEntity.ok(cursoRes);
	}

	@DeleteMapping("/{cursoId}")
	public ResponseEntity<Void> remover(@PathVariable Long cursoId) {
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		cursoService.excluirCurso(cursoId);
		return ResponseEntity.noContent().build();
	}

}
