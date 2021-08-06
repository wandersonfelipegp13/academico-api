package com.ifurutai.academico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifurutai.academico.domain.exception.NegocioException;
import com.ifurutai.academico.domain.model.Professor;
import com.ifurutai.academico.domain.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository profRepository;

	@Override
	public Professor inserirProfessor(Professor prof) {
		Professor profExistente = profRepository.findByEmail(prof.getEmail());
		// Não existe o Professor e não existe outro Professor com o mesmo email
		if(profExistente != null && !profExistente.equals(prof))
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		return profRepository.save(prof);
	}

	@Override
	public Professor atualizarProfessor(Long profId, Professor prof) {
		if(!profRepository.existsById(profId))
			return null;
		prof.setId(profId); // garante que seja executado um update e não um insert
		prof = profRepository.save(prof);
		return prof;
	}

	@Override
	public Professor excluirProfessor(Long profId) {
		var prof = buscarProfessorPorId(profId);
		if(!profRepository.existsById(profId))
			return null;
		profRepository.deleteById(profId);
		return prof;
	}

	@Override
	public List<Professor> buscarProfessores() {
		return profRepository.findAll();
	}

	@Override
	public Professor buscarProfessorPorId(Long profId) {
		Optional<Professor> prof = profRepository.findById(profId);
		// verifica se cliente tem algum valor (não nulo)
		if(prof.isPresent())
			return prof.get();
		return null;
	}

	@Override
	public Professor buscarProfessorPorEmail(String email) {
		Professor prof = profRepository.findByEmail(email);
		if(prof != null)
			return prof;
		return null;
	}

	@Override
	public Boolean existeProfessorPorId(Long profId) {
		if(!profRepository.existsById(profId))
			return false;
		return true;
	}
	
}
