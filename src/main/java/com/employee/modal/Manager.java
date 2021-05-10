package com.employee.modal;

import java.text.DecimalFormat;

public class Manager extends SalariedEmployee {

	@Override
	public void logWork(int numberOfDaysWorked) throws Exception {
		super.validateWorkDays(numberOfDaysWorked);
		float vacations = (Float.valueOf(numberOfDaysWorked) / MAX_WORK_DAYS) * 30;
		this.setVacationDays(this.getVacationDays() + vacations);
		DecimalFormat df = new DecimalFormat(".00"); 
		this.setVacationDays(Float.valueOf(df.format(this.getVacationDays())));
	}

}