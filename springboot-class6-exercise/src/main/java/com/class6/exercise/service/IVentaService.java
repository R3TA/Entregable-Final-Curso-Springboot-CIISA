package com.class6.exercise.service;

import com.class6.exercise.dto.VentaDTO;
import com.class6.exercise.model.Venta;

public interface IVentaService extends ICRUD<Venta> {
	Venta registrarTransactional(VentaDTO vdto);
}
