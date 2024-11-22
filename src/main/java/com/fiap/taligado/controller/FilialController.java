package com.fiap.taligado.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.taligado.dto.FilialDTO;
import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.model.Filial;
import com.fiap.taligado.repository.EmpresaRepository;
import com.fiap.taligado.service.FilialService;

import jakarta.validation.Valid;

@Controller
public class FilialController {

    @Autowired
    private FilialService filialService;
    @Autowired
    private EmpresaRepository empresaRepository;

    // Página de listagem
    @GetMapping("/filiais")
    public String listarFiliais(Model model, Principal principal) {
        // Obtém o ID da empresa a partir do usuário logado
        Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal);

        if (idEmpresa == null) {
            throw new IllegalStateException("Não foi possível determinar o ID da empresa do usuário logado.");
        }

        List<Filial> filiais = filialService.listarFiliaisPorEmpresa(idEmpresa);
        model.addAttribute("filiais", filiais);

        // Retorna o nome da página de listagem de filiais
        return "filial/list";
    }
    
    private Long obterIdEmpresaDoUsuarioLogado(Principal principal) {
        // Exemplo: Obter empresa associada ao CNPJ do usuário logado
        String cnpj = principal.getName(); // Aqui, o username é o CNPJ
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para o CNPJ: " + cnpj));
        return empresa.getIdEmpresa();
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

    @PostMapping("/filiais")
    public ModelAndView salvar(@Valid @ModelAttribute FilialDTO filialDTO, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ModelAndView("filial/form");
        }

        Long idEmpresa = obterIdEmpresaDoUsuarioLogado(principal);
        filialService.salvarOuAtualizar(filialDTO, idEmpresa);

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


