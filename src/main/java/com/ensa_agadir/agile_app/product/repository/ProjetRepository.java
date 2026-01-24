package com.ensa_agadir.agile_app.product.repository;

import com.ensa_agadir.agile_app.product.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    Projet findById(int id);

    @Query("SELECT p FROM Projet p WHERE " +
           "LOWER(p.nomProjet) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Projet> rechercherParMotCle(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT p FROM Projet p JOIN p.membreProjets mp WHERE mp.utilisateur.id = :utilisateurId")
    List<Projet> findByUtilisateurId(@Param("utilisateurId") Integer utilisateurId);
}
