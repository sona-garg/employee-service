package com.rest.employee;

import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.rest.employee.entity.Employee;

@SpringBootTest(classes= {ControllerIntegrationTests.class})
public class ControllerIntegrationTests {

	@Test
	@Order(1)
	void test_fetchEmpList() throws JSONException {
		//Data has to generated when the server is running again because H2 data is not persistant
		String expected="[\r\n"
				+ "    {\r\n"
				+ "        \"empId\": 1,\r\n"
				+ "        \"empFirstName\": \"Sim\",\r\n"
				+ "        \"empLastName\": \"Behl\",\r\n"
				+ "        \"email\": \"Sim@gmail.com\",\r\n"
				+ "        \"roleId\": 1\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"empId\": 2,\r\n"
				+ "        \"empFirstName\": \"David\",\r\n"
				+ "        \"empLastName\": \"Sammy\",\r\n"
				+ "        \"email\": \"David@gmail.com\",\r\n"
				+ "        \"roleId\": 2\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate restTemplate =  new TestRestTemplate();
		ResponseEntity<String> response=restTemplate.getForEntity("http://localhost:9002/employee/", String.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected,response.getBody(), false);

	}
	@Test
	@Order(2)
	void test_getEmpWithRole() throws JSONException {
		//Data has to generated when the server is running again because H2 data is not persistant
		String expected="{\r\n"
				+ "    \"employee\": {\r\n"
				+ "        \"empId\": 1,\r\n"
				+ "        \"empFirstName\": \"Sim\",\r\n"
				+ "        \"empLastName\": \"Behl\",\r\n"
				+ "        \"email\": \"Sim@gmail.com\",\r\n"
				+ "        \"roleId\": 1\r\n"
				+ "    },\r\n"
				+ "    \"role\": {\r\n"
				+ "        \"roleId\": 1,\r\n"
				+ "        \"roleName\": \"Manager\",\r\n"
				+ "        \"roleCode\": \"001\"\r\n"
				+ "    }\r\n"
				+ "}";
		TestRestTemplate restTemplate =  new TestRestTemplate();
		ResponseEntity<String> response=restTemplate.getForEntity("http://localhost:9002/employee/1", String.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected,response.getBody(), false);

	}
	@Test
	@Order(3)
	void test_saveEmployee() throws JSONException {
		//Data has to generated when the server is running again because H2 data is not persistant
		Employee employee = new Employee(3L,"Simmi","Garewal","simmi@gmail.com",3L);
		String expected = "{\r\n"
				+ "    \"empId\": 3,\r\n"
				+ "    \"empFirstName\": \"Teri\",\r\n"
				+ "    \"empLastName\": \"Fox\",\r\n"
				+ "    \"email\": \"terry@gmail.com\",\r\n"
				+ "    \"roleId\": 3\r\n"
				+ "}";

		TestRestTemplate restTemplate =  new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> request = new HttpEntity<Employee>(employee,headers);
		ResponseEntity<String> response=restTemplate.getForEntity("http://localhost:9002/employee/", String.class);
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected,response.getBody(), false);


	}



}
