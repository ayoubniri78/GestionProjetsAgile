package com.ensa_agadir.agile_app.product.controllers;


import com.ensa_agadir.agile_app.product.dtos.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetResponseDTO;
import com.ensa_agadir.agile_app.product.service.GestionProjet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {
    private final GestionProjet gestionProjet;

    public ProjetController(GestionProjet gestionProjet) {
        this.gestionProjet = gestionProjet;
    }

    @PostMapping("/add-project")
    public ProjetResponseDTO createProjet(@RequestBody ProjetRequestDTO dto) {
        return gestionProjet.CreeProjet(dto);
    }
}


