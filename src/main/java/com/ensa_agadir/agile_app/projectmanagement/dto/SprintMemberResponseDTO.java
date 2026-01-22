package com.ensa_agadir.agile_app.projectmanagement.dto;

import com.ensa_agadir.agile_app.projectmanagement.models.RoleSprint;

public record SprintMemberResponseDTO(
        Long id,
        Long userId,
        RoleSprint role,
        Long sprintId) {
}
