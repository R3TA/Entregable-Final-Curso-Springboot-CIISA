package com.class6.exercise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.class6.exercise.model.Comida;

public interface IComidaRepo extends JpaRepository<Comida, Integer>{

}
