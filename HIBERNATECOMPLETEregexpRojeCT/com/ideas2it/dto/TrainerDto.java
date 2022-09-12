package com.ideas2it.dto;

import com.ideas2it.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public class TrainerDto extends EmployeeDto {


    private List<TraineeDto> trainees;

    public void setTraineeDto(List<TraineeDto> trainee) {
        this.trainees = trainee;
    }

    public List<TraineeDto> getTraineeDto() {
        return trainees;
    }


    public TrainerDto(String id, String firstName, String lastName, long phoneNumber, LocalDate dateOfBirth, LocalDate dateOfJoin, String emailId) {
        super(id, firstName, lastName, phoneNumber, dateOfBirth, dateOfJoin, emailId);
    }

    public TrainerDto() {

    }

    public String toString() {
        return super.toString();
    }

}        