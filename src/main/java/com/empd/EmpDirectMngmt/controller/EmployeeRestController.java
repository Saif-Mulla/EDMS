package com.empd.EmpDirectMngmt.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empd.EmpDirectMngmt.model.Employee;
import com.empd.EmpDirectMngmt.service.EmployeeService;

import jakarta.validation.Valid;

/*

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
	private final EmployeeService service;

	public EmployeeRestController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
	    return ResponseEntity.status(201).body(service.create(employee));
	  
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> get(@PathVariable String employeeId) {
	    return ResponseEntity.ok(service.getByEmployeeId(employeeId));
	}

	@GetMapping("/all")
	public ResponseEntity<Page<Employee>> list(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
	    return ResponseEntity.ok(service.list(PageRequest.of(page, size)));
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> update(@PathVariable String employeeId,
	                                         @Valid @RequestBody Employee employee) {
	    return ResponseEntity.ok(service.update(employeeId, employee));
	    
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Void> delete(@PathVariable String employeeId) {
	    service.delete(employeeId);
	    return ResponseEntity.noContent().build();
	    
	}
	
}

*/
