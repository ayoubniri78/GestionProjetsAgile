package com.ensa_agadir.agile_app.projectmanagement.service;

import com.ensa_agadir.agile_app.projectmanagement.dao.ProjectRepo;
import com.ensa_agadir.agile_app.projectmanagement.models.TestProject;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final ProjectRepo projectRepo;

    public TestService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public TestProject saveProject(TestProject project){
        return projectRepo.save(project);
    }

    public TestProject findProjectById(int id){
        return projectRepo.findAllById(id);
    }

}
