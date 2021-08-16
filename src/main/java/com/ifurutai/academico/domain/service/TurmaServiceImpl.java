package com.ifurutai.academico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifurutai.academico.domain.model.Turma;
import com.ifurutai.academico.domain.repository.TurmaRepository;

@Service
public class TurmaServiceImpl implements TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;

	@Override
	public Turma inserirTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	@Override
	public Turma atualizarTurma(Long turmaId, Turma turma) {
		if(!turmaRepository.existsById(turmaId))
			return null;
		turma.setId(turmaId);
		turma = turmaRepository.save(turma);
		return turma;
	}

	@Override
	public Turma excluirTurma(Long turmaId) {
		var prof = buscarTurmaPorId(turmaId);
		if(!turmaRepository.existsById(turmaId))
			return null;
		turmaRepository.deleteById(turmaId);
		return prof;
	}

	@Override
	public List<Turma> buscarTurmas() {
		return turmaRepository.findAll();
	}

	@Override
	public Turma buscarTurmaPorId(Long turmaId) {
		Optional<Turma> turma = turmaRepository.findById(turmaId);
		if(turma.isPresent())
			return turma.get();
		return null;
	}

	@Override
	public Boolean existeTurmaPorId(Long turmaId) {
		if(!turmaRepository.existsById(turmaId))
			return false;
		return true;
	}
	
}
