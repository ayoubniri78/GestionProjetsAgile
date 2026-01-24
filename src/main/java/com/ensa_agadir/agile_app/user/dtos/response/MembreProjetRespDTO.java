package com.ensa_agadir.agile_app.user.dtos.response;

import com.ensa_agadir.agile_app.user.models.RoleDansProjet;

import java.time.LocalDate;

public record MembreProjetRespDTO(
        int userId,
        String userNom,
        String userPrenom,
        int projetId,
        String projetNom,
        RoleDansProjet roleDansProjet,
        LocalDate dateAjout
) {
}
