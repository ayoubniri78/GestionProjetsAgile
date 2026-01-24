package com.ensa_agadir.agile_app.user.repository;

import com.ensa_agadir.agile_app.user.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Utilisateur,Integer> {
    Utilisateur findById(int id);
    
    Optional<Utilisateur> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM Utilisateur u WHERE " +
           "LOWER(u.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    java.util.List<Utilisateur> rechercherParMotCle(@Param("keyword") String keyword);
}
