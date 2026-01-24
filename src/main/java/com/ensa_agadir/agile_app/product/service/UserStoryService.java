package com.ensa_agadir.agile_app.product.service;

import java.util.List;

import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;
import com.ensa_agadir.agile_app.product.models.PrioriteMoscow;
import com.ensa_agadir.agile_app.product.models.StatusUserStory;

public interface UserStoryService {
    UserStoryResponseDTO createUserStory(Integer epicId, UserStoryCreateDTO usDTO);
    void assignToEpic(Integer userStoryId, Integer epicId);
    void deleteUserStory(Integer userStoryId);
}
