package com.ideas2it.service;

import java.util.List;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dao.IEmployeeDao;
import com.ideas2it.dao.impl.EmployeeDaoImpl;


public interface IEmployeeService<T extends EmployeeDto> {

    /**
     * used to add Employee into List
     *
     * @param employee {@link T} the employee object
     * @return {@link}
     */

    public void addEmployee(T employee);

    /**
     * Returns list of  All Employees
     *
     * @return {@link List} of {@link T}
     */

    public List<T> getAllEmployees();

    /**
     * used to get employee by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @return {@void}
     */

    public T getEmployeeById(String employeeId);

    /**
     * used to update the Employee by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @param phoneNumber{@link long} mobile number of employee
     * @return {@void}
     */

    public void updateEmployeePhoneNumber(String employeeId, long phoneNumber);

    /**
     * used to update the Employee by his employeeId
     *
     * @param employeeId  or trainee {@link String} id of employee
     * @param employees or trainers{@link List} of {String} id  of employee
     * @return {@void}
     */

    public void association(List<String> employees, String employeeId);

    /**
     * Returns list of  All associated employees
     *
     * @param employeeId {link String} id of Employee
     * @return {@link List} of {@link T}
     */

    public T displayTrainerAndTrainee(String employeeId);

    /**
     * used to remove the Employee by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @return {@void}
     */

    public void deleteEmployeeById(String employeeId);

} 


   
		 
	