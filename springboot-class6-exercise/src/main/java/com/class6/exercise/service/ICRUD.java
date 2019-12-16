package com.class6.exercise.service;

import java.util.List;

public interface ICRUD<T> {
	T registrar(T obj);
	T modificar(T obj);
	List<T> listar();
	T leer(Integer id);
	boolean eliminar(Integer id);
}
