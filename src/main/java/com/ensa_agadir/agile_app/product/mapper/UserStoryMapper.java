package com.ensa_agadir.agile_app.product.mapper;

import com.ensa_agadir.agile_app.product.dtos.req.UserStoryCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.UserStoryResponseDTO;
import com.ensa_agadir.agile_app.product.models.UserStory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statusUserStory", constant = "A_FAIRE")
    @Mapping(target = "dateDeCreation", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "epic", ignore = true)
    UserStory toEntity(UserStoryCreateDTO dto);  // ✅ UserStoryCreateDTO

    @Mapping(target = "epicId", source = "epic.id")
    UserStoryResponseDTO toResponseDTO(UserStory userStory);  // ✅ UserStoryResponseDTO
}