package com.ensa_agadir.agile_app.user.dtos.response;

import java.util.List;

public record UserResponseDTO(
        String nom,
        String prenom,
        String email,
        List<MembreProjetRespDTO> projets
) {
}
