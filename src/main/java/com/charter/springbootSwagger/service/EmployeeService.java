package com.charter.springbootSwagger.service;

import com.charter.springbootSwagger.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> listAllEmployee();
    public Employee findEmployee(Long Id);
    public Employee saveEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
}
