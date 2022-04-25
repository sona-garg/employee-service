package com.rest.employee;



import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import com.rest.employee.entity.Employee;
import com.rest.employee.repository.EmpRepository;
import com.rest.employee.service.EmpService;

@SpringBootTest(classes= {ServiceMackitoTests.class})
public class ServiceMackitoTests {
	
	@Mock
	EmpRepository empRepository; 
	
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	EmpService empService;
	
	public List<Employee> myEmployee;
	
	@Test
	@Order(1)
	public void test_fetchEmpList() {
		
		myEmployee = new ArrayList<Employee>();
		myEmployee.add(new Employee(1L,"Sandy","Vyas","sandy@gmail.com",1L));
		myEmployee.add(new Employee(2L,"Reva","Jammy","reva@gmail.com",2L));
		when(empRepository.findAll()).thenReturn(myEmployee);//Mocking
		assertEquals(2,empService.fetchEmpList().size());
		}
	@Test
	@Order(2)
	public void test_saveEmployee() {
		Employee employee = new Employee(3L,"Sandy","Vyas","sandy@gmail.com",3L);
		when(empRepository.save(employee)).thenReturn(employee);
		assertEquals(empService.saveEmployee(employee),employee);
		
	}
	
	
	

}
