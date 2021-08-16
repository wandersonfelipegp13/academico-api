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
import com.ifurutai.academico.domain.model.Turma;
import com.ifurutai.academico.domain.model.TurmaInputModel;
import com.ifurutai.academico.domain.model.TurmaResumoModel;
import com.ifurutai.academico.domain.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<TurmaResumoModel> listar() {
		return toCollectionModel(turmaService.buscarTurmas());
	}

	@GetMapping("/{turmaId}")
	public ResponseEntity<TurmaResumoModel> buscar(@PathVariable Long turmaId) {
		if (!turmaService.existeTurmaPorId(turmaId))
			return ResponseEntity.notFound().build();
		Turma turmaRes = turmaService.buscarTurmaPorId(turmaId);
		TurmaResumoModel turmaModel = toModel(turmaRes);
		return ResponseEntity.ok(turmaModel);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TurmaResumoModel adicionar(@Valid @RequestBody TurmaInputModel turmaInputModel) {
		Turma turma = toEntity(turmaInputModel);
		Turma turmaRes = turmaService.inserirTurma(turma);
		TurmaResumoModel turmaModel = toModel(turmaRes);
		return turmaModel;
	}

	@PutMapping("/{turmaId}")
	public ResponseEntity<TurmaResumoModel> atualizar(@Valid @PathVariable Long turmaId, @RequestBody TurmaInputModel turmaInput) {
		Turma turma = toEntity(turmaInput);
		if (!turmaService.existeTurmaPorId(turmaId))
			return ResponseEntity.notFound().build();
		Turma turmaRes = turmaService.atualizarTurma(turmaId, turma);
		TurmaResumoModel turmaModel = toModel(turmaRes); 
		return ResponseEntity.ok(turmaModel);
	}
	
	@DeleteMapping("/{turmaId}")
	public ResponseEntity<Void> remover(@PathVariable Long turmaId) {
		if (!turmaService.existeTurmaPorId(turmaId))
			return ResponseEntity.notFound().build();
		turmaService.excluirTurma(turmaId);
		return ResponseEntity.noContent().build();
	}

	private TurmaResumoModel toModel(Turma turma) {
		return modelMapper.map(turma, TurmaResumoModel.class);
	}
	
	private List<TurmaResumoModel> toCollectionModel(List<Turma> turmas) {
		return turmas.stream().map(turma -> toModel(turma)).collect(Collectors.toList());
	}
	
	private Turma toEntity(TurmaInputModel turmaModel) {
		return modelMapper.map(turmaModel, Turma.class);
	}

}
