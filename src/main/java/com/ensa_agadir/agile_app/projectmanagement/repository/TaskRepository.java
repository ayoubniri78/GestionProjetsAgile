package com.ensa_agadir.agile_app.projectmanagement.repository;

import com.ensa_agadir.agile_app.projectmanagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Trouver les tâches d'un Backlog de Sprint spécifique
    List<Task> findBySprintBacklogId(Long sprintBacklogId);

    // Trouver toutes les tâches liées à une User Story (suivi d'avancement)
    List<Task> findByUserStoryId(Long userStoryId);

    // Trouver les tâches assignées à une personne spécifique
    List<Task> findByAssigneeUserId(Long assigneeUserId);
}
