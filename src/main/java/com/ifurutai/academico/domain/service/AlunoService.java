package com.ifurutai.academico.domain.service; 
import java.util.List;
import com.ifurutai.academico.domain.model.Aluno;

public interface AlunoService {
	public abstract Aluno inserirAluno(Aluno aluno);
	public abstract Aluno atualizarAluno(Long alunoId, Aluno aluno);
	public abstract Aluno excluirAluno(Long alunoId);
	public abstract List<Aluno> buscarAlunos();
	public abstract Aluno buscarAlunoPorId(Long alunoId);
	public abstract Aluno buscarAlunoPorEmail(String email);
	public abstract Aluno buscarAlunoPorCpf(String cpf);
	public abstract Boolean existeAlunoPorId(Long alunoId);
}
