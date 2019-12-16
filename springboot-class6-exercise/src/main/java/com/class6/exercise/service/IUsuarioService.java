package com.class6.exercise.service;

import com.class6.exercise.model.Usuario;

public interface IUsuarioService { //interfaz personalizada

	public Usuario findByUsername(String username);
}
