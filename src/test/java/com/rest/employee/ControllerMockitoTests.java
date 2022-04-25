package com.rest.employee;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.employee.VO.ResponseTemplateVO;
import com.rest.employee.controller.EmpController;
import com.rest.employee.entity.Employee;
import com.rest.employee.service.EmpService;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes= {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	 
	 @Mock
	 EmpService empService;
	 
	 @InjectMocks
	 EmpController empController;
	 
	 public List<Employee> myEmployee;
	 Employee employee;
	 
	 @Test
	 @Order(1)
	 public void test_fetchEmpList() {
		 List<Employee> myEmployee = new ArrayList<Employee>();
			myEmployee.add(new Employee(1L,"Sandy","Vyas","sandy@gmail.com",1L));
			myEmployee.add(new Employee(2L,"Reva","Jammy","reva@gmail.com",2L));
			
		 when(empService.fetchEmpList()).thenReturn(myEmployee);//Mocking
		 List<Employee> res=empController.fetchEmpList();
		 assertEquals(res.size(),2);
	 }
	 
	 @Test
	 @Order(2)
	 public void test_saveEmployee() {
		 Employee employee = new Employee(3L,"Sandy","Vyas","sandy@gmail.com",3L);
		 when(empService.saveEmployee(employee)).thenReturn(employee);
		 Employee res =empController.saveEmployee(employee);
		 assertEquals(res,employee);
	 }
	 
	 
	 

}
