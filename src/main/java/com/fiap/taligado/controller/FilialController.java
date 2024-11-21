package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.FilialDTO;
import com.fiap.taligado.service.FilialService;

@Controller
public class FilialController {

    @Autowired
    private FilialService filialService;

    @GetMapping("/filiais")
    public ModelAndView listarFiliais() {
        ModelAndView mv = new ModelAndView("filial/list"); // Nome do arquivo HTML
        List<FilialDTO> filiais = filialService.buscarTodas(); // Busca filiais via serviço
        mv.addObject("filiais", filiais); // Adiciona a lista de DTOs ao modelo
        return mv;
    }
}
