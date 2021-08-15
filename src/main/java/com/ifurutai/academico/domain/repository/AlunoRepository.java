package com.ifurutai.academico.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifurutai.academico.domain.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByNomeContaining(String nome);
	List<Aluno> findByNome(String nome);
	Aluno findByEmail(String email);
	Aluno findByCpf(String cpf);

}
