package com.ensa_agadir.agile_app.user.mapper;

import com.ensa_agadir.agile_app.user.dtos.request.MembreProjetReqDTO;
import com.ensa_agadir.agile_app.user.dtos.response.MembreProjetRespDTO;
import com.ensa_agadir.agile_app.user.models.MembreProjet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MembreProjetMapper {
    @Mapping(target = "id" , ignore = true) // gerer manulment
    @Mapping(target = "utilisateur" , ignore = true)// a recuperer d'apres repo
    @Mapping(target = "projet", ignore = true)       // Récupéré via repository
    @Mapping(target = "dateAjout", expression = "java(java.time.LocalDate.now())")
    MembreProjet toEntity(MembreProjetReqDTO membreProjetReqDTO);


    @Mapping(target = "userId", source = "utilisateur.id")
    @Mapping(target = "userNom", source = "utilisateur.nom")
    @Mapping(target = "userPrenom", source = "utilisateur.prenom")
    @Mapping(target = "projetId", source = "projet.id")
    @Mapping(target = "projetNom", source = "projet.nomProjet")
    MembreProjetRespDTO toResponseDTO(MembreProjet membreProjet);



}
