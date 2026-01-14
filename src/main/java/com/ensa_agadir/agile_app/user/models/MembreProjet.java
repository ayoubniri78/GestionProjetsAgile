package com.ensa_agadir.agile_app.user.models;

import com.ensa_agadir.agile_app.product.models.Projet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembreProjet {
    @EmbeddedId
    private UserRoleProjectId id;

   @ManyToOne
   @MapsId("userId")  // Mappe userId de UserRoleProjectId
   @JoinColumn(name = "user_id")
   private Utilisateur utilisateur;

   @ManyToOne
   @MapsId("projetId")
   @JoinColumn(name = "projet_id")
   private Projet projet;

}
