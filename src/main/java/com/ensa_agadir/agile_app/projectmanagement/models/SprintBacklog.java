//package com.ensa_agadir.agile_app.projectmanagement.models;
//
////import com.ensa_agadir.agile_app.productmanagement.models.UserStory;
//import com.ensa_agadir.agile_app.shared.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "sprint_backlogs")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class SprintBacklog extends BaseEntity {
//    @Column(nullable = false)
//    private String nom;
//
//    private LocalDateTime dateDebut;
//    private LocalDateTime dateFin;
//
//    @Enumerated(EnumType.STRING)
//    private StatutSprint statutSprint;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "projet_id", nullable = false)
//    private Projet projet;
//
////    @OneToMany(mappedBy = "sprintBacklog", cascade = CascadeType.ALL, orphanRemoval = true)
////    private List<UserStory> userStorys = new ArrayList<>();
//
//}
