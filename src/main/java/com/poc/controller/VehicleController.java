package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.poc.exception.ResourceNotFoundException;
import com.poc.model.Vehicle;
import com.poc.repository.VehicleRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Vehicle", description = "Rest API for Vehicle operations", tags = "Vehicle API")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;

	@ApiOperation(value = "Find paginated All Vehicle Details")
	@GetMapping("/vehicles")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Page<Vehicle> getAllVehicles(Pageable pageable) {
		return vehicleRepository.findAll(pageable);
	}

	@ApiOperation(value = "Save Vehicle Details")
	@PostMapping("/vehicles")
	@PreAuthorize("hasRole('ADMIN')")
	public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@ApiOperation(value = "Update Vehicle Details")
	@PutMapping("/vehicles/{vehicleId}")
	@PreAuthorize("hasRole('ADMIN')")
	public Vehicle updateVehicle(@PathVariable Long vehicleId, @Valid @RequestBody Vehicle vehicleRequest) {
		return vehicleRepository.findById(vehicleId).map(vehicle -> {
			vehicle.setModel(vehicleRequest.getModel());
			vehicle.setMark(vehicleRequest.getMark());
			vehicle.setPicture(vehicleRequest.getPicture());
			return vehicleRepository.save(vehicle);
		}).orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleId + " not found"));
	}

	@ApiOperation(value = "Delete Vehicle Details by ID")
	@DeleteMapping("/vehicles/{vehicleId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
		return vehicleRepository.findById(vehicleId).map(vehicle -> {
			vehicleRepository.delete(vehicle);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleId + " not found"));
	}

}
