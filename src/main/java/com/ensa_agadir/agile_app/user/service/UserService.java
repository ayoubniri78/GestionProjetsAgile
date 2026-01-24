package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.user.dtos.UtilisateurDTO;
import com.ensa_agadir.agile_app.user.dtos.request.*;
import com.ensa_agadir.agile_app.user.dtos.response.AuthResponseDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserProjectDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO creeUser(UserCreateRequestDTO dto);

    Optional<UtilisateurDTO> getUtilisateurById(Integer id);

    boolean emailExiste(String email);


    UtilisateurDTO mettreAJourProfil(Integer userId, UserProfileUpdateDTO profileDTO);


    AuthResponseDTO authentifier(String email, String motDePasse);


    void deconnecter(Integer userId);


    List<String> getRolesDansProjet(Integer userId, Integer projetId);


    void supprimerCompte(Integer userId);


}
