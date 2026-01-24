package com.ensa_agadir.agile_app.user.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UserCreateRequestDTO(
        @NotNull
        String nom,
        String prenom,
        String email,
        String motDePasse
) {
}
