package com.ensa_agadir.agile_app.product.repository;

import com.ensa_agadir.agile_app.product.models.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Integer> {
    List<Epic> findByProductBacklogId(Integer backlogId);
    Optional<Epic> findByProductBacklogIdAndIsEpicGeneraleTrue(Integer backlogId);
}
