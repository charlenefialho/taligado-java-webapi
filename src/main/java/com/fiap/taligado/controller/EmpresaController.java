package com.fiap.taligado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.repository.EmpresaRepository;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "register_enterprise";
    }

    @PostMapping("/cadastro")
    public String cadastrarEmpresa(@ModelAttribute Empresa empresa, RedirectAttributes attributes) {
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha())); // Criptografa a senha
        empresaRepository.save(empresa);
        attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso! Faça login para acessar.");
        return "redirect:/login";
    }
}

