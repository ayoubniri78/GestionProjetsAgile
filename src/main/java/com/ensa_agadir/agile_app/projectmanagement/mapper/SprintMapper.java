package com.ensa_agadir.agile_app.projectmanagement.mapper;

import com.ensa_agadir.agile_app.projectmanagement.dto.SprintRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.StatutSprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    // Entity -> DTO (Pour l'affichage)
    public SprintResponseDTO toDto(Sprint sprint) {
        if (sprint == null)
            return null;

        return new SprintResponseDTO(
                sprint.getId(),
                sprint.getNom(),
                sprint.getDateDebut(),
                sprint.getDateFin(),
                sprint.getObjectif(),
                sprint.getStatutSprint(),
                sprint.getProjectId());
    }

    // DTO -> Entity (Pour la création/modification)
    public Sprint toEntity(SprintRequestDTO dto) {
        if (dto == null)
            return null;

        return Sprint.builder()
                .nom(dto.nom())
                .dateDebut(dto.dateDebut())
                .dateFin(dto.dateFin())
                .objectif(dto.objectif())
                .projectId(dto.projectId())
                .statutSprint(StatutSprint.PLANIFIE) // Valeur par défaut
                .build();
    }
}
