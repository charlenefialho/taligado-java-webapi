package com.fiap.taligado.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "regulacao_energia")
public class RegulacaoEnergia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_regulacao")
	private Long idRegulacao;

	@Column(name = "tarifa_kwh")
	private Double tarifaKwh;

	@Column(name = "nome_bandeira")
	private String nomeBandeira;

	@Column(name = "tarifa_adicional_bandeira")
	private Double tarifaAdicionalBandeira;

	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;
}
