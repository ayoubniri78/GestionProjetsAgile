package com.ensa_agadir.agile_app.projectmanagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record SprintRequestDTO(
                @NotBlank(message = "Le nom du sprint est obligatoire") String nom,

                @NotNull(message = "La date de début est obligatoire") @FutureOrPresent(message = "Le sprint ne peut pas commencer dans le passé") LocalDateTime dateDebut,

                @NotNull(message = "La date de fin est obligatoire") LocalDateTime dateFin,

                String objectif,

                @NotNull(message = "L'ID du projet est obligatoire") Long projectId) {
}
