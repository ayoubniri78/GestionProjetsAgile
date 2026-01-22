package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetUpdateDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GestionProjet {
    ProjetResponseDTO CreeProjet(ProjetRequestDTO projetRequestDTO);
    Optional<ProjetResponseDTO> getProjetById(Integer projetId);
    List<ProjetResponseDTO> rechercherProjets(String keyword);
    List<ProjetResponseDTO> getProjetsByUtilisateur(Integer utilisateurId);
    ProjetResponseDTO mettreAJourProjet(Integer projetId, ProjetUpdateDTO updateDTO);

    ProjetResponseDTO mettreAJourProjet(Integer projetId, ProjetUpdateDTO updateDTO);

    void archiverProjet(Integer projetId);
    void supprimerProjet(Integer projetId);
    boolean hasSprintActif(Integer projetId);
}
