package com.ensa_agadir.agile_app.user.dtos.request;

public record UserProfileUpdateDTO(
        String nom,
        String prenom,
        String email
) {
}
