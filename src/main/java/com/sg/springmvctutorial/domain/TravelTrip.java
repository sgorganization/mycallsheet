package com.sg.springmvctutorial.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TravelTrip {
	
	//@Id
	//@GeneratedValue
	private int id;
	
	@NotNull
	@Size(min=5,max=25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String country;
	
	@NotNull
    @Size(min = 5, max = 25)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String city;
	
	@NotNull
    @NotEmpty
	private String fromDate;
	
	@NotNull
    @NotEmpty
	private String toDate;
	
	private boolean business;
	
	
	public TravelTrip() {
		super();		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public boolean isBusiness() {
		return business;
	}
	public void setBusiness(boolean business) {
		this.business = business;
	}
	
}
