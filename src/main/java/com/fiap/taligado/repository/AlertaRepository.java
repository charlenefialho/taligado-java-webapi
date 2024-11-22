package com.fiap.taligado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.taligado.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT MAX(a.idAlerta) FROM Alerta a")
    Optional<Long> findMaxIdAlerta();
    
    @Query("SELECT a FROM Alerta a WHERE a.sensor.dispositivo.filial.empresa.idEmpresa = :idEmpresa")
    List<Alerta> findByEmpresaId(@Param("idEmpresa") Long idEmpresa);

}
