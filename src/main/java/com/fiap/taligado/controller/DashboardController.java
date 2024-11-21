package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fiap.taligado.dto.HistoricoDTO;
import com.fiap.taligado.service.HistoricoService;

@Controller
public class DashboardController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        // Busca os dados de consumo e emissões
        List<HistoricoDTO> historicos = historicoService.buscarTodos();

        // Adiciona a lista de históricos ao modelo
        model.addAttribute("historicos", historicos);

        return "dashboard"; // Nome do arquivo HTML do dashboard
    }
}
