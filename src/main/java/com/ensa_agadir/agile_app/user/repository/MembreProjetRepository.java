package com.ensa_agadir.agile_app.user.repository;

import com.ensa_agadir.agile_app.user.models.MembreProjet;
import com.ensa_agadir.agile_app.user.models.UserRoleProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreProjetRepository extends JpaRepository<MembreProjet, UserRoleProjectId> {

}
