package com.class6.exercise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.class6.exercise.model.VentaComida;

public interface IVentaComidaRepo extends JpaRepository <VentaComida, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO venta_comida(id_comida, id_venta) VALUES (:idComida, :idVenta)", nativeQuery = true)
	Integer registrar(@Param("idComida") Integer idComida, @Param("idVenta") Integer idVenta);
}
