package com.empd.EmpDirectMngmt.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int id;

	@Column(name = "employee_id", length = 10, nullable = false, unique = true)
	private String employeeId;

	private String firstName;
	private String middleName;
	private String lastName;

	@Column(length = 512)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String fullName;
	
	private String designation;
	private String department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeAddress> addresses = new ArrayList<>();
	
	
//	/** Prevent client from setting fullName (server computes it). */
//	@AssertTrue(message = "fullName is server-generated; do not provide it")
//	public boolean isFullNameNotProvided() {
//		return this.fullName == null || this.fullName.isBlank();
//	}


	@AssertTrue(message = "Only one PRIMARY address is allowed")
	public boolean isAtMostOnePrimaryAddress() {
		if (addresses == null) return true;
		return addresses.stream().filter(a -> a.getAddressType() == AddressType.PRIMARY).count() <= 1;
	}

	@PrePersist 
	@PreUpdate
	public void buildFullName() {
	    String fn = (firstName == null) ? "" : firstName.trim();
	    String mn = (middleName == null) ? "" : (" " + middleName.trim());
	    String ln = (lastName == null) ? "" : (" " + lastName.trim());
	    this.fullName = (fn + mn + ln).trim().replaceAll("\\s+", " ");
	}

	// Helpers to maintain bidirectional link
	public void addAddress(EmployeeAddress addr) {
	    if (addr == null) 
	    	return;
	    addr.setEmployee(this);
	    this.addresses.add(addr);
	}
	
	public void clearAndAddAllAddresses(List<EmployeeAddress> addList) {
	    this.addresses.clear();
	    
	    if (addList != null) {
	      for (EmployeeAddress a : addList) 
	    	  addAddress(a);
	    }
	}

	public List<EmployeeAddress> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<EmployeeAddress> addresses) {
		this.addresses = addresses;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public EmployeeAddress getPrimaryAddress() {
	    if (addresses == null) 
	    	return null;
	    
	    return addresses.stream()
	            .filter(a -> a.getAddressType() == AddressType.PRIMARY)
	            .findFirst()
	            .orElse(null);
	}
	
}
