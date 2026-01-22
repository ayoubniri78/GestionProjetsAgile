package com.ensa_agadir.agile_app.product.dtos;

import java.util.Date;
import java.util.List;

public record ProductBacklogDTO(
        int id,
        String nom,
        Date dateCreation,

        // relation
        int projetId,

        // Epics (version légère)
        List<EpicDTO> epics
) {
}
