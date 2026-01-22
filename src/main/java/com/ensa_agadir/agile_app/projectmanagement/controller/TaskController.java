package com.ensa_agadir.agile_app.projectmanagement.controller;

import com.ensa_agadir.agile_app.projectmanagement.dto.TaskRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.TaskResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.models.TaskStatut;
import com.ensa_agadir.agile_app.projectmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksBySprint(@PathVariable Long sprintId) {
        List<TaskResponseDTO> tasks = taskService.getTasksBySprint(sprintId);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatut status) {
        TaskResponseDTO updatedTask = taskService.updateStatus(taskId, status);
        return ResponseEntity.ok(updatedTask);
    }
}
