package com.empd.EmpDirectMngmt.reposiitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empd.EmpDirectMngmt.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmployeeId(String employeeId);
	boolean existsByEmployeeId(String employeeId);
	
}
