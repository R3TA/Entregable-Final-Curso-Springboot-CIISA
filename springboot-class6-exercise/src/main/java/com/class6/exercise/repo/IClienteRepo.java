package com.class6.exercise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.class6.exercise.model.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer>{

}
