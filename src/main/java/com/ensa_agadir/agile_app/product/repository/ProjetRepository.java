package com.ensa_agadir.agile_app.product.repository;

import com.ensa_agadir.agile_app.product.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    Projet findById(int id);
}
