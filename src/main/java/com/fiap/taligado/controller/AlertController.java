package com.fiap.taligado.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.AlertaDetalhadaDTO;
import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.repository.EmpresaRepository;
import com.fiap.taligado.service.AlertaService;

@Controller
@RequestMapping("/alertas")
public class AlertController {

    @Autowired
    private AlertaService alertaService;
    
    @Autowired
    private EmpresaRepository empresaRepository;

    // Listar alertas com paginação
    private Long obterIdEmpresaDoUsuarioLogado(Principal principal) {
        String cnpj = principal.getName(); // Aqui, o username é o CNPJ
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para o CNPJ: " + cnpj));
        return empresa.getIdEmpresa();
    }

    @GetMapping
    public ModelAndView listarAlertas(@RequestParam(defaultValue = "0") int page, 
                                      @RequestParam(defaultValue = "5") int size, 
                                      Principal principal) {
        ModelAndView mv = new ModelAndView("alerta/list");

        // Obter ID da empresa logada
        Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal);

        // Buscar alertas filtrados pela empresa
        var alertas = alertaService.listarAlertasPorEmpresa(idEmpresa, PageRequest.of(page, size));
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
