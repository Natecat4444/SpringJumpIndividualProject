package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User_Order;

@Repository
public interface User_OrderRepository extends JpaRepository<User_Order, Integer>{
	public List<User_Order> getOrdersByOrdernum(int Ordernum);
	
	public List<User_Order> getOrdersByUser(int user_id);
}
