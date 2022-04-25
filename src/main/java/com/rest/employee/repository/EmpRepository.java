package com.rest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.employee.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Long> {
   Employee findByEmpId(Long empId);
}
