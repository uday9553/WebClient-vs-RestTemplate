package com.uday.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.uday.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ControllerClass {
	
	@GetMapping("/getAllEmployees")
	private List<Employee> getAllEmployees() {
		System.out.println("before"+System.currentTimeMillis());
	    try {
			Thread.sleep(2000L);// delay
			System.out.println("after"+System.currentTimeMillis());
			 return Arrays.asList(
				      new Employee("Uday", "@user1"),
				      new Employee("Sagar", "@user2"),
				      new Employee("Ram", "@user1"));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} 
	    
	}
	
	@GetMapping("/getEmployee")
	private Employee getEmployee() {
		System.out.println("before"+System.currentTimeMillis());
	    try {
			Thread.sleep(2000L);// delay
			System.out.println("after"+System.currentTimeMillis());
			 return new Employee("Uday", "@user1");
				     
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} 
	    
	}

	@GetMapping("/getWithResttemplate")
	public List<Employee> getEmployeesByRestTemplate() {
	    System.out.println("Starting Controller!"+System.currentTimeMillis());
	    final String uri = "http://localhost:8080/getAllEmployees";

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<Employee>> response = restTemplate.exchange(
	      uri, HttpMethod.GET, null,
	      new ParameterizedTypeReference<List<Employee>>(){});

	    List<Employee> result = response.getBody();
	    result.forEach(emp -> System.out.println(emp.toString()));
	    System.out.println("Exiting BLOCKING Controller!"+System.currentTimeMillis());
	    return result;
	}
	
	@Autowired
	WebClient webClient;
	
	@GetMapping(value = "/getEmployeesWithWebclient")
	public Flux<Employee> getEmployeesWithWebclient() {
		System.out.println("Starting NON-BLOCKING Controller!");
		Flux<Employee> employees = webClient.get().uri("/getAllEmployees").retrieve().bodyToFlux(Employee.class);

		employees.subscribe(emp -> System.out.println(emp.toString()));
		System.out.println("Exiting NON-BLOCKING Controller!");
		return employees;
	}
	
	@GetMapping(value = "/getEmployeeWithWebclient")
	public Mono<Employee> getEmployeeWithWebclient() {
		System.out.println("Starting NON-BLOCKING Controller!");
		Mono<Employee> employees = webClient.get().uri("/getEmployee").retrieve().bodyToMono(Employee.class);

		employees.subscribe(emp -> System.out.println(emp.toString()));
		System.out.println("Exiting NON-BLOCKING Controller!");
		return employees;
	}
	
}
