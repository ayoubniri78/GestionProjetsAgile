package com.ensa_agadir.agile_app.user.dtos.request;

public record UserCreateRequestDTO(
        String nom,
        String prenom,
        String email,
        String motDePasse
) {
}
