package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.product.models.Projet;
import com.ensa_agadir.agile_app.product.repository.ProjetRepository;
import com.ensa_agadir.agile_app.user.dtos.request.MembreProjetReqDTO;
import com.ensa_agadir.agile_app.user.dtos.response.MembreProjetRespDTO;
import com.ensa_agadir.agile_app.user.mapper.MembreProjetMapper;
import com.ensa_agadir.agile_app.user.models.MembreProjet;
import com.ensa_agadir.agile_app.user.models.UserRoleProjectId;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import com.ensa_agadir.agile_app.user.repository.MembreProjetRepository;
import com.ensa_agadir.agile_app.user.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembreProjetServiceImpl implements MembreProjetService {
    private final MembreProjetRepository membreProjetRepository;
    private final UserRepo userRepo;
    private final ProjetRepository projetRepository;
    private final MembreProjetMapper membreProjetMapper;

    public MembreProjetServiceImpl(MembreProjetRepository membreProjetRepository, UserRepo userRepo, ProjetRepository projetRepository, MembreProjetMapper membreProjetMapper) {
        this.membreProjetRepository = membreProjetRepository;
        this.userRepo = userRepo;
        this.projetRepository = projetRepository;
        this.membreProjetMapper = membreProjetMapper;
    }


    @Override
    public MembreProjetRespDTO creeMembreProjet(MembreProjetReqDTO dto) {
        Utilisateur user = userRepo.findById(dto.userId());


        Projet projet = projetRepository.findById(dto.projetId());

        MembreProjet membreProjet = membreProjetMapper.toEntity(dto);

        membreProjet.setId(new UserRoleProjectId(dto.userId(),dto.projetId()));
        membreProjet.setUtilisateur(user);
        membreProjet.setProjet(projet);

        MembreProjet membre = membreProjetRepository.save(membreProjet);
        return membreProjetMapper.toResponseDTO(membre);
    }
}
