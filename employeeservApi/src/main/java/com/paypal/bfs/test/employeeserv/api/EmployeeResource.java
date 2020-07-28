package com.paypal.bfs.test.employeeserv.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Interface for employee resource operations.
 */
@RequestMapping(value = "/v1/bfs/employees")
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @GetMapping("/{id}")
    ResponseEntity<EmployeeDto> employeeGetById(@PathVariable("id") String id);

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<EmployeeDto> deleteById(@PathVariable("id") Long id);

    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------
    
    /**
     * This method will be use to store the Employee Data in the H2 Data Base
     * @param employee
     * @return
     */
    @PostMapping("")
    ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employee);

    /**
     * This Method will be used to update the employee object
     * @param employee
     * @return
     */
    @PutMapping("")
    ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employee);
    
    /**
     * This method will be used to return the list of Employees from the data base.
     * @return
     */
    @GetMapping("")
    ResponseEntity<List<EmployeeDto>> getEmpList();
    
    
}
