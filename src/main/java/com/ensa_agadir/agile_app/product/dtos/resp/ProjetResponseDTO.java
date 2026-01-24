package com.ensa_agadir.agile_app.product.dtos.resp;

import com.ensa_agadir.agile_app.product.models.StatusDeProjet;
import com.ensa_agadir.agile_app.product.models.VisibiliteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record ProjetResponseDTO(
        Integer id,
        String nomProjet,
        String description,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateDebut,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateFinPrevue,

        String visibiliteDuProjet,
        String statusDeProjet

//        // Infos supplémentaires
//        Integer createurId,
//        String createurNom,

) {
}
