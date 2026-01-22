package com.ensa_agadir.agile_app.projectmanagement.dto;

import com.ensa_agadir.agile_app.projectmanagement.models.RoleSprint;
import jakarta.validation.constraints.NotNull;

public record AddMemberToSprintDTO(
        @NotNull(message = "L'ID de l'utilisateur est obligatoire") Long userId, // L'ID de l'utilisateur (Domaine 1)

        @NotNull(message = "Le rôle est obligatoire") RoleSprint role // Son rôle dans ce sprint (DEV, SCRUM_MASTER...)
) {
}
