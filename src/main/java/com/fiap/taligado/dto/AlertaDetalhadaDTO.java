package com.fiap.taligado.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AlertaDetalhadaDTO {

    private Long idAlerta;
    private String descricao;
    private String severidade;
    private Date dataAlerta;

    // Informações do Sensor
    private Long sensorId;
    private String sensorTipo;
    private String sensorDescricao;

    // Informações do Dispositivo
    private Long dispositivoId;
    private String dispositivoNome;
    private String dispositivoTipo;
    private String dispositivoStatus;

    // Informações da Filial
    private Long filialId;
    private String filialNome;
    private String filialTipo;
    private String filialCnpj;
    private String filialAreaOperacional;
}

