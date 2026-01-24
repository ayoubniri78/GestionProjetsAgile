//package com.ensa_agadir.agile_app.projectmanagement.models;
//
//import com.ensa_agadir.agile_app.product.models.ProductBacklog;
//import com.ensa_agadir.agile_app.shared.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//import org.apache.catalina.User;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "projets")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Projet extends BaseEntity {
//    @Column(nullable = false)
//    private String nom;
//    private LocalDateTime dateDebut;
//    private LocalDateTime dateFin;
//    private String description;
//
//    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SprintBacklog> sprintBacklogs = new ArrayList<>();
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_backlog_id", referencedColumnName = "id")
//    private ProductBacklog productBacklog;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "projets_membres",
//            joinColumns = @JoinColumn(name = "projet_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> membres = new HashSet<>();
//}
