package com.ifurutai.academico.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifurutai.academico.domain.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
}
