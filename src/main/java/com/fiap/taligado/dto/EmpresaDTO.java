package com.fiap.taligado.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmpresaDTO {
	
	private Long idEmpresa;
	private String nome;
	private String email;
	private String cnpj;
	private String segmento;
	private Date dataFundacao;
	
}
