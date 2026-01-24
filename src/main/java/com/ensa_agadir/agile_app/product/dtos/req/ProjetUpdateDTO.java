package com.ensa_agadir.agile_app.product.dtos.req;

import com.ensa_agadir.agile_app.product.models.StatusDeProjet;
import com.ensa_agadir.agile_app.product.models.VisibiliteEnum;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProjetUpdateDTO(
        String nomProjet,
        String description,
        LocalDate dateFinPrevue,
        VisibiliteEnum visibiliteDuProjet,
        StatusDeProjet statusDeProjet
) {
}
