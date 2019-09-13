package com.charter.springbootSwagger.service;

import com.charter.springbootSwagger.exception.ResourceNotFoundException;
import com.charter.springbootSwagger.model.Employee;
import com.charter.springbootSwagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAllEmployee(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployee(Long Id) {
        return  employeeRepository.findById(Id).get();
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee){
         employeeRepository.delete(employee);
    }
}
