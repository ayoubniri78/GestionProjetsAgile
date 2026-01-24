package com.ensa_agadir.agile_app.product.controllers;

import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;
import com.ensa_agadir.agile_app.product.service.EpicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    @GetMapping("/{id}")
    public ResponseEntity<EpicResponseDTO> getEpic(@PathVariable Integer id) {
        EpicResponseDTO response = epicService.getEpicById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/user-stories")
    public ResponseEntity<EpicResponseDTO> addUserStoryToEpic(
            @PathVariable Integer id,
            @Valid @RequestBody UserStoryCreateDTO usDTO) {
        EpicResponseDTO response = epicService.addUserStoryToEpic(id, usDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Integer id) {
        epicService.deleteEpic(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
