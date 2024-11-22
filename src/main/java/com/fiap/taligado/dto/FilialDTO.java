package com.fiap.taligado.dto;

import lombok.Data;

@Data
public class FilialDTO {

    private Long idFilial;
    private String nome;
    private String tipo;
    private String cnpjFilial;
    private String areaOperacional;

    private Long enderecoId;
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
}

