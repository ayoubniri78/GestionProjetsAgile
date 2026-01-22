package com.ensa_agadir.agile_app.projectmanagement.dto;

import com.ensa_agadir.agile_app.projectmanagement.models.TaskStatut;
import java.time.Duration;

public record TaskResponseDTO(
        Long id,
        String titre,
        String description,
        TaskStatut statut,
        Duration tempsEstime,
        Duration tempsReel,
        Long userStoryId,
        Long assigneeUserId) {
}
