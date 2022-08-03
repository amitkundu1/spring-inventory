package com.coforge.training.ims.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.ims.model.Dealer;
import com.coforge.training.ims.repository.DealerRepository;

/*
 * In an application, the business logic resides within the service layer so we use the @Service Annotation to indicate that a class belongs to that layer.
One most important thing about the @Service Annotation is it can be applied only to classes.
It is used to mark the class as a service provider.
So overall @Service annotation is used with classes that provide some business functionalities.
Spring context will autodetect these classes when annotation-based configuration and classpath scanning is used.

@Transactional annotation, which provides broad support for transaction management i.e
 insert, update, select & delete ,and allows developers to concentrate on business logic rather than worrying about data integrity in the event of system failures.
 */

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private DealerRepository drepo;
	
	public void saveDealer(Dealer dealer) {
		drepo.save(dealer); // Invokes predefined save () method of JPA Repository
	}
	
	public Dealer  findByEmail(String email) {
		
		return drepo.findByEmail(email);

	}
	
}
				
				
				
				
				
				
				
				
