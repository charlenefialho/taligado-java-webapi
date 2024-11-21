package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.DispositivoDTO;
import com.fiap.taligado.model.Dispositivo;
import com.fiap.taligado.repository.DispositivoRepository;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public List<DispositivoDTO> buscarTodos() {
        List<Dispositivo> dispositivos = dispositivoRepository.findAll();
        return dispositivos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private DispositivoDTO converterParaDTO(Dispositivo dispositivo) {
        DispositivoDTO dto = new DispositivoDTO();
        dto.setIdDispositivo(dispositivo.getIdDispositivo());
        dto.setNome(dispositivo.getNome());
        dto.setTipo(dispositivo.getTipo());
        dto.setStatus(dispositivo.getStatus());
        dto.setFilialId(dispositivo.getFilial().getIdFilial());
        return dto;
    }
}

