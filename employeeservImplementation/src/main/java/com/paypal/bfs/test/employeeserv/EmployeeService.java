package com.paypal.bfs.test.employeeserv;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {

	/**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    EmployeeDto employeeGetById(Long id);

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    EmployeeDto deleteById(Long id);

    
    /**
     * This method will be use to store the Employee Data in the H2 Data Base
     * @param employee
     * @return
     */
    EmployeeDto saveEmployee(EmployeeDto employee);

    /**
     * This Method will be used to update the employee object
     * @param employee
     * @return
     */
    EmployeeDto updateEmployee(@RequestBody EmployeeDto employee);
    
    /**
     * This method will be used to return the list of Employees from the data base. If No records present in the database, then this method
     * will return the empty list.
     * @return
     */
    List<EmployeeDto> getEmpList();
}
