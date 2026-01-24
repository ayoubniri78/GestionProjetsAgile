package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.ProductBacklogResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;
import com.ensa_agadir.agile_app.product.mapper.ProductBacklogMapper;
import com.ensa_agadir.agile_app.product.models.ProductBacklog;
import com.ensa_agadir.agile_app.product.models.Projet;
import com.ensa_agadir.agile_app.product.repository.ProjetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensa_agadir.agile_app.product.repository.ProductBacklogRepository;


@Service
@RequiredArgsConstructor
public class ProductBackLogImpl implements ProductBackLogService {

    private final ProductBacklogRepository backlogRepository;
    private final ProjetRepository projetRepository;
    private final ProductBacklogMapper backlogMapper;
    private final EpicService epicService;
    private final UserStoryService userStoryService;

    @Override
    @Transactional(readOnly = true)
    public ProductBacklogResponseDTO getBacklogByProjectId(Integer projectId) {
        return backlogRepository.findByProjetId(projectId)
                .map(backlogMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Backlog non trouvé pour projet: " + projectId));
    }

    @Override
    @Transactional
    public EpicResponseDTO createEpic(Integer projectId, EpicCreateDTO epicDTO) {

        verifierProjetEtBacklog(projectId);
        return epicService.createEpic(projectId, epicDTO);
    }

    @Override
    @Transactional
    public UserStoryResponseDTO addUserStory(Integer projectId, UserStoryCreateDTO usDTO) {
        verifierProjetEtBacklog(projectId);
        return userStoryService.createUserStory(null, usDTO);
    }

    @Override
    @Transactional
    public ProductBacklogResponseDTO createBacklogForProject(Integer projectId, String backlogName) {
        Projet projet = projetRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé: " + projectId));

        if (projet.getProductBacklog() != null) {
            throw new RuntimeException("Ce projet a déjà un backlog");
        }


        ProductBacklog backlog = ProductBacklog.builder()
                .nom(backlogName)
                .projet(projet)
                .build();

        ProductBacklog savedBacklog = backlogRepository.save(backlog);

        return backlogMapper.toResponseDTO(savedBacklog);
    }



    private void verifierProjetEtBacklog(Integer projectId) {

        projetRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé: " + projectId));


        backlogRepository.findByProjetId(projectId)
                .orElseThrow(() -> new RuntimeException("Projet sans backlog: " + projectId));
    }
}
