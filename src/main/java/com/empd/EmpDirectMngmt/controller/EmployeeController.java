package com.empd.EmpDirectMngmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empd.EmpDirectMngmt.model.AddressType;
import com.empd.EmpDirectMngmt.model.Employee;
import com.empd.EmpDirectMngmt.model.EmployeeAddress;
import com.empd.EmpDirectMngmt.service.EmployeeService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    public String maintain(Model model) {
        
        model.addAttribute("employees", service.list(org.springframework.data.domain.PageRequest.of(0, 20)).getContent());
        return "employee/maintain";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Employee e = new Employee();
        // start with one empty PRIMARY address row (you can change to SECONDARY if you prefer)
        EmployeeAddress a = new EmployeeAddress();
        a.setAddressType(AddressType.PRIMARY);
        e.getAddresses().add(a);

        model.addAttribute("employee", e);
        model.addAttribute("addressTypes", AddressType.values());
        model.addAttribute("mode", "create");
        return "employee/create";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("addressTypes", AddressType.values());
            model.addAttribute("mode", "create");
            return "employee/create";
        }
        service.create(employee);
        return "redirect:/api/employee";
    }


    @GetMapping("/{employeeId}/edit")
    public String editForm(@PathVariable String employeeId, Model model) {
        Employee e = service.getByEmployeeId(employeeId);
        model.addAttribute("employee", e);
        model.addAttribute("addressTypes", AddressType.values());
        model.addAttribute("mode", "edit");
        return "employee/edit";
    }

    @PostMapping("/{employeeId}")
    public String update(@PathVariable String employeeId,
                         @ModelAttribute("employee") @Valid Employee employee,
                         BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("addressTypes", AddressType.values());
            model.addAttribute("mode", "edit");
            return "employee/edit";
        }
        service.update(employeeId, employee);
        return "redirect:/api/employee";
    }

    @PostMapping("/{employeeId}/delete")
    public String delete(@PathVariable String employeeId) {
        service.delete(employeeId); // cascades remove addresses
        return "redirect:/api/employee";
    }
    
   
}

