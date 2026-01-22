package com.ensa_agadir.agile_app.projectmanagement.repository;

import com.ensa_agadir.agile_app.projectmanagement.models.SprintMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintMemberRepository extends JpaRepository<SprintMember, Long> {

    // Lister les membres d'un sprint
    List<SprintMember> findBySprintId(Long sprintId);

    // Vérifier si un utilisateur est déjà dans le sprint
    Optional<SprintMember> findBySprintIdAndUserId(Long sprintId, Long userId);
}
