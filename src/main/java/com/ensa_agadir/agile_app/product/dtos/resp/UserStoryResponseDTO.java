package com.ensa_agadir.agile_app.product.dtos.resp;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserStoryResponseDTO(
        Integer id,
        String titre,
        String description,
        String prioriteMoscow,
        String statusUserStory,
        LocalDate dateDeCreation,
        Integer epicId
) {
}
