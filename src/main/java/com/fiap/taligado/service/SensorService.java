package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.SensorDTO;
import com.fiap.taligado.model.Dispositivo;
import com.fiap.taligado.model.Sensor;
import com.fiap.taligado.repository.DispositivoRepository;
import com.fiap.taligado.repository.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public List<SensorDTO> buscarTodos() {
        List<Sensor> sensores = sensorRepository.findAll();
        return sensores.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public SensorDTO buscarPorId(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrado"));
        return converterParaDTO(sensor);
    }
    
    public List<SensorDTO> buscarPorDispositivo(Long dispositivoId) {
        List<Sensor> sensores = sensorRepository.findByDispositivo_IdDispositivo(dispositivoId);
        return sensores.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void salvarOuAtualizar(SensorDTO sensorDTO) {
        Sensor sensor = sensorDTO.getIdSensor() != null
                ? sensorRepository.findById(sensorDTO.getIdSensor())
                    .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrado"))
                : new Sensor();

        sensor.setTipo(sensorDTO.getTipo());
        sensor.setDescricao(sensorDTO.getDescricao());
        sensor.setUnidade(sensorDTO.getUnidade());
        sensor.setValorAtual(sensorDTO.getValorAtual());
        sensor.setTempoOperacao(sensorDTO.getTempoOperacao());

        Dispositivo dispositivo = dispositivoRepository.findById(sensorDTO.getDispositivoId())
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado"));
        sensor.setDispositivo(dispositivo);

        sensorRepository.save(sensor);
    }

    public void excluir(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrado para exclusão"));
        sensorRepository.delete(sensor);
    }

    private SensorDTO converterParaDTO(Sensor sensor) {
        SensorDTO dto = new SensorDTO();
        dto.setIdSensor(sensor.getIdSensor());
        dto.setTipo(sensor.getTipo());
        dto.setDescricao(sensor.getDescricao());
        dto.setUnidade(sensor.getUnidade());
        dto.setValorAtual(sensor.getValorAtual());
        dto.setTempoOperacao(sensor.getTempoOperacao());
        dto.setDispositivoId(sensor.getDispositivo().getIdDispositivo());
        return dto;
    }
}

