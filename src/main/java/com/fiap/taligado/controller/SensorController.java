package com.fiap.taligado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.SensorDTO;
import com.fiap.taligado.service.SensorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/novo/{dispositivoId}")
    public ModelAndView novoSensor(@PathVariable Long dispositivoId) {
        ModelAndView mv = new ModelAndView("sensor/form");
        SensorDTO sensor = new SensorDTO();
        sensor.setDispositivoId(dispositivoId); 
        mv.addObject("sensor", sensor);
        return mv;
    }

    @PostMapping
    public ModelAndView salvar(@ModelAttribute @Valid SensorDTO sensorDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("sensor/form");
            mv.addObject("sensor", sensorDTO);
            return mv;
        }
        sensorService.salvarOuAtualizar(sensorDTO);
        return new ModelAndView("redirect:/dispositivos/" + sensorDTO.getDispositivoId() + "/sensores");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarSensor(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("sensor/form");
        SensorDTO sensorDTO = sensorService.buscarPorId(id);
        mv.addObject("sensor", sensorDTO);
        return mv;
    }

    @PostMapping("/deletar/{id}/{dispositivoId}")
    public ModelAndView excluir(@PathVariable Long id, @PathVariable Long dispositivoId) {
        sensorService.excluir(id);
        return new ModelAndView("redirect:/dispositivos/" + dispositivoId + "/sensores");
    }
}
