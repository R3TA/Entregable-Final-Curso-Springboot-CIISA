package com.class6.exercise.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.class6.exercise.exception.ModelNotFoundException;
import com.class6.exercise.model.Pelicula;
import com.class6.exercise.service.IPeliculaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/peliculas")
@Api(value = "Pelicula Management System", description = "All handlers that belong to Pelicula.")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;
	
	@ApiOperation(value = "View a list of available Peliculas", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping
	public ResponseEntity<List<Pelicula>> listar() {
		List<Pelicula> peliculas = peliculaService.listar();
		return new ResponseEntity<List<Pelicula>>(peliculas, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get an Pelicula by Id", response = ResponseEntity.class)
	@GetMapping("/{id}")
	public ResponseEntity<Pelicula> leer(
		@ApiParam(value = "Pelicula id from which Pelicula object will retrieve", required = true) @PathVariable("id") Integer id) {
		Pelicula obj = peliculaService.leer(id);
		if(obj.getIdPelicula() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		
		return new ResponseEntity<Pelicula>(obj, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Post an Pelicula", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Pelicula pelicula) {
		Pelicula obj = peliculaService.registrar(pelicula);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPelicula()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation(value = "Put an Pelicula", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity<Pelicula> modificar(@Valid @RequestBody Pelicula obj) {
		Pelicula objUpdate = peliculaService.modificar(obj);
		return new ResponseEntity<Pelicula>(objUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an Pelicula", response = ResponseEntity.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Pelicula> eliminar(@PathVariable("id") Integer id) {
		Pelicula obj = peliculaService.leer(id);
		if(obj.getIdPelicula() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		peliculaService.eliminar(id);
		
		return new ResponseEntity<Pelicula>(obj, HttpStatus.FOUND);
	}
}
