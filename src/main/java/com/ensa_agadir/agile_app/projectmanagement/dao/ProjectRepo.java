package com.ensa_agadir.agile_app.projectmanagement.dao;

import com.ensa_agadir.agile_app.projectmanagement.models.TestProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<TestProject,Integer> {
    public TestProject findAllById(int id);
}
