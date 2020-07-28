package com.paypal.bfs.test.employeeserv.mapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.paypal.bfs.test.employeeserv.AddressEntity;
import com.paypal.bfs.test.employeeserv.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.Utility;
import com.paypal.bfs.test.employeeserv.api.dto.AddressDto;
import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;

@Component
public class MapperUtility {

	public EmployeeDto converFromDBEntity(EmployeeEntity employeeEntity) {
		EmployeeDto employee = new EmployeeDto();
		employee.setFirstname(employeeEntity.getFirstName());
		employee.setLastName(employeeEntity.getLastname());
		employee.setId(employeeEntity.getId());
		employee.setDateOfBirth(employeeEntity.getDateOfBirth().toString());
		
		if(null != employeeEntity.getAddressEntity()) {
			AddressDto addressEntity = new AddressDto();
			addressEntity.setEmpId(employee.getId());
			addressEntity.setId(employeeEntity.getAddressEntity().getId());
			addressEntity.setCity(employeeEntity.getAddressEntity().getCity());
			addressEntity.setLine1(employeeEntity.getAddressEntity().getLine1());
			addressEntity.setLine2(employeeEntity.getAddressEntity().getLine2());
			addressEntity.setZipCode(employeeEntity.getAddressEntity().getZipCode());
			addressEntity.setState(employeeEntity.getAddressEntity().getState());
			addressEntity.setCountry(employeeEntity.getAddressEntity().getCountry());
			employee.setAddress(addressEntity);
		}
		
		return employee;
	}
	
	public EmployeeEntity converToDBEntity(EmployeeDto employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setFirstName(employee.getFirstname());
		employeeEntity.setLastname(employee.getLastName());
		if(StringUtils.isNotBlank(employee.getDateOfBirth()))
			employeeEntity.setDateOfBirth(Utility.convertFromString(employee.getDateOfBirth(),false));
		
		if(null != employee.getAddress()) {
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setCity(employee.getAddress().getCity());
			addressEntity.setLine1(employee.getAddress().getLine1());
			addressEntity.setLine2(employee.getAddress().getLine2());
			addressEntity.setZipCode(employee.getAddress().getZipCode());
			addressEntity.setState(employee.getAddress().getState());
			addressEntity.setCountry(employee.getAddress().getCountry());
			addressEntity.setEmployee(employeeEntity);
			employeeEntity.setAddressEntity(addressEntity);
		}
		
		
		return employeeEntity;
	}
}
