package com.ensa_agadir.agile_app.product.mapper;

import com.ensa_agadir.agile_app.product.dtos.req.EpicCreateDTO;
import com.ensa_agadir.agile_app.product.dtos.resp.EpicResponseDTO;
import com.ensa_agadir.agile_app.product.models.Epic;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EpicMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "isEpicGenerale", constant = "false")
    @Mapping(target = "productBacklog", ignore = true)
    @Mapping(target = "userStories", ignore = true)
    Epic toEntity(EpicCreateDTO dto);  // ✅ EpicCreateDTO

    @Mapping(target = "productBacklogId", source = "productBacklog.id")
    @Mapping(target = "nombreUserStories", expression = "java(epic.getUserStories() != null ? epic.getUserStories().size() : 0)")
    EpicResponseDTO toResponseDTO(Epic epic);  // ✅ EpicResponseDTO
}