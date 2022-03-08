package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Monitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private Double size;
	
	@Column(nullable = false)
	private Integer refresh_rate;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private String ports;

	
	public Monitor(Integer id, String brand, Double size, int refresh_rate, Double price, String ports) {
		super();
		this.id = id;
		this.brand = brand;
		this.size = size;
		this.refresh_rate = refresh_rate;
		this.price = price;
		this.ports = ports;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public int getRefresh_rate() {
		return refresh_rate;
	}

	public void setRefresh_rate(int refresh_rate) {
		this.refresh_rate = refresh_rate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}
	
	
}
