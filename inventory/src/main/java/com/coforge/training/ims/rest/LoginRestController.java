package com.coforge.training.ims.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.ims.exception.ResourceNotFoundException;
import com.coforge.training.ims.model.Address;
import com.coforge.training.ims.model.Dealer;
import com.coforge.training.ims.model.DealerAddress;
import com.coforge.training.ims.service.LoginRestService;

@RestController
@RequestMapping(value = "/api")
public class LoginRestController {
     
	@Autowired
	private LoginRestService lrservice;
	
	//Open Postman and make POST request - http://localhost:8085/ims/api/dealers
    //Under body tab --> raw --> Text --> Json and type the json data to be saved
	@PostMapping("/dealers") 
    public DealerAddress createDealer(@Validated @RequestBody DealerAddress dealer) throws ResourceNotFoundException{
 
        
        System.out.println("Hello "+ dealer.getEmail()+" " +dealer.getPassword());
        Dealer d=new Dealer();
        d.setEmail(dealer.getEmail());
        d.setFname(dealer.getFname());
        d.setLname(dealer.getLname());
        d.setPassword(dealer.getPassword());
        System.out.println("Hello "+ dealer.getEmail()+" " +dealer.getPassword());
        d.setDob(dealer.getDob());
        d.setPhoneNo(dealer.getPhoneNo());

        Address a=new Address();
        a.setStreet(dealer.getStreet());
        a.setCity(dealer.getCity());
        a.setPincode(dealer.getPincode());


        d.setAddress(a);
        a.setDealer(d);

        d = lrservice.registerDealer(d);
         return dealer;
    }
	
	//Open Postman and make GET request - http://localhost:8085/ims/api/dealers
	@GetMapping("/dealers")
	public List<DealerAddress> getAllDealers(){
		
		return lrservice.getAllDealers();
	}
	
	//Open Postman and make POST request - http://localhost:8085/ims/api/dealer
	 @PostMapping("/dealer")
	    public Boolean loginDealer(@Validated @RequestBody Dealer dealer) throws ResourceNotFoundException
	    {
	        Boolean a=false;
	        String email=dealer.getEmail();
	        String password=dealer.getPassword();
	        //System.out.println(email+password);
	        Dealer d = lrservice.loginDealer(email).orElseThrow(() ->
	        new ResourceNotFoundException("Dealer not found for this id :: "));
	    //    System.out.println(d.getEmail() +d.getPassword() );

	        if(email.equals(d.getEmail()) && password.equals(d.getPassword()))
	                {
	        //    System.out.println(d.getEmail() +d.getPassword() );
	            a=true;

	                }
	        return a;
	    }
}
