package com.fiap.taligado.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Long idEndereco;
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;

}
