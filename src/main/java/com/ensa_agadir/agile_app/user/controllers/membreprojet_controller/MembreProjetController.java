package com.ensa_agadir.agile_app.user.controllers.membreprojet_controller;
import com.ensa_agadir.agile_app.user.dtos.request.MembreProjetReqDTO;
import com.ensa_agadir.agile_app.user.dtos.response.MembreProjetRespDTO;
import com.ensa_agadir.agile_app.user.service.MembreProjetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/membres-projet")
public class MembreProjetController {
    private final MembreProjetService membreProjetService;

    public MembreProjetController(MembreProjetService membreProjetService) {
        this.membreProjetService = membreProjetService;
    }

    @PostMapping
    public MembreProjetRespDTO ajouterMembre(@RequestBody MembreProjetReqDTO dto) {
        return membreProjetService.creeMembreProjet(dto);
    }
}
