package com.class6.exercise.repo;

import org.springframework.data.repository.CrudRepository;
import com.class6.exercise.model.Usuario;

public interface IUsuarioRepo extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
