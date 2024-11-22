package com.fiap.taligado.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.taligado.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT MAX(a.idAlerta) FROM Alerta a")
    Optional<Long> findMaxIdAlerta();

    @Query("""
            SELECT a FROM Alerta a 
            JOIN a.sensor s 
            JOIN s.dispositivo d 
            JOIN d.filial f 
            WHERE f.empresa.idEmpresa = :idEmpresa
            """)
    Page<Alerta> findByEmpresaId(@Param("idEmpresa") Long idEmpresa, Pageable pageable);
}
