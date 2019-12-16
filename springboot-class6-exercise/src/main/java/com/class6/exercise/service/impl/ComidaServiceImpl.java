package com.class6.exercise.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.class6.exercise.model.Comida;
import com.class6.exercise.repo.IComidaRepo;
import com.class6.exercise.service.IComidaService;

@Service
public class ComidaServiceImpl implements IComidaService {

	@Autowired
	IComidaRepo repo;
	
	@Override
	public Comida registrar(Comida obj) {
		return repo.save(obj);
	}

	@Override
	public Comida modificar(Comida obj) {
		return repo.save(obj);
	}

	@Override
	public List<Comida> listar() {
		return repo.findAll();
	}

	@Override
	public Comida leer(Integer id) {
		Optional<Comida> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Comida();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
