package com.empd.EmpDirectMngmt.service;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empd.EmpDirectMngmt.model.Employee;
import com.empd.EmpDirectMngmt.model.EmployeeAddress;
import com.empd.EmpDirectMngmt.reposiitory.EmployeeRepository;

import jakarta.validation.Valid;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository repo;

	public EmployeeServiceImpl(EmployeeRepository repo) {
		this.repo = repo;
	}


	@Override
	public Employee create(@Valid Employee emp) {
		// reject attempts to set server fields (redundant to entity validation but explicit)
		if (emp.getFullName() != null && !emp.getFullName().isBlank()) {
	      throw new IllegalArgumentException("fullName is server-generated");
	    }
	    
		if (repo.existsByEmployeeId(emp.getEmployeeId())) {
	      throw new DataIntegrityViolationException("employee_id already exists");
	    }
	
	    if (emp.getAddresses() != null) {
	      for (EmployeeAddress a : emp.getAddresses()) {
	        a.setEmployee(emp);
	      }
	    }
	    
	    return repo.save(emp);
	    
	}

	@Override
	@Transactional(readOnly = true)
	public Employee getByEmployeeId(String employeeId) {
	    return repo.findByEmployeeId(employeeId)
	        .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeId));
	    
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Employee> list(Pageable pageable) {
	    return repo.findAll(pageable);
	 
	}

	@Override
	public Employee update(String employeeId, @Valid Employee emp) {
		Employee existing = repo.findByEmployeeId(employeeId)
	        .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeId));

	    
		emp.setEmployeeId(employeeId);

	    // Replace scalar fields
	    existing.setFirstName(emp.getFirstName());
	    existing.setMiddleName(emp.getMiddleName());
	    existing.setLastName(emp.getLastName());
	    existing.setDesignation(emp.getDesignation());
	    existing.setDepartment(emp.getDepartment());

	    // Replace addresses (edit page add/remove rows)
	    existing.clearAndAddAllAddresses(emp.getAddresses());

	    return repo.save(existing);
	  }

	@Override
	public void delete(String employeeId) {
	    Employee e = repo.findByEmployeeId(employeeId)
	        .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + employeeId));
	    
	    repo.delete(e); // child addresses removed by FK ON DELETE CASCADE and/or orphanRemoval
	}
}
