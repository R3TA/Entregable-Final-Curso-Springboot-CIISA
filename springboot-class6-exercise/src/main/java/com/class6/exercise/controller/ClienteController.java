package com.class6.exercise.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.class6.exercise.model.Cliente;
import com.class6.exercise.service.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = { "http://localhost:4200" }) //se especifica el dominio, en este caso para la transferencia y envío de datos al puerto de angular, es un arreglo
@RestController
@RequestMapping("/clientes")
@Api(value = "Cliente Management System", description = "All handlers that belong to Cliente.")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@ApiOperation(value = "View a list of available Clientes", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteService.listar();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get an Cliente by Id", response = ResponseEntity.class)
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> leer(
		@ApiParam(value = "Cliente id from which Cliente object will retrieve", required = true) @PathVariable("id") Integer id) {
		Cliente obj = clienteService.leer(id);
		if(obj.getIdCliente() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		
		return new ResponseEntity<Cliente>(obj, HttpStatus.FOUND);
	}
	
	@ApiOperation(value = " Post an Cliente", response = ResponseEntity.class)
	@Secured("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cliente cliente) {
		Cliente obj = clienteService.registrar(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation(value = "Put an Cliente", response = ResponseEntity.class)
	@Secured("ROLE_ADMIN")
	@PutMapping
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente obj) {
		Cliente objUpdate = clienteService.modificar(obj);
		return new ResponseEntity<Cliente>(objUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an Cliente", response = ResponseEntity.class)
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> eliminar(@PathVariable("id") Integer id) {
		Cliente obj = clienteService.leer(id);
		if(obj.getIdCliente() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		clienteService.eliminar(id);
		
		return new ResponseEntity<Cliente>(obj, HttpStatus.FOUND);
	}
}
