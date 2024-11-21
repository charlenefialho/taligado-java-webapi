package com.fiap.taligado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "filial")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filial")
    private Long idFilial;
    
    private String nome;

    private String tipo;

    @Column(name = "cnpj_filial")
    private String cnpjFilial;

    @Column(name = "area_operacional")
    private String areaOperacional;

    @ManyToOne
    @JoinColumn(name = "empresa_idempresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "endereco_idendereco")
    private Endereco endereco;
}
