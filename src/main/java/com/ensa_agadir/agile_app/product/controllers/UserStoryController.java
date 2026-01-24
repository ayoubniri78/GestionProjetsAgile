package com.ensa_agadir.agile_app.product.controllers;

import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;
import com.ensa_agadir.agile_app.product.service.UserStoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-stories")
@RequiredArgsConstructor
public class UserStoryController {

    private final UserStoryService userStoryService;

    @PostMapping("/cree-user-story")
    public ResponseEntity<UserStoryResponseDTO> createUserStory(
            @RequestParam(required = false) Integer epicId,
            @Valid @RequestBody UserStoryCreateDTO usDTO) {
        UserStoryResponseDTO response = userStoryService.createUserStory(epicId, usDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{userStoryId}/assign-to-epic/{epicId}")
    public ResponseEntity<Void> assignToEpic(
            @PathVariable Integer userStoryId,
            @PathVariable Integer epicId) {
        userStoryService.assignToEpic(userStoryId, epicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Integer id) {
        userStoryService.deleteUserStory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}