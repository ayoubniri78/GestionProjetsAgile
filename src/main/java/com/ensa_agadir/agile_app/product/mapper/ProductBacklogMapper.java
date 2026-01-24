package com.ensa_agadir.agile_app.product.mapper;

import com.ensa_agadir.agile_app.product.dtos.resp.ProductBacklogResponseDTO;
import com.ensa_agadir.agile_app.product.models.ProductBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductBacklogMapper {

    @Mapping(target = "projetId", source = "projet.id")
    @Mapping(target = "projetNom", source = "projet.nomProjet")
    @Mapping(target = "nombreEpics", expression = "java(backlog.getEpics() != null ? backlog.getEpics().size() : 0)")
    ProductBacklogResponseDTO toResponseDTO(ProductBacklog backlog);  // ✅ ProductBacklogResponseDTO
}