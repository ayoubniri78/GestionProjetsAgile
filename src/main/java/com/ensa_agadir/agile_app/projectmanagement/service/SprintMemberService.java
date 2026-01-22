package com.ensa_agadir.agile_app.projectmanagement.service;

import com.ensa_agadir.agile_app.projectmanagement.dto.AddMemberToSprintDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintMemberResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.mapper.SprintMemberMapper;
import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.SprintMember;
import com.ensa_agadir.agile_app.projectmanagement.repository.SprintMemberRepository;
import com.ensa_agadir.agile_app.projectmanagement.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SprintMemberService {

    private final SprintMemberRepository sprintMemberRepository;
    private final SprintRepository sprintRepository;
    private final SprintMemberMapper sprintMemberMapper;

    @Transactional
    public SprintMemberResponseDTO addMemberToSprint(Long sprintId, AddMemberToSprintDTO request) {
        // 1. Vérifier si le sprint existe
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint non trouvé"));

        // 2. Vérifier si le membre est déjà dans le sprint
        if (sprintMemberRepository.findBySprintIdAndUserId(sprintId, request.userId()).isPresent()) {
            throw new RuntimeException("L'utilisateur est déjà membre de ce sprint");
        }

        // 3. Créer le membre
        SprintMember member = SprintMember.builder()
                .userId(request.userId())
                .roleSprint(request.role())
                .sprint(sprint)
                .build();

        SprintMember savedMember = sprintMemberRepository.save(member);
        return sprintMemberMapper.toDto(savedMember);
    }

    public List<SprintMemberResponseDTO> getMembersBySprint(Long sprintId) {
        return sprintMemberRepository.findBySprintId(sprintId).stream()
                .map(sprintMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeMember(Long memberId) {
        if (!sprintMemberRepository.existsById(memberId)) {
            throw new RuntimeException("Membre non trouvé");
        }
        sprintMemberRepository.deleteById(memberId);
    }
}
