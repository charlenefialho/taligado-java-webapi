package com.fiap.taligado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.repository.EmpresaRepository;

@Service
public class EmpresaDetailsService implements UserDetailsService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UsernameNotFoundException("Empresa não encontrada com o CNPJ: " + cnpj));

        return User.builder()
                .username(empresa.getCnpj())
                .password(empresa.getSenha())
                .roles("EMPRESA")
                .build();
    }

}