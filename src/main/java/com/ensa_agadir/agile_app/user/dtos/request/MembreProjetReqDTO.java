package com.ensa_agadir.agile_app.user.dtos.request;

import com.ensa_agadir.agile_app.user.models.RoleDansProjet;

public record MembreProjetReqDTO(
        int userId,
        int projetId,
        RoleDansProjet roleDansProjet
        ) {
}
