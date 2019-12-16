package com.class6.exercise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.class6.exercise.model.Pelicula;

public interface IPeliculaRepo extends JpaRepository<Pelicula, Integer>{

}
