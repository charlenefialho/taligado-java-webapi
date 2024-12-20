package com.fiap.taligado.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.taligado.dto.DispositivoDTO;
import com.fiap.taligado.dto.SensorDTO;
import com.fiap.taligado.model.Dispositivo;
import com.fiap.taligado.model.Empresa;
import com.fiap.taligado.model.Filial;
import com.fiap.taligado.repository.DispositivoRepository;
import com.fiap.taligado.repository.FilialRepository;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private FilialRepository filialRepository; // Injetar o repositório de Filial
    @Autowired
    private EmpresaAutenticadaService empresaAutenticadaService;

    public List<DispositivoDTO> buscarTodos() {
        List<Dispositivo> dispositivos = dispositivoRepository.findAll();
        return dispositivos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public DispositivoDTO buscarPorId(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado"));
        return converterParaDTO(dispositivo);
    }
    
    public List<Dispositivo> buscarDispositivosPorEmpresa(Long idEmpresa) {
        return dispositivoRepository.findByEmpresaId(idEmpresa);
    }

    public void salvarOuAtualizar(DispositivoDTO dispositivoDTO) {
        Dispositivo dispositivo;

        if (dispositivoDTO.getIdDispositivo() != null) {
            dispositivo = dispositivoRepository.findById(dispositivoDTO.getIdDispositivo())
                    .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado para atualização"));
        } else {
            dispositivo = new Dispositivo();
        }

        dispositivo.setNome(dispositivoDTO.getNome());
        dispositivo.setTipo(dispositivoDTO.getTipo());
        dispositivo.setStatus(dispositivoDTO.getStatus());
        dispositivo.setPotenciaNominal(dispositivoDTO.getPotenciaNominal());
        dispositivo.setDataInstalacao(dispositivoDTO.getDataInstalacao());

        // Buscar a filial associada
        Filial filial = filialRepository.findById(dispositivoDTO.getFilialId())
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));
        dispositivo.setFilial(filial);

        dispositivoRepository.save(dispositivo);
    }

    public void excluir(Long id) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado para exclusão"));
        dispositivoRepository.delete(dispositivo);
    }

    private DispositivoDTO converterParaDTO(Dispositivo dispositivo) {
        DispositivoDTO dto = new DispositivoDTO();
        dto.setIdDispositivo(dispositivo.getIdDispositivo());
        dto.setNome(dispositivo.getNome());
        dto.setTipo(dispositivo.getTipo());
        dto.setStatus(dispositivo.getStatus());
        dto.setDataInstalacao(dispositivo.getDataInstalacao());
        dto.setPotenciaNominal(dispositivo.getPotenciaNominal());
        dto.setFilialId(dispositivo.getFilial().getIdFilial());
        dto.setFilialNome(dispositivo.getFilial().getNome());
        
        // Adiciona os sensores associados
        List<SensorDTO> sensores = dispositivo.getSensores().stream()
                .map(sensor -> {
                    SensorDTO sensorDTO = new SensorDTO();
                    sensorDTO.setIdSensor(sensor.getIdSensor());
                    sensorDTO.setTipo(sensor.getTipo());
                    sensorDTO.setDescricao(sensor.getDescricao());
                    sensorDTO.setUnidade(sensor.getUnidade());
                    sensorDTO.setValorAtual(sensor.getValorAtual());
                    sensorDTO.setTempoOperacao(sensor.getTempoOperacao());
                    sensorDTO.setDispositivoId(sensor.getDispositivo().getIdDispositivo());
                    return sensorDTO;
                }).collect(Collectors.toList());
        dto.setSensores(sensores);

        return dto;
    }
}
