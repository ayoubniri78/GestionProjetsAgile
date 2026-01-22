package com.ensa_agadir.agile_app.projectmanagement.models;

import com.ensa_agadir.agile_app.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sprints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sprint extends BaseEntity {

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

    @Column(length = 500)
    private String objectif;

    @Enumerated(EnumType.STRING)
    private StatutSprint statutSprint;

    // REFERENCE EXTERNE : On stocke l'ID du projet (Domaine externe), pas l'objet
    @Column(name = "projet_id", nullable = false)
    private Long projectId;

    // Un Sprint a un seul SprintBacklog
    @OneToOne(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private SprintBacklog sprintBacklog;

    // Un Sprint a plusieurs membres
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<SprintMember> membres = new HashSet<>();
}
