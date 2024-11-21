package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.RegulacaoEnergiaDTO;
import com.fiap.taligado.service.RegulacaoEnergiaService;

@Controller
public class RegulacaoEnergiaController {

    @Autowired
    private RegulacaoEnergiaService regulacaoEnergiaService;

    @GetMapping("/regulacoes")
    public ModelAndView listarRegulacoes() {
        ModelAndView mv = new ModelAndView("regulacao/list");
        List<RegulacaoEnergiaDTO> regulacoes = regulacaoEnergiaService.buscarTodas();
        mv.addObject("regulacoes", regulacoes);
        return mv;
    }
}
