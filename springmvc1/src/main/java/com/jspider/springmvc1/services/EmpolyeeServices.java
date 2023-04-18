package com.jspider.springmvc1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.springmvc1.pojo.EmployeePojo;
import com.jspider.springmvc1.repository.EmployeeRepository;

@Service
public class EmpolyeeServices {
    
	@Autowired
	EmployeeRepository repository;
	
	public EmployeePojo addEmployee(String name, String email, long contact, String designation, double salary) {
		EmployeePojo employee=repository.addEmployee(name, email, contact, designation, salary);
		
		
		return employee;
		
		
	}

    public EmployeePojo search(int id) {
		EmployeePojo employee = repository.searchEmployee(id);
		return employee;
	}

	public List<EmployeePojo>searchAllEmployees() {
		List<EmployeePojo> employees = (List<EmployeePojo>)repository.searchAllEmployees();
		return employees;
	}

	public void removeEmployee(int id) {
		repository.removeEmployee(id);
		
	}

	public void updateEmployee(int id, String name, String email, long contact, String designation, double salary) {
		
		repository.updateEmployee(id,name,email,contact,designation,salary);
		
	}

	
	
}
