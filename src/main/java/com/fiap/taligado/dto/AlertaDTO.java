package com.fiap.taligado.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AlertaDTO {

    private Long idAlerta;
    private String descricao;
    private String severidade;
    private Date dataAlerta;
    private Long sensorId;

}
