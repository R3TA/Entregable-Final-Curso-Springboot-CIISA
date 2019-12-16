package com.class6.exercise.dto;

import java.util.List;
import com.class6.exercise.model.Comida;
import com.class6.exercise.model.Venta;

public class VentaDTO {

	private Venta venta;
	private List<Comida> comida;

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<Comida> getComida() {
		return comida;
	}

	public void setComida(List<Comida> comida) {
		this.comida = comida;
	}
}
