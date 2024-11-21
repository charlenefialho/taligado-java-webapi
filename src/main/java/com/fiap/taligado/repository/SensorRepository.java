package com.fiap.taligado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.taligado.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
