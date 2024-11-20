package com.fiap.taligado.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ChatAssistantController {
	@Autowired
	private OpenAiChatClient chatClient;
	
	@GetMapping("form_chat_assistant")
	public String retornaFormChat() {
		return "form_chat_assistant";
	}
	
	@PostMapping("/enviar_pergunta_chat")
	public String enviarPerguntaChat(@RequestParam(name = "pergunta") String pergunta, 
			Model model) {
		String resposta = chatClient.call(pergunta);
		model.addAttribute("resposta", resposta );
		return "resposta_chat_assistant";
	}
}
