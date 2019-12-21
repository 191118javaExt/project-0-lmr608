package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();
	public void employeeLogin();
	public void approveDeny();
	
}
