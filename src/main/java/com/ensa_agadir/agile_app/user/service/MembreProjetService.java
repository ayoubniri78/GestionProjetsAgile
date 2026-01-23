package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.user.dtos.request.MembreProjetReqDTO;
import com.ensa_agadir.agile_app.user.dtos.response.MembreProjetRespDTO;

public interface MembreProjetService {
    MembreProjetRespDTO creeMembreProjet(MembreProjetReqDTO dto);
}
