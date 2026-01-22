package com.ensa_agadir.agile_app.product.dtos;

import java.sql.Date;

public record UserStoryDTO(
        int id,
        String titre,
        String description,
        String prioriteMoscow,
        String statusUserStory,
        Date dateDeCreation,
        int epicId
) {
}
