package com.class6.exercise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.class6.exercise.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer>{

}
