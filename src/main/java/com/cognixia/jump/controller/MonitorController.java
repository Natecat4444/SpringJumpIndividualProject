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

@RequestMapping("/api")
@RestController
public class MonitorController {

	@Autowired
	MonitorRepository repo;
	
	@PostMapping("/monitor")
	public ResponseEntity<?> createMonitor(@RequestBody Monitor monitor){
		monitor.setId(null);
		Monitor newMon = repo.save(monitor);
		return ResponseEntity.status(201).body(newMon);
	}
	
	@GetMapping("/monitors")
	public List<Monitor> getMonitors(){
		return repo.findAll();
	}
	
	@GetMapping("/monitor/{id}")
	public ResponseEntity<?> getMonitor(@PathVariable int id){
		Optional<Monitor> found = repo.findById(id);
		
		if(found.isEmpty()) {
			return ResponseEntity.status(404).body("Not found");
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@GetMapping("/monitor/brand/{brand}")
	public List<Monitor> getMonitorByBrand(@PathVariable String brand){
		return repo.getMonitorsByBrand(brand);
	}
	
	@GetMapping("/monitor/size/{size}")
	public List<Monitor> getMonitorsBySize(@PathVariable Double size){
		return repo.getMonitorsBySize(size);
	}
	
	@GetMapping("/monitor/size/gt/{size}")
	public List<Monitor> getMonitorsBySizeGreaterThan(@PathVariable Double size){
		return repo.getMonitorsBySizeGreaterThan(size);
	}
	
	@GetMapping("/monitor/brand/{brand}/{size}")
	public List<Monitor> getMonitorsBySizeAndBrand(@PathVariable String brand, @PathVariable Double size){
		return repo.getMonitorsBySizeAndBrand(size, brand);
		
	}
	
	@GetMapping("/monitor/brand/gt/{brand}/{size}")
	public List<Monitor> getMonitorsBysizeAndBrandGreaterThan(@PathVariable String brand, @PathVariable Double size){
		return repo.getMonitorsByBrandAndSizeGreaterThan(size, brand);
	}
}
