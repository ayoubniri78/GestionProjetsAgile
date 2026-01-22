package com.ensa_agadir.agile_app.product.mapper;

import com.ensa_agadir.agile_app.product.dtos.ProjetRequestDTO;
import com.ensa_agadir.agile_app.product.dtos.ProjetResponseDTO;
import com.ensa_agadir.agile_app.product.models.Projet;
import com.ensa_agadir.agile_app.user.models.RoleDansProjet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProjetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membreProjets", ignore = true)
    @Mapping(target = "productBacklog", ignore = true)
    Projet toEntity(ProjetRequestDTO dto);

    @Mapping(target = "productOwnerId", source = "projet", qualifiedByName = "extractProductOwnerId")
    @Mapping(target = "productOwnerName", source = "projet", qualifiedByName = "extractProductOwnerName")
    @Mapping(target = "productOwnerEmail", source = "projet", qualifiedByName = "extractProductOwnerEmail")
    ProjetResponseDTO toResponseDto(Projet projet);

    @Named("extractProductOwnerId")
    default Integer extractProductOwnerId(Projet projet) {
        if (projet.getMembreProjets() == null) return null;
        return projet.getMembreProjets().stream()
                .filter(m -> m.getRoleDansProjet() == RoleDansProjet.PRODUCT_OWNER)
                .findFirst()
                .map(m -> m.getUtilisateur().getId())
                .orElse(null);
    }

    @Named("extractProductOwnerName")
    default String extractProductOwnerName(Projet projet) {
        if (projet.getMembreProjets() == null) return null;
        return projet.getMembreProjets().stream()
                .filter(m -> m.getRoleDansProjet() == RoleDansProjet.PRODUCT_OWNER)
                .findFirst()
                .map(m -> m.getUtilisateur().getNom() + " " + m.getUtilisateur().getPrenom())
                .orElse(null);
    }

    @Named("extractProductOwnerEmail")
    default String extractProductOwnerEmail(Projet projet) {
        if (projet.getMembreProjets() == null) return null;
        return projet.getMembreProjets().stream()
                .filter(m -> m.getRoleDansProjet() == RoleDansProjet.PRODUCT_OWNER)
                .findFirst()
                .map(m -> m.getUtilisateur().getEmail())
                .orElse(null);
    }
}
