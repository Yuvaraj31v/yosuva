package com.ideas2it.entity;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Employee {

    Employee() {
	
    }
    private String employeeId;

    private String employeeFirstName;

    private String employeeLastName;

    private long employeePhoneNumber;

    private LocalDate employeeDateOfBirth;

    private LocalDate employeeDateOfJoin;

    private String employeeEmailId;

    private int isActive = 1; 

    public void setId(String id) {
	this.employeeId = id;
    }   

    public String getId() {
	return employeeId;
    }

    public void setFirstName(String firstName) {
	this.employeeFirstName = firstName;
    }

    public String getFirstName() {
	return employeeFirstName;
    }

    public void setLastName(String lastName) {
	this.employeeLastName = lastName;
    }

    public String getLastName() {
	return employeeLastName;
    }

    public void setPhoneNumber(long phoneNumber) {
	this.employeePhoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
	return employeePhoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
	this.employeeDateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
	return  employeeDateOfBirth ;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
	this.employeeDateOfJoin = dateOfJoin;
    }

     public LocalDate getDateOfJoin() {
	return  employeeDateOfJoin;
    }

    public void setEmailId(String emailId) {
	this.employeeEmailId = emailId;
    }

    public String getEmailId() {
	return  employeeEmailId;
    }

    public void setIsActive(int value) {
	this.isActive = value;
    }
}