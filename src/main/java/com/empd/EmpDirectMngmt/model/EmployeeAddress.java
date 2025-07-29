package com.empd.EmpDirectMngmt.model;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_address")
public class EmployeeAddress {
	
	@Id 
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private int id;

  	@ManyToOne(fetch = FetchType.LAZY, optional = false)
  	@JoinColumn(name = "employee_id", nullable = false)
  	@JsonIgnore
  	private Employee employee;

  	@Enumerated(EnumType.STRING)
  	@Column(name = "address_type", nullable = false)
  	private AddressType addressType;

  	private String address1;
  	private String address2;
  	private String postalCode;

  	public int getId() {
	  return id;
  	}
  
  	public void setId(int id) {
  		this.id = id;
	}
  	
  	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
  	
  	public Employee getEmployee() {
		return employee;
	}
  	
  	public void setAddress1(String address1) {
		this.address1 = address1;
	}
  	
  	public String getAddress1() {
		return address1;
	}
  	
  	public void setAddress2(String address2) {
		this.address2 = address2;
	}
  	
  	public String getAddress2() {
		return address2;
	}
  	
  	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
  	
  	public AddressType getAddressType() {
		return addressType;
	}
  	
  	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
  	
  	public String getPostalCode() {
		return postalCode;
	}
	
}
