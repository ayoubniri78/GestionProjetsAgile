package com.ensa_agadir.agile_app.user.mapper;

import com.ensa_agadir.agile_app.user.dtos.request.UserCreateRequestDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "dateDeCreation", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "membreProjets", ignore = true) // gerer separement
    Utilisateur toEntity(UserCreateRequestDTO userCreateRequestDTO);

    @Mapping(target = "projets", source = "membreProjets")
    UserResponseDTO toResponseDTO(Utilisateur utilisateur);
}
