package com.ensa_agadir.agile_app.product.models;

import com.ensa_agadir.agile_app.user.models.MembreProjet;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_projet")
    private String nomProjet;

    @Column(name = "description")
    private String description;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;


    @Enumerated(EnumType.STRING)
    @Column(name = "visibilite_du_projet")
    VisibiliteEnum visibiliteDuProjet;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_de_projet")
    StatusDeProjet statusDeProjet;

    @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER)
    List<MembreProjet> membreProjets;

    @OneToOne(mappedBy = "projet")
    ProductBacklog productBacklog;

    // reste relation avec sprint


}
