package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.FilialDTO;
import com.fiap.taligado.model.Filial;
import com.fiap.taligado.repository.FilialRepository;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    public List<FilialDTO> buscarTodas() {
        List<Filial> filiais = filialRepository.findAll();
        return filiais.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private FilialDTO converterParaDTO(Filial filial) {
        FilialDTO dto = new FilialDTO();
        dto.setIdFilial(filial.getIdFilial());
        dto.setNome(filial.getNome());
        dto.setTipo(filial.getTipo());
        dto.setCnpjFilial(filial.getCnpjFilial());
        dto.setAreaOperacional(filial.getAreaOperacional());
        return dto;
    }
}

