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
import com.class6.exercise.dto.VentaDTO;
import com.class6.exercise.exception.ModelNotFoundException;
import com.class6.exercise.model.Venta;
import com.class6.exercise.service.IVentaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = { "http://localhost:4200" }) //se especifica el dominio, en este caso para la transferencia y envío de datos al puerto de angular, es un arreglo
@RestController
@RequestMapping("/ventas")
@Api(value = "Venta Management System", description = "All handlers that belong to Venta.")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	
	@ApiOperation(value = "View a list of available ventas", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> ventas = ventaService.listar();
		return new ResponseEntity<List<Venta>>(ventas, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get an Venta by Id", response = ResponseEntity.class)
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/{id}")
	public ResponseEntity<Venta> leer(
		@ApiParam(value = "Venta id from which venta object will retrieve", required = true) @PathVariable("id") Integer id) {
		Venta obj = ventaService.leer(id);
		if(obj.getIdVenta() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		
		return new ResponseEntity<Venta>(obj, HttpStatus.FOUND);
	}
	

	@ApiOperation(value = "Post an VentaDTO ", response = ResponseEntity.class)
	@Secured({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody VentaDTO ventaDTO) {
		Venta obj = ventaService.registrarTransactional(ventaDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@ApiOperation(value = "Put an Venta ", response = ResponseEntity.class)
	@Secured({"ROLE_ADMIN"})
	@PutMapping
	public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta obj) {
		Venta update = ventaService.leer(obj.getIdVenta());
		if(update.getIdVenta() == null) {
			throw new ModelNotFoundException("Id not found: "+obj.getIdVenta());
		}
		
		Venta objUpdate = ventaService.modificar(obj);
		return new ResponseEntity<Venta>(objUpdate, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an Venta ", response = ResponseEntity.class)
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	public ResponseEntity<Venta> eliminar(@PathVariable("id") Integer id) {
		Venta obj = ventaService.leer(id);
		if(obj.getIdVenta() == null) {
			throw new ModelNotFoundException("Id not found: "+id);
		}
		
		ventaService.eliminar(id);
		
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
}
