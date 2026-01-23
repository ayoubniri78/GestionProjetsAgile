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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titre;
    @Column
    private String description;

    @Column
    private int valeurMetier;

    @Column
    private LocalDate dateCreation;

    @Column
    private Boolean isEpicGenerale = false;

    @ManyToOne
    @JoinColumn(name = "product_backlog")
    private ProductBacklog productBacklog;

    @OneToMany(mappedBy = "epic")
    private List<UserStory> userStories;
}
