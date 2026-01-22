package com.ensa_agadir.agile_app.user.repository;

import com.ensa_agadir.agile_app.user.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Utilisateur,Integer> {
    Utilisateur findById(int id);
}
