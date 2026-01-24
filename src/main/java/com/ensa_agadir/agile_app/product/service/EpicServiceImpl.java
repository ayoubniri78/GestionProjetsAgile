package com.ensa_agadir.agile_app.product.service;

import com.ensa_agadir.agile_app.product.mapper.EpicMapper;
import com.ensa_agadir.agile_app.product.models.Epic;
import com.ensa_agadir.agile_app.product.repository.EpicRepository;
import com.ensa_agadir.agile_app.product.repository.ProductBacklogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final EpicMapper epicMapper;
    private final ProductBacklogRepository backlogRepository;
    private final UserStoryService userStoryService;

    @Override
    @Transactional
    public EpicResponseDTO createEpic(Integer projectId, EpicCreateDTO epicDTO) {
        var backlog = backlogRepository.findByProjetId(projectId)
                .orElseThrow(() -> new RuntimeException("Projet sans backlog: " + projectId));

        Epic epic = epicMapper.toEntity(epicDTO);
        epic.setProductBacklog(backlog);

        Epic saved = epicRepository.save(epic);
        return epicMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public EpicResponseDTO addUserStoryToEpic(Integer epicId, UserStoryCreateDTO usDTO) {
        var userStoryDTO = userStoryService.createUserStory(epicId, usDTO);
        return getEpicById(epicId);
    }

    @Override
    @Transactional(readOnly = true)
    public EpicResponseDTO getEpicById(Integer epicId) {
        return epicRepository.findById(epicId)
                .map(epicMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Epic non trouvée: " + epicId));
    }

    @Override
    @Transactional
    public void deleteEpic(Integer epicId) {
        epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic non trouvée: " + epicId));

        epicRepository.deleteById(epicId);
    }

}
