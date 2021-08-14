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

import com.ifurutai.academico.domain.exception.CursoModel;
import com.ifurutai.academico.domain.model.Curso;
import com.ifurutai.academico.domain.model.CursoInputModel;
import com.ifurutai.academico.domain.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<CursoModel> listar() {
		return toCollectionModel(cursoService.buscarCursos());
	}

	@GetMapping("/{cursoId}")
	public ResponseEntity<CursoModel> buscar(@PathVariable Long cursoId) {
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		Curso cursoRes = cursoService.buscarCursoPorId(cursoId);
		// DTO
		CursoModel cursoModel = toModel(cursoRes);
		return ResponseEntity.ok(cursoModel);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CursoModel adicionar(@Valid @RequestBody CursoInputModel cursoInput) {
		Curso curso = toEntity(cursoInput);
		Curso cursoRes = cursoService.inserirCurso(curso);
		CursoModel cursoModel = toModel(cursoRes);
		return cursoModel;
	}

	@PutMapping("/{cursoId}")
	public ResponseEntity<CursoModel> atualizar(@Valid @PathVariable Long cursoId,
			@RequestBody CursoInputModel cursoInput) {
		Curso curso = toEntity(cursoInput);
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		// como o modelo de entrada não tem status e no BD é obrigatório, eu busco e
		// seto novamente o status para inserir, o status é inserido pelo Service
		Curso c = cursoService.buscarCursoPorId(cursoId);
		curso.setStatus(c.getStatus());
		// curso.setStatus(StatusCurso.ABERTA);
		Curso cursoRes = cursoService.atualizarCurso(cursoId, curso);
		CursoModel cursoModel = toModel(cursoRes);
		return ResponseEntity.ok(cursoModel);
	}

	@DeleteMapping("/{cursoId}")
	public ResponseEntity<Void> remover(@PathVariable Long cursoId) {
		if (!cursoService.existeCursoPorId(cursoId))
			return ResponseEntity.notFound().build();
		cursoService.excluirCurso(cursoId);
		return ResponseEntity.noContent().build();
	}

	// metodos para fazer o mapeamento de Curso para CursoModel

	private CursoModel toModel(Curso curso) {
		return modelMapper.map(curso, CursoModel.class);
	}

	private List<CursoModel> toCollectionModel(List<Curso> cursos) {
		// percoro a lista, pegando um a um do tipo Curso e transformando cada um em
		// CursoModel.
		return cursos.stream().map(curso -> toModel(curso)).collect(Collectors.toList());
	}

	// Transforma modelo de representação em modelo de domínio
	private Curso toEntity(CursoInputModel cursoInputModel) {
		return modelMapper.map(cursoInputModel, Curso.class);
	}

}
