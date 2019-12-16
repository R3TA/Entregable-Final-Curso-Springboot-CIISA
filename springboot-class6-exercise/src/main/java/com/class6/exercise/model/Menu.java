package com.class6.exercise.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenu;
	
	@NotNull
	@Size(max = 80)
	@Column(nullable = false)
	private String icono;
	
	@NotNull
	@Size(max = 20)
	@Column(nullable = false)
	private String nombre;
	
	@NotNull
	@Size(max = 50)
	@Column(nullable = false)
	private String url;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "menu_rol", 
				joinColumns = @JoinColumn(name = "idMenu", referencedColumnName = "idMenu"),
				inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "idRole"))
	private List<Rol> roles;
	
	private static final long serialVersionUID = 1L;
}
