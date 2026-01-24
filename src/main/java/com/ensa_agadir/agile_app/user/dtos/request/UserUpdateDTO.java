package com.ensa_agadir.agile_app.user.dtos.request;

public record UserUpdateDTO(
        String nom,
        String prenom,
        String email
) {
}
