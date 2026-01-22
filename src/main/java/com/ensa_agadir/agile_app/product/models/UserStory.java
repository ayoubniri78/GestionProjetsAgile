package com.ensa_agadir.agile_app.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titre;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private PrioriteMoscow prioriteMoscow;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusUserStory statusUserStory;

    @Column
    private LocalDate dateDeCreation;

    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    // reste task et sprint backlog (dans sprint back log relation faux c'est 1 to many pas many to many )
}
