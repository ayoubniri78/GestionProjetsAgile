package com.ensa_agadir.agile_app.product.dtos;

import jakarta.persistence.Column;

import java.sql.Date;

public record EpicDTO(
        String titre,
        String description,
        int valeurMetier,
        Date dateCreation,
        int product_backlog_id

) {
}
