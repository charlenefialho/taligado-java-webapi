package com.fiap.taligado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.taligado.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
