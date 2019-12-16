package com.class6.exercise.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.class6.exercise.model.Pelicula;
import com.class6.exercise.repo.IPeliculaRepo;
import com.class6.exercise.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements IPeliculaService {
	
	@Autowired
	IPeliculaRepo repo;
	
	@Override
	public Pelicula registrar(Pelicula obj) {
		return repo.save(obj);
	}

	@Override
	public Pelicula modificar(Pelicula obj) {
		return repo.save(obj);
	}

	@Override
	public List<Pelicula> listar() {
		return repo.findAll();
	}

	@Override
	public Pelicula leer(Integer id) {
		Optional<Pelicula> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Pelicula();
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
