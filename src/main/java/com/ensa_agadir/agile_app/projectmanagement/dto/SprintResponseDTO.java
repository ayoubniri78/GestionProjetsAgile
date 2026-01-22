package com.ensa_agadir.agile_app.projectmanagement.dto;

import com.ensa_agadir.agile_app.projectmanagement.models.StatutSprint;
import java.time.LocalDateTime;

public record SprintResponseDTO(
        Long id,
        String nom,
        LocalDateTime dateDebut,
        LocalDateTime dateFin,
        String objectif,
        StatutSprint statut,
        Long projectId) {
}
