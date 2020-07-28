package com.paypal.bfs.test.employeeserv.impl;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import com.paypal.bfs.test.employeeserv.exception.ValidationFailedException;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	
	private EmployeeService employeeService;
	
	public EmployeeResourceImpl(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

    @Override
    public ResponseEntity<EmployeeDto> employeeGetById(String id) {

    	EmployeeDto empObj = employeeService.employeeGetById(Long.valueOf(id));
    	if(null == empObj)
    		throw new ValidationFailedException("EMPLOYEE_NOT_EXIST");
    	
		return new ResponseEntity<>(empObj, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<EmployeeDto> saveEmployee(EmployeeDto employee) {
		EmployeeDto empObj = employeeService.saveEmployee(employee);
		return new ResponseEntity<>(empObj, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EmployeeDto> updateEmployee(EmployeeDto employee) {
		EmployeeDto empObj = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(empObj, HttpStatus.OK);
	}
    
	@Override
	public ResponseEntity<List<EmployeeDto>> getEmpList() {
		HttpHeaders headers = new HttpHeaders();
		List<EmployeeDto> empList = employeeService.getEmpList();
		if(!CollectionUtils.isEmpty(empList))
			headers.add("X-Records-Count", String.valueOf(empList.size()));
		return new ResponseEntity<>(empList,headers, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<EmployeeDto> deleteById(Long id) {
		EmployeeDto deleteById = employeeService.deleteById(id);
		return new ResponseEntity<>(deleteById,HttpStatus.OK);
	}
    
}
