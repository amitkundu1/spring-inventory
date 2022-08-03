package com.coforge.training.ims.model;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addressId;

    private String street;
    private String city;
    private int pincode;
    
    /*
     * Modeling with foreign key relationship in jpa
     *place  @OneToOne on the primary class entity feild dealer
     *Place @JoinColumn to configure foreign key column dealer_id in the Address class that maps to the primary key column of the dealer class
     */
    @OneToOne 
    @JoinColumn(name="dealer_id")
    private Dealer dealer;

	public Address() {
		super();
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
    
    

}
