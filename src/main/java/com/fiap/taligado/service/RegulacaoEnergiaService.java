package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.RegulacaoEnergiaDTO;
import com.fiap.taligado.model.RegulacaoEnergia;
import com.fiap.taligado.repository.RegulacaoEnergiaRepository;

@Service
public class RegulacaoEnergiaService {

    @Autowired
    private RegulacaoEnergiaRepository regulacaoEnergiaRepository;

    public List<RegulacaoEnergiaDTO> buscarTodas() {
        List<RegulacaoEnergia> regulacoes = regulacaoEnergiaRepository.findAll();
        return regulacoes.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private RegulacaoEnergiaDTO converterParaDTO(RegulacaoEnergia regulacao) {
        RegulacaoEnergiaDTO dto = new RegulacaoEnergiaDTO();
        dto.setIdRegulacao(regulacao.getIdRegulacao());
        dto.setTarifaKwh(regulacao.getTarifaKwh());
        dto.setNomeBandeira(regulacao.getNomeBandeira());
        dto.setTarifaAdicionalBandeira(regulacao.getTarifaAdicionalBandeira());
        dto.setDataAtualizacao(regulacao.getDataAtualizacao());
        return dto;
    }
}

