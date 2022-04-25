package com.rest.employee;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.employee.controller.EmpController;
import com.rest.employee.entity.Employee;
import com.rest.employee.service.EmpService;

@ComponentScan(basePackages="com.rest.employee")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMockMvcTests.class})
public class ControllerMockMvcTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	EmpService empService;
	
	@InjectMocks
	EmpController empController;
	public List<Employee> myEmployee;
	Employee employee;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}
	
	@Test
	@Order(1)
	public void test_fetchEmpList() throws Exception {
	    myEmployee = new ArrayList<Employee>();
		myEmployee.add(new Employee(1L,"Sandy","Vyas","sandy@gmail.com",1L));
		myEmployee.add(new Employee(2L,"Reva","Jammy","reva@gmail.com",2L));
		when(empService.fetchEmpList()).thenReturn(myEmployee);
		this.mockMvc.perform(get("/")).equals(myEmployee);
		
	}
	@Test
	@Order(2)
	public void test_saveEmployee() throws Exception{
		Employee employee = new Employee(3L,"Sandy","Vyas","sandy@gmail.com",3L);
		when(empService.saveEmployee(employee)).thenReturn(employee);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody=mapper.writeValueAsString(employee);
		this.mockMvc.perform(post("/")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.equals(employee);
	}
	
	
	

}
