package com.ifurutai.academico.domain.service;

import java.util.List;

import com.ifurutai.academico.domain.model.Curso;

public interface CursoService {
	
	public abstract Curso inserirCurso(Curso curso);
	public abstract Curso atualizarCurso(Long cursoId, Curso curso);
	public abstract Curso excluirCurso(Long cursoId);
	public abstract List<Curso> buscarCursos();
	public abstract Curso buscarCursoPorId(Long cursoId);
	public abstract Boolean existeCursoPorId(Long cursoId);
	
}
