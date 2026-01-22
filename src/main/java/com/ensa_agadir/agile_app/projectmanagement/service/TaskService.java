package com.ensa_agadir.agile_app.projectmanagement.service;

import com.ensa_agadir.agile_app.projectmanagement.dto.TaskRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.TaskResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.mapper.TaskMapper;
import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.Task;
import com.ensa_agadir.agile_app.projectmanagement.models.TaskStatut;
import com.ensa_agadir.agile_app.projectmanagement.repository.SprintRepository;
import com.ensa_agadir.agile_app.projectmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final TaskMapper taskMapper; // Injection du Mapper

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO request) {
        // 1. Vérifier l'existence du Sprint
        Sprint sprint = sprintRepository.findById(request.sprintId())
                .orElseThrow(() -> new RuntimeException("Sprint non trouvé avec l'ID : " + request.sprintId()));

        // 2. Créer la tâche via le Mapper (gère déjà le statut par défaut)
        Task task = taskMapper.toEntity(request);

        // 3. Associer manuellement le Backlog du Sprint trouvé
        task.setSprintBacklog(sprint.getSprintBacklog());

        // 4. Sauvegarder
        Task savedTask = taskRepository.save(task);

        return taskMapper.toDto(savedTask);
    }

    public List<TaskResponseDTO> getTasksBySprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint non trouvé"));

        return taskRepository.findBySprintBacklogId(sprint.getSprintBacklog().getId()).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskResponseDTO updateStatus(Long taskId, TaskStatut newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        task.setStatutTask(newStatus);

        // save() est implicite dans une transaction, mais on le garde pour être
        // explicite
        return taskMapper.toDto(taskRepository.save(task));
    }
}
