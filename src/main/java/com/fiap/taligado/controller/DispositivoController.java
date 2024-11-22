package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.DispositivoDTO;
import com.fiap.taligado.service.DispositivoService;
import com.fiap.taligado.service.FilialService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;
    @Autowired
    private FilialService filialService;

    // Página de listagem
    @GetMapping
    public ModelAndView listarDispositivos() {
        ModelAndView mv = new ModelAndView("dispositivo/list");
        List<DispositivoDTO> dispositivos = dispositivoService.buscarTodos();
        mv.addObject("dispositivos", dispositivos);
        return mv;
    }

    // Página para criar um novo dispositivo
    @GetMapping("/novo")
    public ModelAndView novoDispositivo() {
        ModelAndView mv = new ModelAndView("dispositivo/form");
        mv.addObject("dispositivo", new DispositivoDTO());
        mv.addObject("filiais", filialService.buscarTodas());
        return mv;
    }

    // Página para editar um dispositivo existente
    @PostMapping("/editar/{id}")
    public ModelAndView editarDispositivo(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("dispositivo/form");
        mv.addObject("dispositivo", dispositivoService.buscarPorId(id));
        mv.addObject("filiais", filialService.buscarTodas());
        return mv;
    }

    // Salvar ou atualizar dispositivo
    @PostMapping
    public ModelAndView salvar(@Valid @ModelAttribute DispositivoDTO dispositivoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("dispositivo/form");
        }
        dispositivoService.salvarOuAtualizar(dispositivoDTO);
        return new ModelAndView("redirect:/dispositivos");
    }

    // Excluir dispositivo
    @PostMapping("/deletar/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/dispositivos");
        try {
            dispositivoService.excluir(id);
        } catch (Exception e) {
            mv.setViewName("dispositivo/list");
            mv.addObject("dispositivos", dispositivoService.buscarTodos());
            mv.addObject("errorMessage", "Não foi possível excluir o dispositivo. Verifique dependências antes de tentar novamente.");
        }
        return mv;
    }
}
