package com.ideas2it.service.impl;

import java.util.List;
import java.util.ArrayList;


import com.ideas2it.employeeConverter.EmployeeConverter;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import com.ideas2it.dao.IEmployeeDao;

import com.ideas2it.dao.impl.EmployeeDaoImpl;
import com.ideas2it.service.IEmployeeService;

public class EmployeeServiceImpl<T extends EmployeeDto> implements IEmployeeService<T> {

    private static IEmployeeDao<Trainer> trainerEmployeeDao = new EmployeeDaoImpl(new Trainer());
    private static IEmployeeDao<Trainee> traineeEmployeeDao = new EmployeeDaoImpl(new Trainee());

    private T value;

    public EmployeeServiceImpl(T value) {
        this.value = value;
    }

    /**
     * used to add Employee into List
     *
     * @param employee {@link T} the employee object
     * @return {@link void}
     */
    @Override
    public void addEmployee(T employee) {
        if (employee instanceof TrainerDto) {
            Trainer trainer = EmployeeConverter.convertTrainerDtoToTrainer((TrainerDto) employee);
            trainerEmployeeDao.insertEmployee((Trainer) trainer);

        } else {
            Trainee trainee = EmployeeConverter.convertTraineeDtoToTrainee((TraineeDto) employee);
            traineeEmployeeDao.insertEmployee((Trainee) trainee);
        }
    }

    /**
     * Returns list of  All Employees
     *
     * @return {@link List} of {@link T}
     */
    @Override
    public List<T> getAllEmployees() {
        if (value instanceof TrainerDto) {
            List<TrainerDto> trainers = EmployeeConverter.convertTrainerList((List<Trainer>) trainerEmployeeDao.retrieveAllEmployees());
            return (List<T>) trainers;

        } else {
            List<TraineeDto> trainees = EmployeeConverter.convertTraineeList((List<Trainee>) traineeEmployeeDao.retrieveAllEmployees());
            return (List<T>) trainees;
        }
    }

    /**
     * used to get employee by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @return {@void}
     */
    @Override
    public T getEmployeeById(String employeeId) {
        if (value instanceof TrainerDto) {
            TrainerDto trainerDto = EmployeeConverter.convertTrainerToTrainerDto((Trainer) trainerEmployeeDao.retrieveEmployeeById(employeeId));
            return (T) trainerDto;

        } else {
            TraineeDto traineeDto = EmployeeConverter.convertTraineeToTraineeDto((Trainee) traineeEmployeeDao.retrieveEmployeeById(employeeId));
            return (T) traineeDto;

        }
    }

    /**
     * used to associate traienrs and trainee and viceversa into List
     *
     * @param employees {@link List} of {@link String} id the employee to be associated object
     * @param employee  {@String} id of the employee
     * @return {@link void}
     */
    @Override
    public void association(List<String> employees, String employeeId) {
        if (value instanceof TrainerDto) {
            Trainer trainer = (Trainer) trainerEmployeeDao.retrieveEmployeeById(employeeId);
            if (trainer != null) {
                List<Trainee> trainees = new ArrayList<>();
                for (String id : employees) {
                    Trainee trainee = new Trainee();
                    trainee = traineeEmployeeDao.retrieveEmployeeById(id);
                    if (trainee != null) {
                        trainees.add(trainee);
                    }
                }
                trainer.setTrainee(trainees);
                trainerEmployeeDao.updateEmployee(trainer);
            }
        } else {
            Trainee trainee = (Trainee) traineeEmployeeDao.retrieveEmployeeById(employeeId);
            if (trainee != null) {
                List<Trainer> trainers = new ArrayList<>();
                for (String id : employees) {
                    Trainer trainer = new Trainer();
                    trainer = trainerEmployeeDao.retrieveEmployeeById(id);
                    if (trainer != null) {
                        trainers.add(trainer);
                    }
                }
                trainee.setTrainer(trainers);
                traineeEmployeeDao.updateEmployee(trainee);
            }
        }
    }

    /**
     * used to get the  assigned trainers and trainees by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @return {@void}
     */
    @Override
    public T displayTrainerAndTrainee(String employeeId) {
        if (value instanceof TrainerDto) {
            Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
            TrainerDto trainerDto = null;
            if (trainer != null) {
                List<TraineeDto> trainees = new ArrayList<>();
                for (Trainee trainee : trainer.getTrainee()) {
                    TraineeDto traineeDto = EmployeeConverter.convertTraineeToTraineeDto(trainee);
                    trainees.add(traineeDto);
                }
                trainerDto = EmployeeConverter.convertTrainerToTrainerDto(trainer);
                trainerDto.setTraineeDto(trainees);
            }
            return (T) trainerDto;
        } else {
            Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
            TraineeDto traineeDto = null;
            if (trainee != null) {
                List<TrainerDto> trainers = new ArrayList<>();
                for (Trainer trainer : trainee.getTrainer()) {
                    TrainerDto trainerDto = EmployeeConverter.convertTrainerToTrainerDto(trainer);
                    trainers.add(trainerDto);
                }
                traineeDto = EmployeeConverter.convertTraineeToTraineeDto(trainee);
                traineeDto.setTrainerDto(trainers);
            }
            return (T) traineeDto;
        }
    }

    /**
     * used to remove the Employee by his employeeId
     *
     * @param employeeId {@link String} id of employee
     * @return {@void}
     */
    @Override
    public void deleteEmployeeById(String employeeId) {
        if (value instanceof TrainerDto) {
            Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
            trainer.setIsActive(0);
            for (int i = 0; i < trainer.getTrainee().size(); i++) {
                trainer.getTrainee().remove(i);
            }
            trainerEmployeeDao.updateEmployee(trainer);
        } else {
            Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
            trainee.setIsActive(0);
            for (Trainer trainer : trainee.getTrainer()) {
                trainee.getTrainer().remove(trainer);
            }
            traineeEmployeeDao.updateEmployee(trainee);
        }
    }


    /**
     * used to update the Employee by his employeeId
     *
     * @param employeeId         {@link String} id of employee
     * @param mobileNumber{@link long} mobile number of employee
     * @return {@void}
     */
    @Override
    public void updateEmployeePhoneNumber(String employeeId, long phoneNumber) {
        if (value instanceof TrainerDto) {
            Trainer trainer = trainerEmployeeDao.retrieveEmployeeById(employeeId);
            trainer.setPhoneNumber(phoneNumber);
            trainerEmployeeDao.updateEmployee(trainer);
        } else {
            Trainee trainee = traineeEmployeeDao.retrieveEmployeeById(employeeId);
            trainee.setPhoneNumber(phoneNumber);
            traineeEmployeeDao.updateEmployee(trainee);
        }
    }
}