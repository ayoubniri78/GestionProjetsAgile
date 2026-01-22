package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetUpdateDTO;
import com.ensa_agadir.agile_app.product.mapper.ProjetMapper;
import com.ensa_agadir.agile_app.product.models.Projet;
import com.ensa_agadir.agile_app.product.repository.ProjetRepository;
import com.ensa_agadir.agile_app.user.models.MembreProjet;
import com.ensa_agadir.agile_app.user.models.RoleDansProjet;
import com.ensa_agadir.agile_app.user.models.UserRoleProjectId;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import com.ensa_agadir.agile_app.user.repository.MembreProjetRepository;
import com.ensa_agadir.agile_app.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GestionProjetImpl implements GestionProjet {
    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;
    private final UserRepo userRepo;
    private final MembreProjetRepository membreProjetRepository;

    public GestionProjetImpl(ProjetRepository projetRepository, ProjetMapper projetMapper, UserRepo userRepo, MembreProjetRepository membreProjetRepository) {
        this.projetRepository = projetRepository;
        this.projetMapper = projetMapper;
        this.userRepo = userRepo;
        this.membreProjetRepository = membreProjetRepository;
    }


    @Override
    public ProjetResponseDTO CreeProjet(ProjetRequestDTO projetRequestDTO) {

        Projet projet = projetMapper.toEntity(projetRequestDTO);
        projetRepository.save(projet);

        Utilisateur createur = userRepo.findById(projetRequestDTO.createurId()); // a changer
        if (createur == null) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + projetRequestDTO.createurId());
        }
        ajouterCreateurCommeProductOwner(projet,createur);

        return projetMapper.toResponseDto(projet);
    }

    @Override
    public Optional<ProjetResponseDTO> getProjetById(Integer projetId) {
        return Optional.empty();
    }

    @Override
    public List<ProjetResponseDTO> rechercherProjets(String keyword) {
        return List.of();
    }

    @Override
    public List<ProjetResponseDTO> getProjetsByUtilisateur(Integer utilisateurId) {
        return List.of();
    }

    @Override
    public ProjetResponseDTO mettreAJourProjet(Integer projetId, ProjetUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public void archiverProjet(Integer projetId) {

    }

    @Override
    public void supprimerProjet(Integer projetId) {

    }

    @Override
    public boolean hasSprintActif(Integer projetId) {
        return false;
    }


    private void ajouterCreateurCommeProductOwner(Projet projet, Utilisateur createur) {
        MembreProjet membreProjet = MembreProjet.builder()
                .id(new UserRoleProjectId(createur.getId(), projet.getId()))
                .utilisateur(createur)
                .projet(projet)
                .roleDansProjet(RoleDansProjet.PRODUCT_OWNER)
                .dateAjout(new Date(System.currentTimeMillis()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
                .build();
        membreProjetRepository.save(membreProjet);
    }
}
