package com.fiap.taligado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.taligado.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
