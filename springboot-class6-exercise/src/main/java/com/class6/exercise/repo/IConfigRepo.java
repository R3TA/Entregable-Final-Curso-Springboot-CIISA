package com.class6.exercise.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.class6.exercise.model.Config;

public interface IConfigRepo extends JpaRepository <Config, Integer> {

	//@Query("SELECT c FROM Config c WHERE c.parametro = ?1")
	List<Config> findByParametro(String parametro);
}