package com.ensa_agadir.agile_app.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom;

    @Column
    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "project_id",unique = true)
    private Projet projet;

    @OneToMany(mappedBy = "productBacklog")
    private List<Epic> epics;

}
