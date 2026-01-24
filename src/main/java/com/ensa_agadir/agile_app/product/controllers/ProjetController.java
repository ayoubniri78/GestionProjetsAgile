package com.ensa_agadir.agile_app.product.controllers;


import com.ensa_agadir.agile_app.product.dtos.req.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.req.ProjetUpdateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.ProjetResponseDTO;
import com.ensa_agadir.agile_app.product.service.GestionProjet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
public class ProjetController {
    private final GestionProjet gestionProjet;

    @PostMapping("cree-projet")
    public ResponseEntity<ProjetResponseDTO> createProject(@Valid @RequestBody ProjetRequestDTO requestDTO) {
        ProjetResponseDTO response = gestionProjet.CreeProjet(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponseDTO> getProjectById(@PathVariable Integer id) {
        return gestionProjet.getProjetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("all-project")
    public ResponseEntity<List<ProjetResponseDTO>> getAllProjects(
            @RequestParam(required = false) String keyword) {
        List<ProjetResponseDTO> projets = gestionProjet.rechercherProjets(keyword);
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<ProjetResponseDTO>> getProjectsByUser(@PathVariable Integer userId) {
        List<ProjetResponseDTO> projets = gestionProjet.getProjetsByUtilisateur(userId);
        return ResponseEntity.ok(projets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetResponseDTO> updateProject(
            @PathVariable Integer id,
            @Valid @RequestBody ProjetUpdateDTO updateDTO) {
        ProjetResponseDTO response = gestionProjet.mettreAJourProjet(id, updateDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/archiver")
    public ResponseEntity<Void> archiveProject(@PathVariable Integer id) {
        gestionProjet.archiverProjet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        gestionProjet.supprimerProjet(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


