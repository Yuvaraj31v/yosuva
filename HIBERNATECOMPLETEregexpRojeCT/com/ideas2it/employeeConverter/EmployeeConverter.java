package com.ideas2it.employeeConverter;

import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.entity.Trainee;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.TrainerDto;
import com.ideas2it.dto.TraineeDto;
import java.util.List;
import java.util.ArrayList;


public class EmployeeConverter {

    /**
     *used to convert TrainerDto to trainer
     *@param trainerDto {@link TrainerDto} the trainerDto Object
     *@return {@link Trainer} the trainer object 
     */
    public static Trainer convertTrainerDtoToTrainer(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();
	trainer.setId(trainerDto.getEmployeeId());
	trainer.setFirstName(trainerDto.getEmployeeFirstName());
	trainer.setLastName(trainerDto.getEmployeeLastName());
	trainer.setPhoneNumber(trainerDto.getEmployeePhoneNumber());
	trainer.setDateOfBirth(trainerDto.getEmployeeDateOfBirth());
	trainer.setDateOfJoin(trainerDto.getEmployeeDateOfJoin());
	trainer.setEmailId(trainerDto.getEmployeeEmailId());
        return trainer;
    }

    /**
     *used to convert TraineeDto to trainee
     *@param traineeDto {@link TraineeDto} the traineeDto 
     *@return {@link Trainee} the trainee object 
     */  
    public static Trainee convertTraineeDtoToTrainee(TraineeDto traineeDto) {
        Trainee trainee = new Trainee();
	trainee.setId(traineeDto.getEmployeeId());
	trainee.setFirstName(traineeDto.getEmployeeFirstName());
	trainee.setLastName(traineeDto.getEmployeeLastName());
	trainee.setPhoneNumber(traineeDto.getEmployeePhoneNumber());
	trainee.setDateOfBirth(traineeDto.getEmployeeDateOfBirth());
	trainee.setDateOfJoin(traineeDto.getEmployeeDateOfJoin());
	trainee.setEmailId(traineeDto.getEmployeeEmailId());
        return trainee;
    }

    /**
     *used to convert Trainer to TrainerDto
     *@param trainer {@link TrainerDto} the trainer 
     *@return {@link TrainerDto} the trainerDto object 
     */
    public static TrainerDto convertTrainerToTrainerDto(Trainer trainer) {
	TrainerDto trainerDto = null;
	if (trainer != null) {
	    trainerDto =  new TrainerDto(trainer.getId(),trainer.getFirstName(),trainer.getLastName(),trainer.getPhoneNumber(),trainer.getDateOfBirth(),trainer.getDateOfJoin(),trainer.getEmailId());
	}
        return trainerDto;
    }

    /**
     *used to convert Trainee to TraineeDto
     *@param trainee {@link TrainerDto} the trainee 
     *@return {@link TraineeDto} the traineeDto object 
     */    
    public static TraineeDto convertTraineeToTraineeDto(Trainee trainee) {
	TraineeDto traineeDto = null;
	if (trainee !=null) {
	    traineeDto =  new TraineeDto(trainee.getId(),trainee.getFirstName(),trainee.getLastName(),trainee.getPhoneNumber(),trainee.getDateOfBirth(),trainee.getDateOfJoin(),trainee.getEmailId());
	}
        return traineeDto;
    }

     /**
     *used to convert TraineeList to TraineeDtoList
     *@param trainees {@link List<Trainee>} trainees
     *@return {@link List<TraineeDto>} the List of traineeDto  
     */
    public static List<TraineeDto> convertTraineeList(List<Trainee> trainees) {
	List<TraineeDto> traineeDtos = new ArrayList<>();
	
	TraineeDto traineeDto = null; 
	for (Trainee trainee : trainees) {
	    traineeDto = convertTraineeToTraineeDto(trainee);
	    traineeDtos.add(traineeDto);
	}
	return traineeDtos;
    }

    /**
     *used to convert TrainerDtoList to TrainerList
     *@param  {@link List<Trainer>} trainers
     *@return {@link List<TraineeDto>} List of TraienrDto  
     */	
    public static List<TrainerDto> convertTrainerList(List<Trainer> trainers) {
	List<TrainerDto> trainerDtos = new ArrayList<>();	
	TrainerDto trainerDto = null;	
	for (Trainer trainer : trainers) {
	    trainerDto = convertTrainerToTrainerDto(trainer);
	    trainerDtos.add(trainerDto);	  
	}
	return trainerDtos;
    }
}