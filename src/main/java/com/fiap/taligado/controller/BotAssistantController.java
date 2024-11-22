package com.fiap.taligado.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

@Controller
public class BotAssistantController {
	
	@Autowired
	private OpenAiChatClient chatClient;
	
	@GetMapping("form_bot_assistant")
	public String retornaFormChatGPT() {
		return "form_bot_assistant";
	}
	
	
	@PostMapping("/enviar_chat")
	public String enviarPerguntaChatGPT(@RequestParam(name = "pergunta") String pergunta, Model model) {
	    try {
	        String resposta = chatClient.call(pergunta);

	        // Formatar resposta para HTML
	        String respostaFormatada = resposta.replace("\n", "<br>");
	        model.addAttribute("resposta", respostaFormatada);
	        return "resposta_bot";

	    } catch (RestClientException e) {
	        e.printStackTrace();
	        model.addAttribute("resposta", "Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.");
	        return "resposta_bot";
	    }
	}

}
