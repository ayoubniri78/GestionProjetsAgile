package com.ensa_agadir.agile_app.user.dtos;

import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;

// Alias pour UserResponseDTO pour compatibilité avec l'interface
public record UtilisateurDTO(
        String nom,
        String prenom,
        String email
) {
    public static UtilisateurDTO from(UserResponseDTO response) {
        return new UtilisateurDTO(response.nom(), response.prenom(), response.email());
    }
}
