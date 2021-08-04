package com.ifurutai.academico.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifurutai.academico.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	// Busca por nome que contém uma parte
	List<Professor> findByNomeContaining(String nome);
	
	// Busca por nome
	List<Professor> findByNome(String nome);
	
	// Busca um cliente por email
	Professor findByEmail(String email);
}
