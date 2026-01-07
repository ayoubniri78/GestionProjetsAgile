package com.ensa_agadir.agile_app.projectmanagement.models;

import com.ensa_agadir.agile_app.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projet extends BaseEntity {
    @Column(nullable = false)
    private String nom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String description;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SprintBacklog> sprintBacklogs = new ArrayList<>();

}
