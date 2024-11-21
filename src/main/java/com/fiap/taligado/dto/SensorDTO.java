package com.fiap.taligado.dto;

import lombok.Data;

@Data
public class SensorDTO {

    private Long idSensor;
    private String tipo;
    private String descricao;
    private String unidade;
    private Double valorAtual;
    private Double tempoOperacao;
    private Long dispositivoId;

}
