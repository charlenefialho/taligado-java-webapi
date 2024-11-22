package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.FilialDTO;
import com.fiap.taligado.service.FilialService;

import jakarta.validation.Valid;

@Controller
public class FilialController {

    @Autowired
    private FilialService filialService;

    // Página de listagem
    @GetMapping("/filiais")
    public ModelAndView listarFiliais() {
        ModelAndView mv = new ModelAndView("filial/list");
        List<FilialDTO> filiais = filialService.buscarTodas();
        mv.addObject("filiais", filiais);
        return mv;
    }

 // Página para criar uma nova filial
    @GetMapping("/filiais/nova")
    public ModelAndView novaFilial() {
        ModelAndView mv = new ModelAndView("filial/form");
        mv.addObject("filial", new FilialDTO());
        return mv;
    }

    // Página para editar uma filial existente
    @PostMapping("/filiais/editar/{id}")
    public ModelAndView editarFilial(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("filial/form");
        mv.addObject("filial", filialService.buscarPorId(id));
        return mv;
    }

    // Salvar ou atualizar filial
    @PostMapping("/filiais")
    public ModelAndView salvar(@Valid @ModelAttribute FilialDTO filialDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("filial/form");
        }
        filialService.salvarOuAtualizar(filialDTO);
        return new ModelAndView("redirect:/filiais");
    }

    // Excluir filial
    @PostMapping("/filiais/deletar/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/filiais");
        try {
            filialService.excluir(id);
        } catch (Exception e) {
            mv.setViewName("filial/list");
            mv.addObject("filiais", filialService.buscarTodas());
            mv.addObject("errorMessage", "Não foi possível excluir a filial. Verifique dependências antes de tentar novamente.");
        }
        return mv;
    }
}


