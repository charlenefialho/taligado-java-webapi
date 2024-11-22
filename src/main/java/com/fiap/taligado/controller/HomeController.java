package com.fiap.taligado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.service.EmpresaAutenticadaService;


@SessionAttributes("empresaLogada")
@Controller
public class HomeController {

    @Autowired
    private EmpresaAutenticadaService empresaAutenticadaService;

    @GetMapping("/initial_page")
    public ModelAndView carregarPaginaInicial(Model model) {
        Empresa empresa = empresaAutenticadaService.getEmpresaAutenticada();
        model.addAttribute("empresaLogada", empresa);

        ModelAndView mv = new ModelAndView("initial_page");
        return mv;
    }
}
