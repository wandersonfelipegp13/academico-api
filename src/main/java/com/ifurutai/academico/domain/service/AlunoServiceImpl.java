package com.ifurutai.academico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifurutai.academico.domain.exception.NegocioException;
import com.ifurutai.academico.domain.model.Aluno;
import com.ifurutai.academico.domain.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public Aluno inserirAluno(Aluno aluno) {
		Aluno AlunoExistenteCpf = alunoRepository.findByCpf(aluno.getCpf());
		Aluno AlunoExistenteEmail = alunoRepository.findByEmail(aluno.getEmail());
		if(AlunoExistenteCpf != null && !AlunoExistenteCpf.equals(aluno))
			throw new NegocioException("Já existe um cliente cadastrado com este CPF.");
		if(AlunoExistenteEmail != null && !AlunoExistenteEmail.equals(aluno))
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		return alunoRepository.save(aluno);
	}

	@Override
	public Aluno atualizarAluno(Long alunoId, Aluno aluno) {
		if(!alunoRepository.existsById(alunoId))
			return null;
		aluno.setId(alunoId);
		aluno = alunoRepository.save(aluno);
		return aluno;
	}

	@Override
	public Aluno excluirAluno(Long alunoId) {
		var aluno = buscarAlunoPorId(alunoId);
		if(!alunoRepository.existsById(alunoId))
			return null;
		alunoRepository.deleteById(alunoId);
		return aluno;
	}

	@Override
	public List<Aluno> buscarAlunos() {
		return alunoRepository.findAll();
	}

	@Override
	public Aluno buscarAlunoPorId(Long alunoId) {
		Optional<Aluno> aluno = alunoRepository.findById(alunoId);
		if(aluno.isPresent())
			return aluno.get();
		return null;
	}

	@Override
	public Aluno buscarAlunoPorEmail(String email) {
		Aluno aluno = alunoRepository.findByEmail(email);
		if(aluno != null)
			return aluno;
		return null;
	}

	@Override
	public Boolean existeAlunoPorId(Long alunoId) {
		if(!alunoRepository.existsById(alunoId))
			return false;
		return true;
	}

	@Override
	public Aluno buscarAlunoPorCpf(String cpf) {
		Aluno aluno = alunoRepository.findByCpf(cpf);
		if(aluno != null)
			return aluno;
		return null;
	}
	
}
