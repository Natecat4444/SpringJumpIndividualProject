package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;

@Entity
public class User_Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Status{
		Placed, Recieved, Shipped, Delivered, CANCELED
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Range(min = 0)
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "monitor_id")
	private Monitor monitor;
	
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer ordernum;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	public User_Order(Integer id, Integer quantity, User user, Monitor monitor, Integer ordernum) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.user = user;
		this.monitor = monitor;
		this.ordernum = ordernum;
		this.status = Status.Recieved;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	

}
