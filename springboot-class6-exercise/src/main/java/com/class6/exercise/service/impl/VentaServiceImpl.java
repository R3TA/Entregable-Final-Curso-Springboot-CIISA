package com.class6.exercise.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.class6.exercise.dto.VentaDTO;
import com.class6.exercise.model.Venta;
import com.class6.exercise.repo.IVentaComidaRepo;
import com.class6.exercise.repo.IVentaRepo;
import com.class6.exercise.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {
	Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);
	
	@Autowired
	IVentaRepo ventaRepo;
	
	@Autowired
	IVentaComidaRepo ventaComidaRepo;
	
	@Override
	public Venta registrar(Venta obj) {
		obj.getDetalleVenta().forEach(det -> {
			det.setVenta(obj);
		});
		return ventaRepo.save(obj);
	}

	@Override
	public Venta modificar(Venta obj) {
		return ventaRepo.save(obj);
	}

	@Override
	public List<Venta> listar() {
		return ventaRepo.findAll();
	}

	@Override
	public Venta leer(Integer id) {
		Optional<Venta> op = ventaRepo.findById(id);
		return op.isPresent() ? op.get() : new Venta();
	}

	@Override
	public boolean eliminar(Integer id) {
		ventaRepo.deleteById(id);
		return true;
	}

	@Override
	public Venta registrarTransactional(VentaDTO dto) { 
		dto.getVenta().getDetalleVenta().forEach(det -> {
			det.setVenta(dto.getVenta());
		});
		
		ventaRepo.save(dto.getVenta());
		dto.getComida().forEach(ex -> ventaComidaRepo.registrar(ex.getIdComida(),dto.getVenta().getIdVenta())); // Se establece la relación entre venta y comida
		
		return dto.getVenta();
	}

}
