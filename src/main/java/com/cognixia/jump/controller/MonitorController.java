package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Monitor;
import com.cognixia.jump.repository.MonitorRepository;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api")
@RestController
public class MonitorController {

	@Autowired
	MonitorRepository repo;
	
	@Operation(summary = "admin endpoint for creating monitors")
	@PostMapping("/monitor")
	public ResponseEntity<?> createMonitor(@RequestBody Monitor monitor){
		monitor.setId(null);
		Monitor newMon = repo.save(monitor);
		return ResponseEntity.status(201).body(newMon);
	}
	
	@Operation(summary = "Authenticated endpoint for viewing all monitors")
	@GetMapping("/monitors")
	public List<Monitor> getMonitors(){
		return repo.findAll();
	}
	
	@Operation(summary = "Authenticated endpoint for viewing a monitor")
	@GetMapping("/monitor/{id}")
	public ResponseEntity<?> getMonitor(@PathVariable int id){
		Optional<Monitor> found = repo.findById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Not found");
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@Operation(summary = "Authenticated endpoint for viewing monitors by brand")
	@GetMapping("/monitor/brand/{brand}")
	public List<Monitor> getMonitorByBrand(@PathVariable String brand){
		return repo.getMonitorsByBrand(brand);
	}
	
	@Operation(summary = "Authenticated endpoint for viewing all monitors of a certain size")
	@GetMapping("/monitor/size/{size}")
	public List<Monitor> getMonitorsBySize(@PathVariable Double size){
		return repo.getMonitorsBySize(size);
	}
	
	@Operation(summary = "Authenticated endpoint for viewing all monitors bigger than a certain size")
	@GetMapping("/monitor/size/gt/{size}")
	public List<Monitor> getMonitorsBySizeGreaterThan(@PathVariable Double size){
		return repo.getMonitorsBySizeGreaterThan(size);
	}
	
	@Operation(summary = "Authenticated endpoint for viewing all monitors of a certain size from a certain brand")
	@GetMapping("/monitor/brand/{brand}/{size}")
	public List<Monitor> getMonitorsBySizeAndBrand(@PathVariable String brand, @PathVariable Double size){
		return repo.getMonitorsBySizeAndBrand(size, brand);
		
	}
	
	@Operation(summary = "Authenticated endpoint for viewing all monitors from a certain brand greater than a specified size")
	@GetMapping("/monitor/brand/gt/{brand}/{size}")
	public List<Monitor> getMonitorsBysizeAndBrandGreaterThan(@PathVariable String brand, @PathVariable Double size){
		return repo.getMonitorsByBrandAndSizeGreaterThan(size, brand);
	}
}
