package com.ensa_agadir.agile_app.projectmanagement.mapper;

import com.ensa_agadir.agile_app.projectmanagement.dto.AddMemberToSprintDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintMemberResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.models.SprintMember;
import org.springframework.stereotype.Component;

@Component
public class SprintMemberMapper {

    public SprintMemberResponseDTO toDto(SprintMember member) {
        if (member == null)
            return null;

        return new SprintMemberResponseDTO(
                member.getId(),
                member.getUserId(),
                member.getRoleSprint(),
                member.getSprint() != null ? member.getSprint().getId() : null);
    }
}
