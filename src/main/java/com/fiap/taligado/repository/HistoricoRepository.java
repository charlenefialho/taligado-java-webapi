package com.fiap.taligado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.taligado.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}