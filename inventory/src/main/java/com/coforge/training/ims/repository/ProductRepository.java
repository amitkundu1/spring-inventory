package com.coforge.training.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.ims.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	// Custom Finder Method.The implementation is Plugged in by spring data JPA autimatically.
	List<Product> findByMadien(String country);

}
