package com.ensa_agadir.agile_app.projectmanagement.mapper;

import com.ensa_agadir.agile_app.projectmanagement.dto.TaskRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.TaskResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.models.Task;
import com.ensa_agadir.agile_app.projectmanagement.models.TaskStatut;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    // Entity -> DTO
    public TaskResponseDTO toDto(Task task) {
        if (task == null)
            return null;

        return new TaskResponseDTO(
                task.getId(),
                task.getTitre(),
                task.getDescription(),
                task.getStatutTask(),
                task.getTempsEstime(),
                task.getTempsReel(),
                task.getUserStoryId(),
                task.getAssigneeUserId());
    }

    // DTO -> Entity
    public Task toEntity(TaskRequestDTO dto) {
        if (dto == null)
            return null;

        return Task.builder()
                .titre(dto.titre())
                .description(dto.description())
                .statutTask(dto.statut() != null ? dto.statut() : TaskStatut.TO_DO)
                .tempsEstime(dto.tempsEstime())
                .userStoryId(dto.userStoryId())
                .assigneeUserId(dto.assigneeUserId())
                // Le SprintBacklog sera attaché dans le Service
                .build();
    }
}
