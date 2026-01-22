package com.ensa_agadir.agile_app.projectmanagement.dto;

import com.ensa_agadir.agile_app.projectmanagement.models.TaskStatut;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Duration;

public record TaskRequestDTO(
                @NotBlank(message = "Le titre de la tâche est obligatoire") String titre,

                String description,

                TaskStatut statut,

                @NotNull(message = "Le temps estimé est requis") Duration tempsEstime, // Note: Duration peut nécessiter
                                                                                       // une gestion spécifique JSON,
                                                                                       // mais on garde simple pour
                                                                                       // l'instant

                @NotNull(message = "L'ID User Story est requis") Long userStoryId,

                Long assigneeUserId,

                @NotNull(message = "L'ID du sprint est requis") Long sprintId) {
}
