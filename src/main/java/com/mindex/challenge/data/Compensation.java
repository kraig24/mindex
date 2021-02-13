package com.mindex.challenge.data;

public class Compensation {

    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation() {
    }
    public String geteffectiveDate() {
        return effectiveDate;
    }

    public void seteffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}