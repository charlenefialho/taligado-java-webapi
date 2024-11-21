package com.fiap.taligado.dto;

import lombok.Data;

@Data
public class DispositivoDTO {

    private Long idDispositivo;
    private String nome;
    private String tipo;
    private String status;
    private String dataInstalacao;
    private Double potenciaNominal;
    private Long filialId;

}