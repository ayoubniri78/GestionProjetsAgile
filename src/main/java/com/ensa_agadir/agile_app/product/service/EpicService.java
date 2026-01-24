package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;

import java.util.List;

public interface EpicService {
    EpicResponseDTO createEpic(Integer projectId, EpicCreateDTO epicDTO);
    EpicResponseDTO addUserStoryToEpic(Integer epicId, UserStoryCreateDTO usDTO);
    EpicResponseDTO getEpicById(Integer epicId);
    void deleteEpic(Integer epicId);
}
