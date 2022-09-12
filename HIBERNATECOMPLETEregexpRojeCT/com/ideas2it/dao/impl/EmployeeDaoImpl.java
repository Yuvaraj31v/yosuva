package com.ideas2it.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.criterion.Restrictions;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueObjectException;

import com.ideas2it.singleton.ConnectionEstablishment;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dao.IEmployeeDao;

public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> {

    private T value;
    private Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    public EmployeeDaoImpl(T value) {
        this.value = value;
    }

    /**
     * used to insert Employee into List
     *
     * @param employee {@link T} the employee object
     * @return {@link void}
     */
    @Override
    public void insertEmployee(T employee) {
        try (Session session = ConnectionEstablishment.getConnection();) {
            Transaction transaction = session.beginTransaction();
            if (employee instanceof Trainer) {
                Trainer trainer = (Trainer) employee;
                session.persist(trainer);
            } else {

                Trainee trainee = (Trainee) employee;
                session.persist(trainee);
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Invalid");
        }
    }

    /**
     * Retrive list of  All trainers
     *
     * @return {@link List} of {@link Trainer}
     */
    @Override
    public List<T> retrieveAllEmployees() {
        List<T> employees = new ArrayList<>();
        try (Session session = ConnectionEstablishment.getConnection();) {

            if (value instanceof Trainer) {
                TypedQuery query = session.getNamedQuery("findTrainer");
                query.setParameter("isActive", 1);
                employees = query.getResultList();
            } else {
                TypedQuery query = session.getNamedQuery("findTrainee");
                query.setParameter("isActive", 1);
                employees = query.getResultList();
            }
        } catch (HibernateException e) {
            logger.error("Invalid");
        }
        return (List<T>) employees;
    }

    /**
     * Retrive list of  All Employees
     *
     * @param emp {@link T} the employee object
     * @return {@link List} of {@link T}
     */
    @Override
    public T retrieveEmployeeById(String employeeId) {
        T employee = null;
        try (Session session = ConnectionEstablishment.getConnection();) {
            if (value instanceof Trainer) {
                TypedQuery query = session.getNamedQuery("findTrainerById");
                query.setParameter("isActive", 1);
                query.setParameter("employeeId", employeeId);
                employee = (T) query.getSingleResult();
            } else {
                TypedQuery query = session.getNamedQuery("findTraineeById");
                query.setParameter("isActive", 1);
                query.setParameter("employeeId", employeeId);
                employee = (T) query.getSingleResult();
            }
        } catch (HibernateException e) {
            logger.error("Invalid");
        } catch (NoResultException e) {
            logger.error("Invalid");
        }
        return (T) employee;
    }

    /**
     * update  Employee with his id
     *
     * @param employeeId          {@link String} id of employee
     * @param mobileNumber{@long} mobile number of employee
     * @return {@link void}
     */
    @Override
    public void updateEmployee(T employee) {
        try (Session session = ConnectionEstablishment.getConnection();) {
            Transaction transaction = session.beginTransaction();
            if (value instanceof Trainer) {
                session.update((Trainer) employee);
            } else {
                session.update((Trainee) employee);
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Invalid");
        }

    }
}	
      
    

    
   