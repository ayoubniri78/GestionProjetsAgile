package com.ensa_agadir.agile_app.user.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRoleProjectId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "projet_id")
    private Integer projetId;
}
