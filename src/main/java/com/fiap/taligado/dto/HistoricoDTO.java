package com.fiap.taligado.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HistoricoDTO {
	private Long idHistorico;
    private Date dataCriacao;
    private Double valorConsumoKwh;
    private Double intensidadeCarbono;
    private Double custoEnergiaEstimado;


}