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

import com.ifurutai.academico.domain.model.Aluno;
import com.ifurutai.academico.domain.model.AlunoInputModel;
import com.ifurutai.academico.domain.model.AlunoModel;
import com.ifurutai.academico.domain.model.AlunoResumoModel;
import com.ifurutai.academico.domain.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<AlunoResumoModel> listar() {
		return toCollectionModel(alunoService.buscarAlunos());
	}
	
	@GetMapping("/{alunoId}")
	public ResponseEntity<AlunoResumoModel> buscar(@PathVariable Long alunoId) {
		if (!alunoService.existeAlunoPorId(alunoId))
			return ResponseEntity.notFound().build();
		Aluno alunoRes = alunoService.buscarAlunoPorId(alunoId);
		AlunoResumoModel alunoModel = toModel(alunoRes);
		return ResponseEntity.ok(alunoModel);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoResumoModel adicionar(@Valid @RequestBody AlunoInputModel alunoInputModel) {
		Aluno aluno = toEntity(alunoInputModel);
		Aluno alunoRes = alunoService.inserirAluno(aluno);
		AlunoResumoModel alunoModel = toModel(alunoRes);
		return alunoModel;
	}
	
	@PutMapping("/{alunoId}")
	public ResponseEntity<AlunoModel> atualizar(@Valid @PathVariable Long alunoId, @RequestBody AlunoInputModel alunoInput) {
		Aluno aluno = toEntity(alunoInput);
		if (!alunoService.existeAlunoPorId(alunoId))
			return ResponseEntity.notFound().build();
		Aluno alunoRes = alunoService.atualizarAluno(alunoId, aluno);
		AlunoModel alunoModel = toTestModel(alunoRes); 
		return ResponseEntity.ok(alunoModel);
	}

	@DeleteMapping("/{alunoId}")
	public ResponseEntity<Void> remover(@PathVariable Long alunoId) {
		if (!alunoService.existeAlunoPorId(alunoId))
			return ResponseEntity.notFound().build();
		alunoService.excluirAluno(alunoId);
		return ResponseEntity.noContent().build();
	}

	private AlunoResumoModel toModel(Aluno aluno) {
		return modelMapper.map(aluno, AlunoResumoModel.class);
	}
	
	private AlunoModel toTestModel(Aluno aluno) {
		return modelMapper.map(aluno, AlunoModel.class);
	}
	
	private List<AlunoResumoModel> toCollectionModel(List<Aluno> alunos) {
		return alunos.stream().map(aluno -> toModel(aluno)).collect(Collectors.toList());
	}

	private Aluno toEntity(AlunoInputModel alunoModel) {
		return modelMapper.map(alunoModel, Aluno.class);
	}

}
