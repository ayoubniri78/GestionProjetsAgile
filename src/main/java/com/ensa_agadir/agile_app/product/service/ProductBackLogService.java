package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.ProductBacklogResponseDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;

import java.util.List;

public interface ProductBackLogService {
    ProductBacklogResponseDTO getBacklogByProjectId(Integer projectId);
    EpicResponseDTO createEpic(Integer projectId, EpicCreateDTO epicDTO);
    UserStoryResponseDTO addUserStory(Integer projectId, UserStoryCreateDTO usDTO);
    ProductBacklogResponseDTO createBacklogForProject(Integer projectId, String backlogName);
}
