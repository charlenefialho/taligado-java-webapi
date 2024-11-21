package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.HistoricoDTO;
import com.fiap.taligado.service.HistoricoService;

@Controller
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;


    @GetMapping("/historico")
    public ModelAndView listarHistoricos() {
        ModelAndView mv = new ModelAndView("historico/list"); // Nome do arquivo .html
        List<HistoricoDTO> historicos = historicoService.buscarTodos(); // Busca os históricos via serviço
        mv.addObject("historicos", historicos); // Adiciona a lista de DTOs no ModelAndView
        return mv; // Retorna o ModelAndView para renderização
    }
}
