package com.fiap.taligado.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long idHistorico;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "valor_consumo_kwh")
    private Double valorConsumoKwh;

    @Column(name = "intensidade_carbono")
    private Double intensidadeCarbono;

    @Column(name = "custo_energia_estimado")
    private Double custoEnergiaEstimado;

    @ManyToOne
    @JoinColumn(name = "regulacao_energia_idregulacao", foreignKey = @ForeignKey(name = "fk_historico_regulacao_energia"))
    private RegulacaoEnergia regulacaoEnergia;

    @ManyToMany(mappedBy = "historicos")
    private List<Sensor> sensores = new ArrayList<>();
}
