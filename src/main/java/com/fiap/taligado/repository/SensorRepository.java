package com.fiap.taligado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.taligado.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByDispositivo_IdDispositivo(Long dispositivoId);
}

