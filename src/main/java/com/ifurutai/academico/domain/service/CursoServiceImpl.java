package com.ifurutai.academico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifurutai.academico.domain.exception.NegocioException;
import com.ifurutai.academico.domain.model.Curso;
import com.ifurutai.academico.domain.model.Professor;
import com.ifurutai.academico.domain.model.StatusCurso;
import com.ifurutai.academico.domain.repository.CursoRepository;
import com.ifurutai.academico.domain.repository.ProfessorRepository;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	public Curso inserirCurso(Curso curso) {
		// Garantir regra de negócio: cursos com nomes únicos
		Curso cursoExistente = cursoRepository.findByNome(curso.getNome());
		// não existe curso e não existe outro curso com o mesmo nome
		if(cursoExistente != null && !cursoExistente.equals(curso))
			throw new NegocioException("Já existe um curso cadastrado com este nome.");
		
		if(curso.getProfessor().getId() == null)
			throw new NegocioException("Professor não informado");
		
		Professor prof = professorRepository.findById(curso.getProfessor().getId())
				.orElseThrow(() -> new NegocioException("Professor não encontrado."));
		curso.setProfessor(prof);
		// defino o status inicial
		curso.setStatus(StatusCurso.ABERTA);
		return cursoRepository.save(curso);
	}

	@Override
	public Curso atualizarCurso(Long cursoId, Curso curso) {
		// se o curso não existe, retorna null
		if(!cursoRepository.existsById(cursoId))
			return null;
		// verifico se existe o prof e gero uma exceção, caso não exista
		
		@SuppressWarnings("unused")
		Professor prof = professorRepository.findById(curso.getProfessor().getId()).orElseThrow(() -> new NegocioException("Professor não encontrado."));
		curso.setId(cursoId);
		curso = cursoRepository.save(curso);
		return curso;
	}

	@Override
	public Curso excluirCurso(Long cursoId) {
		var curso = buscarCursoPorId(cursoId);
		if(!cursoRepository.existsById(cursoId))
			return null;
		cursoRepository.deleteById(cursoId);
		return curso;
	}

	@Override
	public List<Curso> buscarCursos() {
		return cursoRepository.findAll();
	}

	@Override
	public Curso buscarCursoPorId(Long cursoId) {
		Optional<Curso> curso = cursoRepository.findById(cursoId);
		if(curso.isPresent())
			return curso.get();
		return null;
	}

	@Override
	public Boolean existeCursoPorId(Long cursoId) {
		if(!cursoRepository.existsById(cursoId))
			return false;
		return true;
	}
	
}
