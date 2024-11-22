package com.fiap.taligado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.taligado.model.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    // Busca dispositivos de todas as filiais de uma empresa
    @Query("SELECT d FROM Dispositivo d WHERE d.filial.empresa.idEmpresa = :idEmpresa")
    List<Dispositivo> findByEmpresaId(@Param("idEmpresa") Long idEmpresa);
}

