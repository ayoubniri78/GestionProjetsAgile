//package com.ensa_agadir.agile_app.projectmanagement.models;
//
////import com.ensa_agadir.agile_app.productmanagement.models.UserStory;
//import com.ensa_agadir.agile_app.shared.BaseEntity;
//import com.ensa_agadir.agile_app.shared.DurationConverter;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.Duration;
//
//@Entity
//@Table(name = "tasks")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Task extends BaseEntity {
//
//    @Column(nullable = false)
//    private String titre;
//    private String description;
//
//    @Enumerated(EnumType.STRING)
//    private TaskStatut statutTask;
//
//    @Convert(converter = DurationConverter.class)
//    @Column(name = "temps_estime")
//    private Duration tempsEstime;
//
//    @Convert(converter = DurationConverter.class)
//    @Column(name = "temps_reel")
//    private Duration tempsReel;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_story_id", nullable = false)
//    private UserStory userStory;
//
//}
