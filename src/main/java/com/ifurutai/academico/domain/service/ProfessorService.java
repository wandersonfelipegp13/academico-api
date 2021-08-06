package com.ifurutai.academico.domain.service; 
import java.util.List;
import com.ifurutai.academico.domain.model.Professor;

public interface ProfessorService {
	public abstract Professor inserirProfessor(Professor prof);
	public abstract Professor atualizarProfessor(Long profId, Professor prof);
	public abstract Professor excluirProfessor(Long profId);
	public abstract List<Professor> buscarProfessores();
	public abstract Professor buscarProfessorPorId(Long profId);
	public abstract Professor buscarProfessorPorEmail(String email);
	public abstract Boolean existeProfessorPorId(Long profId);
}
