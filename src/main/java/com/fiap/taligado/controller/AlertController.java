package com.fiap.taligado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.AlertaDTO;
import com.fiap.taligado.dto.AlertaDetalhadaDTO;
import com.fiap.taligado.service.AlertaService;

@Controller
@RequestMapping("/alertas")
public class AlertController {

    @Autowired
    private AlertaService alertaService;

    // Listar alertas com paginação
    @GetMapping
    public ModelAndView listarAlertas(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        ModelAndView mv = new ModelAndView("alerta/list");
        var alertas = alertaService.buscarTodos(PageRequest.of(page, size));
        mv.addObject("alertas", alertas);
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", alertas.getTotalPages());
        return mv;
    }

    // Exibir detalhes de um alerta
    @GetMapping("/{id}")
    public ModelAndView detalhesAlerta(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("alerta/details");
        AlertaDetalhadaDTO alertaDetalhado = alertaService.buscarDetalhesPorId(id);
        mv.addObject("alerta", alertaDetalhado);
        return mv;
    }
}
