package com.ensa_agadir.agile_app.projectmanagement.service;

import com.ensa_agadir.agile_app.projectmanagement.dto.SprintRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.mapper.SprintMapper;
import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.SprintBacklog;
import com.ensa_agadir.agile_app.projectmanagement.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper; // Injection du Mapper

    @Transactional
    public SprintResponseDTO createSprint(SprintRequestDTO request) {
        // 1. Conversion DTO -> Entity via le Mapper
        Sprint sprint = sprintMapper.toEntity(request);

        // 2. Règle métier : Création automatique du SprintBacklog
        SprintBacklog backlog = new SprintBacklog();
        backlog.setSprint(sprint);
        sprint.setSprintBacklog(backlog);

        // 3. Sauvegarde (Cascade gère le backlog)
        Sprint savedSprint = sprintRepository.save(sprint);

        // 4. Conversion Entity -> DTO pour la réponse
        return sprintMapper.toDto(savedSprint);
    }

    public List<SprintResponseDTO> getSprintsByProject(Long projectId) {
        return sprintRepository.findByProjectId(projectId).stream()
                .map(sprintMapper::toDto) // Utilisation élégante du mapper
                .collect(Collectors.toList());
    }
}
