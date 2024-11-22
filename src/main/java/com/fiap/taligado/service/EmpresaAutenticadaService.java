package com.fiap.taligado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.repository.EmpresaRepository;

@Service
public class EmpresaAutenticadaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa getEmpresaAutenticada() {
        String cnpj = SecurityContextHolder.getContext().getAuthentication().getName();
        return empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new IllegalStateException("Empresa logada não encontrada."));
    }
}

