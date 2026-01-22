package com.ensa_agadir.agile_app.projectmanagement.models;

import com.ensa_agadir.agile_app.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sprint_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SprintMember extends BaseEntity {

    // REFERENCE EXTERNE : ID de l'Utilisateur (Domaine 1)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleSprint roleSprint; // Tu devras créer cet Enum si inexistant

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;
}
