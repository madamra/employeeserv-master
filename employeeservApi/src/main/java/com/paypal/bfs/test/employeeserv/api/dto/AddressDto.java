package com.paypal.bfs.test.employeeserv.api.dto;

import com.paypal.bfs.test.employeeserv.annatation.FieldValidator;
import com.paypal.bfs.test.employeeserv.annatation.Validate;

@Validate
public class AddressDto {

	private Long id;
	
	private Long empId;
	
	@FieldValidator(min = 1, max = 255, message = "Line1 Value min is 1 and Max is 255 Chars",required = true)
	private String line1;

	@FieldValidator(max = 255, message = "Line2 Value Max is 255 Chars",required = false)
	private String line2;

	@FieldValidator(min = 1, max = 100, message = "City Value Min is 1 and Max is 100 Chars",required = true)
	private String city;

	@FieldValidator(min = 1, max = 50, message = "State Value Min us 1 and Max is 50 Chars",required = true)
	private String state;

	@FieldValidator(min = 1, max = 50, message = "Country Value Min is 1 and Max is 50 Chars",required = true)
	private String country;

	@FieldValidator(min = 5, max = 6, message = "Zip Code Min is 5 Value and Max is 6 Chars",required = true)
	private String zipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}
