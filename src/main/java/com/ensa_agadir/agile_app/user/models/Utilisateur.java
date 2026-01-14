package com.ensa_agadir.agile_app.user.models;

import com.ensa_agadir.agile_app.product.models.Projet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column
    private String email;

    @Column
    private String motDePasse;

    @Column
    private Date dateDeCreation;

    @OneToMany(mappedBy = "utilisateur")
    private List<MembreProjet> membreProjets;

}

