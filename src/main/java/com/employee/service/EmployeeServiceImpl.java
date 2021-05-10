package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.employee.enums.EmployeeType;
import com.employee.modal.Employee;
import com.employee.modal.HourlyEmployee;
import com.employee.modal.Manager;
import com.employee.modal.SalariedEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static List<Employee> employees;

	@PostConstruct
	private void createEmployees() {
		employees = new ArrayList<>();
		employees.add(getEmployee(1, "HourlyEmployee 1", EmployeeType.HOURLY));
		employees.add(getEmployee(2, "HourlyEmployee 2", EmployeeType.HOURLY));
		employees.add(getEmployee(3, "HourlyEmployee 3", EmployeeType.HOURLY));
		employees.add(getEmployee(4, "HourlyEmployee 4", EmployeeType.HOURLY));
		employees.add(getEmployee(5, "SalariedEmployee 1", EmployeeType.SALARIED));
		employees.add(getEmployee(6, "SalariedEmployee 2", EmployeeType.SALARIED));
		employees.add(getEmployee(7, "SalariedEmployee 3", EmployeeType.SALARIED));
		employees.add(getEmployee(8, "Manager 1", EmployeeType.MANAGER));
		employees.add(getEmployee(9, "Manager 2", EmployeeType.MANAGER));
		employees.add(getEmployee(10, "Manager 3", EmployeeType.MANAGER));
	}

	@Override
	public List<Employee> getEmployees() {
		return employees;
	}

	@Override
	public List<Employee> logWork(int id, int workDays) throws Exception {
		Optional<Employee> emp = employees.stream().filter(employee -> employee.getId() == id).findFirst();
		if (emp.isPresent()) {
			emp.get().logWork(workDays);
		} else {
			throw new RuntimeException("Employee not found");
		}
		return employees;
	}

	@Override
	public List<Employee> takeVacation(int id, float vacations) throws Exception {
		Optional<Employee> emp = employees.stream().filter(employee -> employee.getId() == id).findFirst();
		if (emp.isPresent()) {
			emp.get().takeVacation(vacations);
		} else {
			throw new RuntimeException("Employee not found");
		}
		return employees;
	}

	private Employee getEmployee(int id, String name, EmployeeType type) {
		Employee employee = null;
		switch (type) {
		case HOURLY:
			employee = new HourlyEmployee();
			employee.setType(EmployeeType.HOURLY);
			break;
		case SALARIED:
			employee = new SalariedEmployee();
			employee.setType(EmployeeType.SALARIED);
			break;
		case MANAGER:
			employee = new Manager();
			employee.setType(EmployeeType.MANAGER);
			break;
		}
		employee.setId(id);
		employee.setName(name);
		return employee;
	}

}
