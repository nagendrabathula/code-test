package com.imaginnovate.codetest.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.codetest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}