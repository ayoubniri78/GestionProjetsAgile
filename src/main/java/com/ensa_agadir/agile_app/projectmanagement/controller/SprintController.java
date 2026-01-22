package com.ensa_agadir.agile_app.projectmanagement.controller;

import com.ensa_agadir.agile_app.projectmanagement.dto.SprintRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Autorise React (par défaut sur le port 3000)
public class SprintController {

    private final SprintService sprintService;

    @PostMapping
    public ResponseEntity<SprintResponseDTO> createSprint(@Valid @RequestBody SprintRequestDTO sprintRequestDTO) {
        SprintResponseDTO createdSprint = sprintService.createSprint(sprintRequestDTO);
        return new ResponseEntity<>(createdSprint, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<SprintResponseDTO>> getSprintsByProject(@PathVariable Long projectId) {
        List<SprintResponseDTO> sprints = sprintService.getSprintsByProject(projectId);
        return ResponseEntity.ok(sprints);
    }
}
