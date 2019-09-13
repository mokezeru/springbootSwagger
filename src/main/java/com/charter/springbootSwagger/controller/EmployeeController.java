package com.charter.springbootSwagger.controller;

import com.charter.springbootSwagger.exception.ResourceNotFoundException;
import com.charter.springbootSwagger.model.Employee;
import com.charter.springbootSwagger.repository.EmployeeRepository;
import com.charter.springbootSwagger.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/employees")
    public List < Employee > getAllEmployees() {
        return employeeService.listAllEmployee();
    }

    @ApiOperation(value = "Get an employee by Id")
    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee > getEmployeeById(
            @ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployee(employeeId);
        if(employee == null)
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping("/employees")
    public Employee createEmployee(
            @ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @ApiOperation(value = "Update an employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(
            @ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable(value = "id") Long employeeId,
            @ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployee(employeeId);
        if(employee == null)
                throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(
            @ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployee(employeeId);
        if(employee==null)
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        employeeService.deleteEmployee(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}