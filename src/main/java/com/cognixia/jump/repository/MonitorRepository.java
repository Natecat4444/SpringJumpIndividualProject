package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Integer>{
	public List<Monitor> getMonitorsByBrand(String Brand);
	
	public List<Monitor> getMonitorsBySize(Double size);
	
	@Query("Select m from Monitor m WHERE m.size > ?1")
	public List<Monitor> getMonitorsBySizeGreaterThan(Double size);
	
	@Query("Select m from Monitor m WHERE m.size = ?1 AND m.brand = ?2")
	public List<Monitor> getMonitorsBySizeAndBrand(Double size, String Brand);
	
	@Query("Select m from Monitor m WHERE m.size > ?1 AND m.brand = ?2")
	public List<Monitor> getMonitorsByBrandAndSizeGreaterThan(Double size, String Brand);
}
