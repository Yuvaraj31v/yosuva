package com.ideas2it.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;


@NamedQueries(  
    {  
        @NamedQuery(  
        name  = "findTrainer",  
        query = "from Trainer e where e.isActive =:isActive"  
        ),

	@NamedQuery(  
        name  = "findTrainerById",  
        query = "from Trainer e where e.isActive =:isActive AND e.employeeId = :employeeId"   
        )
         
    }  
)  
@Entity
@Table(name ="Trainers")

public class Trainer extends Employee {

    @Id
    @GeneratedValue    
    private long id;
     
    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)  
    @JoinTable(name = "trainee_trainer",   
    joinColumns = { @JoinColumn(name = "Trainer_id") },   
    inverseJoinColumns = { @JoinColumn(name = "Trainee_id") }) 
    private List<Trainee> trainees;

    public void setTrainee(List<Trainee> trainee ) {
	this.trainees= trainee;
    }

    public List<Trainee> getTrainee() {
	return trainees;
    }
}