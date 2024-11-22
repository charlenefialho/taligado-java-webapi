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
@Table(name = "dispositivo")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disposito")
    private Long idDispositivo;

    private String nome;

    private String tipo;

    private String status;

    @Column(name = "data_instalacao")
    private String dataInstalacao;

    @Column(name = "potencia_nominal")
    private Double potenciaNominal;

    @ManyToOne
    @JoinColumn(name = "filial_idfilial", foreignKey = @ForeignKey(name = "fk_dispositivo_filial"))
    private Filial filial;
    

    @ManyToMany
    @JoinTable(
        name = "dispositivo_sensor",
        joinColumns = @JoinColumn(name = "dispositivo_iddispositivo", referencedColumnName = "id_disposito"),
        inverseJoinColumns = @JoinColumn(name = "sensor_idsensor", referencedColumnName = "id_sensor"),
        foreignKey = @ForeignKey(name = "fk_dispositivo_sensor_dispositivo"),
        inverseForeignKey = @ForeignKey(name = "fk_dispositivo_sensor_sensor")
    )
    private List<Sensor> sensores = new ArrayList<>();
}

