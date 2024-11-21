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

    public FilialDTO buscarPorId(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));
        return converterParaDTO(filial);
    }

    public void salvarOuAtualizar(FilialDTO filialDTO) {
        Filial filial;

        if (filialDTO.getIdFilial() != null) {
            filial = filialRepository.findById(filialDTO.getIdFilial())
                    .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada para atualização"));
        } else {
            filial = new Filial();
        }

        filial.setNome(filialDTO.getNome());
        filial.setTipo(filialDTO.getTipo());
        filial.setCnpjFilial(filialDTO.getCnpjFilial());
        filial.setAreaOperacional(filialDTO.getAreaOperacional());

        filialRepository.save(filial);
    }

    public void excluir(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada para exclusão"));

        filialRepository.delete(filial);
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
