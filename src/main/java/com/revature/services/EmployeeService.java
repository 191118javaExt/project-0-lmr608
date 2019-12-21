package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {
	
	EmployeeDAO repository = null;
	
	public EmployeeService() {
		repository = new EmployeeDAOImpl();
	}
	
	public EmployeeService(EmployeeDAO dao) {
		repository = dao;
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	public boolean insert(Employee e) {
		return repository.insert(e);
	}
	

}