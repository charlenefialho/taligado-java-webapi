package com.fiap.taligado.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.AlertaDTO;
import com.fiap.taligado.model.Alerta;
import com.fiap.taligado.model.Sensor;
import com.fiap.taligado.repository.AlertaRepository;
import com.fiap.taligado.repository.SensorRepository;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final SensorRepository sensorRepository;

    public AlertaService(AlertaRepository alertaRepository, SensorRepository sensorRepository) {
        this.alertaRepository = alertaRepository;
        this.sensorRepository = sensorRepository;
    }

    public Alerta criarAlerta(AlertaDTO alertaDTO) {
        Alerta alerta = new Alerta();

        // Popula os campos básicos do alerta
        alerta.setDescricao(alertaDTO.getDescricao());
        alerta.setSeveridade(alertaDTO.getSeveridade());
        alerta.setDataAlerta(alertaDTO.getDataAlerta() != null ? alertaDTO.getDataAlerta() : new Date());

        // Obtém o sensor associado
        Sensor sensor = sensorRepository.findById(alertaDTO.getSensorId())
                .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrado"));
        alerta.setSensor(sensor);

        // Busca o último ID do alerta registrado e incrementa
        Long ultimoId = alertaRepository.findMaxIdAlerta().orElse(0L); // Caso não exista, começa do 0
        alerta.setIdAlerta(ultimoId + 1);

        // Salva o alerta no banco de dados
        return alertaRepository.save(alerta);
    }
}


