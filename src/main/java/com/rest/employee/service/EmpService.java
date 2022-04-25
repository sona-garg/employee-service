package com.rest.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.employee.VO.ResponseTemplateVO;
import com.rest.employee.VO.Role;
import com.rest.employee.entity.Employee;
import com.rest.employee.repository.EmpRepository;

@Service
public class EmpService {
    
	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Employee saveEmployee(Employee employee ) {
		return empRepository.save(employee);
	}
	
	public List<Employee> fetchEmpList(){
		return empRepository.findAll();
	}
	
	public ResponseTemplateVO getEmpWithRole(Long empId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Employee employee  = empRepository.findByEmpId(empId); 
		
		Role role = restTemplate.getForObject("http://localhost:9001/roles/"+employee.getRoleId(),Role.class );
		
		vo.setEmployee(employee);
		vo.setRole(role);
		
		return vo;
		
	}
}
