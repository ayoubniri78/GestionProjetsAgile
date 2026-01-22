package com.ensa_agadir.agile_app.product.dtos;

import com.ensa_agadir.agile_app.product.models.StatusDeProjet;
import com.ensa_agadir.agile_app.product.models.VisibiliteEnum;

import java.util.Date;

public record ProjetRequestDTO(
        String nomProjet,
        String description,
        Date dateDebut,
        Date dateFinPrevue,

        VisibiliteEnum visibiliteDuProjet,
        StatusDeProjet statusDeProjet,
        int createurId
) {
}
