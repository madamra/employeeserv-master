package com.paypal.bfs.test.employeeserv.api.dto;

import java.io.Serializable;

import com.paypal.bfs.test.employeeserv.annatation.FieldValidator;
import com.paypal.bfs.test.employeeserv.annatation.Validate;

@Validate
public class EmployeeDto implements Serializable{

	/**
	 * Default Serial ID;
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@FieldValidator(min = 1, max = 255, message = "First Name Minimum Value size 1 and Max size 255", required = true)
	private String firstname;
	
	@FieldValidator(min = 1, max = 255, message = "Last Name Minimum Value size 1 and Max size 255", required = true)
	private String lastName;
	
	private String dateOfBirth;
	
	private AddressDto address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	
}
