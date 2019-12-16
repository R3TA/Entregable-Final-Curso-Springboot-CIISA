package com.class6.exercise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.class6.exercise.exception.ModelNotFoundException;
import com.class6.exercise.model.Comida;
import com.class6.exercise.service.IComidaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/comidas")
@Api(value = "Comida Management System", description = "All handlers that belong to Comida.")
public class ComidaController {

	@Autowired
	private IComidaService comidaService;
	
	@ApiOperation(value = "View a list of available Comidas", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping
	public ResponseEntity<List<Comida>> listar() {
		List<Comida> comidas = comidaService.listar();
		return new ResponseEntity<List<Comida>>(comidas, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get an Comida by Id", response = ResponseEntity.class)
	@GetMapping("/{id}")
	public ResponseEntity<Comida> leer(
		@ApiParam(value = "Comida id from which Comida object will retrieve", required = true) @PathVariable("id") Integer id) {
		Comida obj = comidaService.leer(id);
		if(obj.getIdComida() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		
		return new ResponseEntity<Comida>(obj, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = "Post an Comida ", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Comida comida) {
		Comida obj = comidaService.registrar(comida);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdComida()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation(value = "Put an Comida ", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity<Comida> modificar(@Valid @RequestBody Comida obj) {
		Comida objUpdate = comidaService.modificar(obj);
		return new ResponseEntity<Comida>(objUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an Comida ", response = ResponseEntity.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Comida> eliminar(@PathVariable("id") Integer id) {
		Comida obj = comidaService.leer(id);
		if(obj.getIdComida() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		comidaService.eliminar(id);
		
		return new ResponseEntity<Comida>(obj, HttpStatus.FOUND);
	}
}
