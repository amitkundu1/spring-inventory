package com.coforge.training.ims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coforge.training.ims.model.Dealer;
import com.coforge.training.ims.model.DealerAddress;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
		// long is the data type of id feild of dealer class
	
	/*
     * This interface has save(),findAll(),findById(),deleteById(),count()
    etc.. inbuilt methods of jpa repository for various database operations.
    This interface will be implemented by class automatically
    */
	
	// custom Method to fetch record/object based on email feild non id feild
	//public Dealer findByEmail(String email);
	
	public Optional<Dealer> findByEmail(String email);
	
	// custom query using jpql.
	@Query("SELECT new com.raj.model.DealerAddress(d.id,d.email,d.fname,d.lname,"
			+ "d.password,d.dob,d.phoneNo,a.street,a.city,a.pincode)"
			+ "FROM Dealer d INNER JOIN d.address a")
	public List<DealerAddress> fetchDealerInnerJoin();
}
