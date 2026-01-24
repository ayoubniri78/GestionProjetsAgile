package com.ensa_agadir.agile_app.product.dtos.resp;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EpicResponseDTO(
        Integer id,
        String titre,
        String description,
        Integer valeurMetier,
        LocalDate dateCreation,
        Integer productBacklogId,
        Boolean isEpicGenerale,
        Integer nombreUserStories
) {
}
