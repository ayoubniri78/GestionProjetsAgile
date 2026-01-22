package com.ensa_agadir.agile_app.product.dtos;

import java.util.Date;

public record ProjetResponseDTO(
        Integer id,
        String nomProjet,
        String description,
        Date dateDebut,
        Date dateFinPrevue,

        String visibiliteDuProjet,
        String statusDeProjet,
        Integer productOwnerId,
        String productOwnerName,
        String productOwnerEmail
) {
}
