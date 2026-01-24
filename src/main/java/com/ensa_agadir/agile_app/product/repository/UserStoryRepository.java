package com.ensa_agadir.agile_app.product.repository;

import com.ensa_agadir.agile_app.product.models.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Integer> {
    List<UserStory> findByEpicId(Integer epicId);
    
    @Query("SELECT us FROM UserStory us WHERE us.epic IS NULL AND us.epic.productBacklog.id = :backlogId")
    List<UserStory> findUnassignedByBacklogId(@Param("backlogId") Integer backlogId);
}
