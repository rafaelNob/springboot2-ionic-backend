package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	
	@Query("Select c FROM Cidade c where c.estado.id = :estadoId order by c.nome")
	List<Cidade> listCidadePeloEstado(@Param("estadoId") Integer estadoId);

}
