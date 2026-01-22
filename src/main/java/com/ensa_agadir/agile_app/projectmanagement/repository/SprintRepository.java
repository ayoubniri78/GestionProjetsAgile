package com.ensa_agadir.agile_app.projectmanagement.repository;

import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.StatutSprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    // Récupérer tous les sprints d'un projet spécifique
    List<Sprint> findByProjectId(Long projectId);

    // Trouver le sprint actif d'un projet (utile pour le tableau de bord)
    List<Sprint> findByProjectIdAndStatutSprint(Long projectId, StatutSprint statutSprint);
}
