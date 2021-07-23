package com.ifurutai.academico.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifurutai.academico.domain.model.Professor;

@RestController
public class ProfessorController {
	
	@GetMapping("/professor")
	public Professor listar() {
		var prof = new Professor(1L, "John Neves", "Mestre", "snow@gmail.com", "(64) 99293-9212");
		return prof;
	}
	
	@GetMapping("/professores")
	public List<Professor> listarAll() {
		var prof1 = new Professor(1L, "John Neves", "Mestre", "snow@gmail.com", "(64) 99293-9212");
		var prof2 = new Professor(1L, "John Neves", "Mestre", "snow@gmail.com", "(64) 99293-9212");
		return Arrays.asList(prof1, prof2);
	}
	
}

