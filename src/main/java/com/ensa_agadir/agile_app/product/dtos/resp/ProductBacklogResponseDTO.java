package com.ensa_agadir.agile_app.product.dtos.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jshell.Snippet;

import java.time.LocalDate;

public record ProductBacklogResponseDTO(
        Integer id,
        String nom,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateCreation,

        // Référence au projet
        Integer projetId,
        String projetNom,

        // Métriques principales
        Integer nombreEpics,
        Integer nombreUserStories,
        Integer totalStoryPoints,
        Integer totalBusinessValue
) {

}
