package com.ensa_agadir.agile_app.projectmanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@Data
public class TestProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description ;
    private Date startDate;
    private Date endDate;
    public TestProject(String name,String description,Date startDate,Date endDate){
        this.name=name;
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
    }
}
