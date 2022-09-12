package com.ideas2it.dto;

import com.ideas2it.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public class TraineeDto extends EmployeeDto {


    private List<TrainerDto> trainers;

    public void setTrainerDto(List<TrainerDto> trainer) {
        this.trainers = trainer;
    }

    public List<TrainerDto> getTrainerDto() {
        return trainers;
    }

    public TraineeDto(String id, String firstName, String lastName, long phoneNumber, LocalDate dateOfBirth,
                      LocalDate dateOfJoin, String emailId) {

        super(id, firstName, lastName, phoneNumber, dateOfBirth, dateOfJoin, emailId);
    }

    public TraineeDto() {

    }

    public String toString() {
        return super.toString();
    }
}        
