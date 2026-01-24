package com.ensa_agadir.agile_app.product.dtos.req;

import com.ensa_agadir.agile_app.product.models.PrioriteMoscow;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserStoryCreateDTO(
        @NotBlank String titre,
        String description,
        PrioriteMoscow prioriteMoscow
) {
}
