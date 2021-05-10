package com.employee.modal;

import java.text.DecimalFormat;

import com.employee.enums.EmployeeType;

public abstract class Employee {
	public static final int MAX_WORK_DAYS = 260;
	public static final int MIN_WORK_DAYS = 1;
	
	private int id;
	private String name;
	private float vacationDays;
	private int workDays;
	private EmployeeType type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(float vacationDays) {
		this.vacationDays = vacationDays;
	}

	public int getWorkDays() {
		return workDays;
	}

	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	public abstract void logWork(int days) throws Exception;
	
	public void takeVacation(float days) throws RuntimeException{
		if(days > vacationDays) {
			throw new RuntimeException("Vacations balance is not enough to take vacations.");
		}
		float vacations = this.vacationDays - days;
		this.setVacationDays(vacations);
		DecimalFormat df = new DecimalFormat(".00"); 
		this.setVacationDays(Float.valueOf(df.format(this.getVacationDays())));
	}
	
	protected void validateWorkDays(int numberOfDaysWorked) throws RuntimeException{
		this.workDays += numberOfDaysWorked;
		if(this.workDays < MIN_WORK_DAYS || this.workDays > MAX_WORK_DAYS) {
			this.workDays -= numberOfDaysWorked;
			throw new RuntimeException("Work days can not be less than 1 or greater than 260 days.");
		}
	}
}
