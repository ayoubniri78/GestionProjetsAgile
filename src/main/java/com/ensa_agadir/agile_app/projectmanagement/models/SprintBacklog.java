package com.ensa_agadir.agile_app.projectmanagement.models;

import com.ensa_agadir.agile_app.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sprint_backlogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintBacklog extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    @OneToMany(mappedBy = "sprintBacklog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();
}
