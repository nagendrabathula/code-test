package com.imaginnovate.codetest.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.imaginnovate.codetest.entity.Employee;
import com.imaginnovate.codetest.model.TaxResult;
import com.imaginnovate.codetest.service.TaxCalculationService;

@Service
public class TaxCalculationService{

    public TaxResult calculateTax(Employee employee) {
        double yearlySalary = calculateYearlySalary(employee);
        double taxAmount = calculateTaxAmount(yearlySalary);
        double cessAmount = calculateCessAmount(yearlySalary);

        TaxResult result = new TaxResult();
        result.setEmployeeCode(employee.getEmployeeId());
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setYearlySalary(yearlySalary);
        result.setTaxAmount(taxAmount);
        result.setCessAmount(cessAmount);

        return result;
    }

    private double calculateYearlySalary(Employee employee) {
        // Extract the year from DOJ
        Calendar dojCalendar = Calendar.getInstance();
        dojCalendar.setTime(employee.getDoj());
        int dojYear = dojCalendar.get(Calendar.YEAR);

        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);

        int monthsWorked = 12 - dojCalendar.get(Calendar.MONTH); 
        if (dojYear == currentYear) {
            double partialMonthDays = (30.0 - dojCalendar.get(Calendar.DAY_OF_MONTH) + 1) / 30.0; 
            monthsWorked = (int)(monthsWorked - partialMonthDays); 
        }

        double yearlySalary = employee.getSalary() * monthsWorked;

        return yearlySalary;
    }



    private double calculateTaxAmount(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary <= 500000) {
            return (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            return 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
        } else {
            return 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
        }
    }

    private double calculateCessAmount(double yearlySalary) {
        if (yearlySalary > 2500000) {
            double excessAmount = yearlySalary - 2500000;
            return excessAmount * 0.02;
        } else {
            return 0;
        }
    }
}