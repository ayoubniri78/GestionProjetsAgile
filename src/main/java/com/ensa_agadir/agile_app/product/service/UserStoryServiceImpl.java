package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;
import com.ensa_agadir.agile_app.product.mapper.UserStoryMapper;
import com.ensa_agadir.agile_app.product.models.Epic;
import com.ensa_agadir.agile_app.product.models.PrioriteMoscow;
import com.ensa_agadir.agile_app.product.models.StatusUserStory;
import com.ensa_agadir.agile_app.product.models.UserStory;
import com.ensa_agadir.agile_app.product.repository.EpicRepository;
import com.ensa_agadir.agile_app.product.repository.UserStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final UserStoryMapper userStoryMapper;
    private final EpicRepository epicRepository;

    @Override
    @Transactional
    public UserStoryResponseDTO createUserStory(Integer epicId, UserStoryCreateDTO usDTO) {
        var userStory = userStoryMapper.toEntity(usDTO);

        if (epicId != null) {
            var epic = epicRepository.findById(epicId)
                    .orElseThrow(() -> new RuntimeException("Epic non trouvée: " + epicId));
            userStory.setEpic(epic);
        }

        var saved = userStoryRepository.save(userStory);
        return userStoryMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public void assignToEpic(Integer userStoryId, Integer epicId) {
        var userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("UserStory non trouvée: " + userStoryId));

        var epic = epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic non trouvée: " + epicId));

        userStory.setEpic(epic);
        userStoryRepository.save(userStory);
    }

    @Override
    @Transactional
    public void deleteUserStory(Integer userStoryId) {
        userStoryRepository.deleteById(userStoryId);
    }
}
