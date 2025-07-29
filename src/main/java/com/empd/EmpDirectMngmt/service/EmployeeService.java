package com.empd.EmpDirectMngmt.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empd.EmpDirectMngmt.model.Employee;

public interface EmployeeService {

	Employee create(Employee employeeInbound);
	Employee getByEmployeeId(String employeeId);
	Page<Employee> list(Pageable pageable);
	Employee update(String employeeId, Employee employeeInbound);
	void delete(String employeeId);
}
