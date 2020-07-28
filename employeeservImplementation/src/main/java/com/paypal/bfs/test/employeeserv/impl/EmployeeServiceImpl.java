package com.paypal.bfs.test.employeeserv.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.paypal.bfs.test.employeeserv.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.EmployeeService;
import com.paypal.bfs.test.employeeserv.aop.Validation;
import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import com.paypal.bfs.test.employeeserv.exception.ValidationFailedException;
import com.paypal.bfs.test.employeeserv.mapper.MapperUtility;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	
	private MapperUtility mapperUtility;
	
	@Autowired
	public EmployeeServiceImpl (EmployeeRepository employeeRepository,MapperUtility mapperUtility) {
		this.employeeRepository = employeeRepository;
		this.mapperUtility = mapperUtility;
	}

	@Override
	public EmployeeDto employeeGetById(Long id) {
		Optional<EmployeeEntity> empObj = employeeRepository.findById(id);
		if(empObj.isPresent())
			return mapperUtility.converFromDBEntity(empObj.get());
		return null;
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employee) {
		
		validateEmployeeRequest(employee);
		
		EmployeeEntity empEntity = mapperUtility.converToDBEntity(employee);
		employeeRepository.save(empEntity);
		return mapperUtility.converFromDBEntity(empEntity);
	}

	/**
	 * This method will be use to validate the Employee Request objecy for bith save and update method
	 * 
	 * @param employee
	 */
	private void validateEmployeeRequest(EmployeeDto employee) {
		// validating the request before process
		List<String> validationMessages = new LinkedList<String>();
		Set<String> validationMessage = Validation.validate(employee, new HashSet<String>());
		if (null != validationMessage && !validationMessage.isEmpty()) {
			validationMessages.addAll(validationMessage);
			throw new ValidationFailedException(validationMessage.stream().collect(Collectors.toList()).toString());
		}
		
		// Address Object need validate
		if(CollectionUtils.isEmpty(validationMessage) && null != employee.getAddress()) {
			validationMessage = Validation.validate(employee.getAddress(), new HashSet<String>());
			if (null != validationMessage && !validationMessage.isEmpty()) {
				validationMessages.addAll(validationMessage);
				throw new ValidationFailedException(validationMessage.stream().collect(Collectors.toList()).toString());
			}
		}
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employee) {
		
		// validating the request before process
		validateEmployeeRequest(employee);
		if(null == employee.getId() || 0 == employee.getId())
			throw new ValidationFailedException("TO_UPDATE_ID_MANDATORY");
		
		EmployeeEntity empEntity = mapperUtility.converToDBEntity(employee);
		empEntity.setId(employee.getId());
		employeeRepository.save(empEntity);
		return mapperUtility.converFromDBEntity(empEntity);
	}

	@Override
	public List<EmployeeDto> getEmpList() {
		List<EmployeeEntity> empList = employeeRepository.findAll();
		if(!CollectionUtils.isEmpty(empList)) {
			return empList.stream().map(EmployeeEntity -> {
				return mapperUtility.converFromDBEntity(EmployeeEntity);
			}).collect(toList());
		}
		return Collections.emptyList();
	}
	
	@Override
	public EmployeeDto deleteById(Long id) {
		EmployeeDto employeeGetById = employeeGetById(id);
		if(null == employeeGetById)
			throw new ValidationFailedException("EMPLOYEE_NOT_EXIST");
		
		employeeRepository.deleteById(id);
		
		return employeeGetById;
	}

}
