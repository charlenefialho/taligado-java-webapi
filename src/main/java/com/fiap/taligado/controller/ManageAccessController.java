package com.fiap.taligado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageAccessController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/acesso_negado")
	public String bloquearAcesso() {
		return "acesso_negado";
	}
}
