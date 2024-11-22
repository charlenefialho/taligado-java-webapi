package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.FilialDTO;
import com.fiap.taligado.model.Endereco;
import com.fiap.taligado.model.Filial;
import com.fiap.taligado.repository.EnderecoRepository;
import com.fiap.taligado.repository.FilialRepository;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

        // Atualizar ou criar endereço
        Endereco endereco = filialDTO.getEnderecoId() != null
                ? enderecoRepository.findById(filialDTO.getEnderecoId())
                        .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado"))
                : new Endereco();

        endereco.setLogradouro(filialDTO.getLogradouro());
        endereco.setCidade(filialDTO.getCidade());
        endereco.setEstado(filialDTO.getEstado());
        endereco.setCep(filialDTO.getCep());
        endereco.setPais(filialDTO.getPais());

        filial.setEndereco(enderecoRepository.save(endereco));

        // Atualizar dados da filial
        filial.setNome(filialDTO.getNome());
        filial.setTipo(filialDTO.getTipo());
        filial.setCnpjFilial(filialDTO.getCnpjFilial());
        filial.setAreaOperacional(filialDTO.getAreaOperacional());

        filialRepository.save(filial);
    }

    public void excluir(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada para exclusão"));
        
        Endereco endereco = filial.getEndereco();
        if (endereco != null) {
            filial.setEndereco(null); // Remove a relação antes de excluir
            filialRepository.save(filial);
            enderecoRepository.delete(endereco);
        }

        filialRepository.delete(filial);
    }

    private FilialDTO converterParaDTO(Filial filial) {
        FilialDTO dto = new FilialDTO();
        dto.setIdFilial(filial.getIdFilial());
        dto.setNome(filial.getNome());
        dto.setTipo(filial.getTipo());
        dto.setCnpjFilial(filial.getCnpjFilial());
        dto.setAreaOperacional(filial.getAreaOperacional());

        if (filial.getEndereco() != null) {
            dto.setEnderecoId(filial.getEndereco().getIdEndereco());
            dto.setLogradouro(filial.getEndereco().getLogradouro());
            dto.setCidade(filial.getEndereco().getCidade());
            dto.setEstado(filial.getEndereco().getEstado());
            dto.setCep(filial.getEndereco().getCep());
            dto.setPais(filial.getEndereco().getPais());
        }
        return dto;
    }
}
