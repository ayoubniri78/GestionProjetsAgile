package com.ensa_agadir.agile_app.user.dtos.request;

public record PasswordChangeDTO(
        String ancienMotDePasse,
        String nouveauMotDePasse
) {
}
