package com.ensa_agadir.agile_app.user.dtos.response;

public record AuthResponseDTO(
        String token,
        UserResponseDTO utilisateur
) {
}
