package com.fiap.taligado.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Long idSensor;

    private String tipo;

    private String descricao;

    private String unidade;

    @Column(name = "valor_atual")
    private Double valorAtual;

    @Column(name = "tempo_operacao")
    private Double tempoOperacao;

    @ManyToOne
    @JoinColumn(name = "dispositivo_iddispositivo", foreignKey = @ForeignKey(name = "fk_sensor_dispositivo"))
    private Dispositivo dispositivo;

    @ManyToMany
    @JoinTable(
        name = "historico_sensor",
        joinColumns = @JoinColumn(name = "sensor_idsensor", referencedColumnName = "id_sensor"),
        inverseJoinColumns = @JoinColumn(name = "historico_idhistorico", referencedColumnName = "id_historico"),
        foreignKey = @ForeignKey(name = "fk_historico_sensor_sensor"),
        inverseForeignKey = @ForeignKey(name = "fk_historico_sensor_historico")
    )
    private List<Historico> historicos = new ArrayList<>();
}

