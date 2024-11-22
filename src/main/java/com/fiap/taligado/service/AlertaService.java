package com.fiap.taligado.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.AlertaDTO;
import com.fiap.taligado.dto.AlertaDetalhadaDTO;
import com.fiap.taligado.model.Alerta;
import com.fiap.taligado.model.Dispositivo;
import com.fiap.taligado.model.Filial;
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

        // Salva o alerta no banco de dados
        return alertaRepository.save(alerta);
    }
    
    // Busca todos os alertas com paginação
    public Page<AlertaDTO> buscarTodos(Pageable pageable) {
        return alertaRepository.findAll(pageable)
                .map(this::converterParaDTO);
    }
    
    public Page<AlertaDTO> listarAlertasPorEmpresa(Long idEmpresa, Pageable pageable) {
        return alertaRepository.findByEmpresaId(idEmpresa, pageable)
                .map(this::converterParaDTO);
    }


    public AlertaDetalhadaDTO buscarDetalhesPorId(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alerta não encontrado"));

        Sensor sensor = alerta.getSensor();
        Dispositivo dispositivo = sensor.getDispositivo();
        Filial filial = dispositivo.getFilial();

        AlertaDetalhadaDTO dto = new AlertaDetalhadaDTO();
        dto.setIdAlerta(alerta.getIdAlerta());
        dto.setDescricao(alerta.getDescricao());
        dto.setSeveridade(alerta.getSeveridade());
        dto.setDataAlerta(alerta.getDataAlerta());

        dto.setSensorId(sensor.getIdSensor());
        dto.setSensorTipo(sensor.getTipo());
        dto.setSensorDescricao(sensor.getDescricao());

        dto.setDispositivoId(dispositivo.getIdDispositivo());
        dto.setDispositivoNome(dispositivo.getNome());
        dto.setDispositivoTipo(dispositivo.getTipo());
        dto.setDispositivoStatus(dispositivo.getStatus());

        dto.setFilialId(filial.getIdFilial());
        dto.setFilialNome(filial.getNome());
        dto.setFilialTipo(filial.getTipo());
        dto.setFilialCnpj(filial.getCnpjFilial());
        dto.setFilialAreaOperacional(filial.getAreaOperacional());

        return dto;
    }


    private AlertaDTO converterParaDTO(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO();
        dto.setIdAlerta(alerta.getIdAlerta());
        dto.setDescricao(alerta.getDescricao());
        dto.setSeveridade(alerta.getSeveridade());
        dto.setDataAlerta(alerta.getDataAlerta());
        dto.setSensorId(alerta.getSensor().getIdSensor());
        return dto;
    }
}
