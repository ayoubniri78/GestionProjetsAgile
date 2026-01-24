package com.ensa_agadir.agile_app.user.dtos.request;

public record PasswordResetDTO(
        String token,
        String nouveauMotDePasse
) {
}
