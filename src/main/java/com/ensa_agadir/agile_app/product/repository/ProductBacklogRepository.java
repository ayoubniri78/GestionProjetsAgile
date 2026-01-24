package com.ensa_agadir.agile_app.product.repository;

import com.ensa_agadir.agile_app.product.models.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Integer> {
    Optional<ProductBacklog> findByProjetId(Integer projetId);
}
