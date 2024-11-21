package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.DispositivoDTO;
import com.fiap.taligado.service.DispositivoService;

@Controller
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivos")
    public ModelAndView listarDispositivos() {
        ModelAndView mv = new ModelAndView("dispositivo/list");
        List<DispositivoDTO> dispositivos = dispositivoService.buscarTodos();
        mv.addObject("dispositivos", dispositivos);
        return mv;
    }
}