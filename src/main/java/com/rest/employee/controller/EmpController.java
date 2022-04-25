package com.rest.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.employee.VO.ResponseTemplateVO;
import com.rest.employee.entity.Employee;
import com.rest.employee.service.EmpService;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmpController {
    
	@Autowired
	private EmpService empService;
	
	@PostMapping("/")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return empService.saveEmployee(employee);
	}
	
	@GetMapping("/")
    public List<Employee> fetchEmpList() {
        
        return empService.fetchEmpList();
    }

	
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getEmpWithRole(@PathVariable("id") Long empId) {
		return empService.getEmpWithRole(empId);
	}
	
}
