package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;

public interface IEmployeeDao<T extends Employee> {


    /**
     *used to insert Employee into List
     *@param employee {@link T} the employee object
     *@return {@link}
     */
    public void insertEmployee(T employee);

    /** Retrive list of  employees
     * @return {@link List} of {@link T}
     */ 
    public List<T> retrieveAllEmployees();

     /**
     * Retrive employee by their Id
     * @param employeeId {@link T} the employee object
     * @return {@link List} of {@link T}
     */   

    public T retrieveEmployeeById(String employeeId);

    /**
     * update  Employee with his id  
     * @param employee {@link String} id of employee
     * @return {@link }
     */   
    public void updateEmployee(T employee);

}  
