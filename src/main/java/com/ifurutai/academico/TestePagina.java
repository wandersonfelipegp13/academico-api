package com.ifurutai.academico;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestePagina {
	
	@RequestMapping("/")
	String home() {
		return "Hello world!!!";
	}
	
}
