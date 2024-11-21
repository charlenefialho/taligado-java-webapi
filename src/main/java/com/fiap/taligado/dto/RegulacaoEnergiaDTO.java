package com.fiap.taligado.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RegulacaoEnergiaDTO {

    private Long idRegulacao;
    private Double tarifaKwh;
    private String nomeBandeira;
    private Double tarifaAdicionalBandeira;
    private Date dataAtualizacao;

}
