package com.ensa_agadir.agile_app.product.service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ensa_agadir.agile_app.product.dtos.req.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.req.ProjetUpdateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.ProjetResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensa_agadir.agile_app.product.mapper.ProjetMapper;
import com.ensa_agadir.agile_app.product.models.Projet;
import com.ensa_agadir.agile_app.product.models.StatusDeProjet;
import com.ensa_agadir.agile_app.product.repository.ProjetRepository;
import com.ensa_agadir.agile_app.user.models.MembreProjet;
import com.ensa_agadir.agile_app.user.models.RoleDansProjet;
import com.ensa_agadir.agile_app.user.models.UserRoleProjectId;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import com.ensa_agadir.agile_app.user.repository.MembreProjetRepository;
import com.ensa_agadir.agile_app.user.repository.UserRepo;

@Service
public class GestionProjetImpl implements GestionProjet {
    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;
    private final UserRepo userRepo;
    private final MembreProjetRepository membreProjetRepository;
    private final ProductBackLogService productBackLogService;

    public GestionProjetImpl(ProjetRepository projetRepository, ProjetMapper projetMapper, UserRepo userRepo, MembreProjetRepository membreProjetRepository, ProductBackLogService productBackLogService) {
        this.projetRepository = projetRepository;
        this.projetMapper = projetMapper;
        this.userRepo = userRepo;
        this.membreProjetRepository = membreProjetRepository;
        this.productBackLogService = productBackLogService;
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
        productBackLogService.createBacklogForProject(projet.getId(), projet.getNomProjet()+"backlog");

        return projetMapper.toResponseDto(projet);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjetResponseDTO> getProjetById(Integer projetId) {
        return projetRepository.findById(projetId)
                .map(projetMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetResponseDTO> rechercherProjets(String keyword) {
        List<Projet> projets = (keyword == null || keyword.isBlank())
                ? projetRepository.findAll()
                : projetRepository.rechercherParMotCle(keyword);

        return projets.stream()
                .map(projetMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjetResponseDTO> getProjetsByUtilisateur(Integer utilisateurId) {
        return projetRepository.findByUtilisateurId(utilisateurId).stream()
                .map(projetMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public ProjetResponseDTO mettreAJourProjet(Integer projetId, ProjetUpdateDTO updateDTO) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé: " + projetId));

        projetMapper.updateEntity(updateDTO, projet);
        Projet updated = projetRepository.save(projet);

        return projetMapper.toResponseDto(updated);
    }

    @Override
    @Transactional
    public void archiverProjet(Integer projetId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé: " + projetId));

        projet.setStatusDeProjet(StatusDeProjet.ARCHIVE);
        projetRepository.save(projet);
    }

    @Override
    @Transactional
    public void supprimerProjet(Integer projetId) {
        projetRepository.deleteById(projetId);
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
