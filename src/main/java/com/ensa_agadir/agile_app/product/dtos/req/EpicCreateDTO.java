package com.ensa_agadir.agile_app.product.dtos.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EpicCreateDTO(
        @NotBlank String titre,
        String description,
        Integer valeurMetier
) {
}
