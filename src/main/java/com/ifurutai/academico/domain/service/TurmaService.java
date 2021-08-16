package com.ifurutai.academico.domain.service; 
import java.util.List;
import com.ifurutai.academico.domain.model.Turma;

public interface TurmaService {
	public abstract Turma inserirTurma(Turma turma);
	public abstract Turma atualizarTurma(Long turmaId, Turma turma);
	public abstract Turma excluirTurma(Long turmaId);
	public abstract List<Turma> buscarTurmas();
	public abstract Turma buscarTurmaPorId(Long TurmaId);
	public abstract Boolean existeTurmaPorId(Long TurmaId);
}
