package com.ensa_agadir.agile_app.product.controllers;


import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.ProductBacklogResponseDTO;
import com.ensa_agadir.agile_app.product.service.ProductBackLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projets/{projectId}/backlog")
@RequiredArgsConstructor
public class ProductBacklogController {

    private final ProductBackLogService productBackLogService;

    @GetMapping
    public ResponseEntity<ProductBacklogResponseDTO> getBacklog(@PathVariable Integer projectId) {
        ProductBacklogResponseDTO response = productBackLogService.getBacklogByProjectId(projectId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductBacklogResponseDTO> createBacklog(
            @PathVariable Integer projectId,
            @RequestParam String backlogName) {
        ProductBacklogResponseDTO response = productBackLogService.createBacklogForProject(projectId, backlogName);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/epics")
    public ResponseEntity<?> createEpic(
            @PathVariable Integer projectId,
            @Valid @RequestBody EpicCreateDTO epicDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productBackLogService.createEpic(projectId, epicDTO));
    }

    @PostMapping("/user-stories")
    public ResponseEntity<?> addUserStory(
            @PathVariable Integer projectId,
            @Valid @RequestBody UserStoryCreateDTO usDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productBackLogService.addUserStory(projectId, usDTO));
    }
}
