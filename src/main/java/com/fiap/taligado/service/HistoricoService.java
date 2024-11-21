package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.HistoricoDTO;
import com.fiap.taligado.model.Historico;
import com.fiap.taligado.repository.HistoricoRepository;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<HistoricoDTO> buscarTodos() {
        List<Historico> historicos = historicoRepository.findAll();
        return historicos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private HistoricoDTO converterParaDTO(Historico historico) {
        HistoricoDTO dto = new HistoricoDTO();
        dto.setIdHistorico(historico.getIdHistorico());
        dto.setDataCriacao(historico.getDataCriacao());
        dto.setValorConsumoKwh(historico.getValorConsumoKwh());
        dto.setIntensidadeCarbono(historico.getIntensidadeCarbono());
        dto.setCustoEnergiaEstimado(historico.getCustoEnergiaEstimado());
        return dto;
    }
}
