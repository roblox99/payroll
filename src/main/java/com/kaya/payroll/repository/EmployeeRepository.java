package com.kaya.payroll.repository;

import com.kaya.payroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA repositories are interfaces with CRUD methods against a back-end data store.
// With this interfaces data can easily be accessed to create, update, delete or find employees.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
