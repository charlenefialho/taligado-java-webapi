package com.fiap.taligado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.taligado.model.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {

	@Query("SELECT f FROM Filial f LEFT JOIN FETCH f.endereco WHERE f.empresa.idEmpresa = :idEmpresa")
	List<Filial> findByEmpresaId(@Param("idEmpresa") Long idEmpresa);

}
