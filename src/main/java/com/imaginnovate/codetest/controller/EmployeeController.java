package com.imaginnovate.codetest.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.codetest.entity.Employee;
import com.imaginnovate.codetest.model.TaxResult;
import com.imaginnovate.codetest.repository.EmployeeRepository;
import com.imaginnovate.codetest.service.TaxCalculationService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaxCalculationService taxCalculationService;

    @PostMapping("/create-employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid Employee employee) {
    	return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    	
    }
    
    
    @GetMapping("/tax-deduction/{employeeId}")
    public TaxResult getEmployeeTaxDeduction(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return taxCalculationService.calculateTax(employee);
    }
}