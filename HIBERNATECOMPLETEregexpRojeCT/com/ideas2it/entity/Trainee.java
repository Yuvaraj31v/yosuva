package com.ideas2it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;


@NamedQueries(  
    {  
        @NamedQuery(  
        name  = "findTrainee",  
        query = "from Trainee e where e.isActive =:isActive"  
        ) , 

        @NamedQuery(  
        name  = "findTraineeById",  
        query = "from Trainee e where e.isActive =:isActive AND e.employeeId = :employeeId"  
        )  
    }  
)
  
@Entity
@Table (name ="Trainees")
public class Trainee extends Employee {
    
    @Id   
    @GeneratedValue	    
    private long id;    
   
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
    private List<Trainer> trainers;

    public List<Trainer> getTrainer(){
	return trainers;
    }
                                    
    public void setTrainer(List<Trainer> trainer) {
	this.trainers = trainer;
    }
	
}