package com.ensa_agadir.agile_app.projectmanagement.controller;

import com.ensa_agadir.agile_app.projectmanagement.dto.AddMemberToSprintDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintMemberResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.service.SprintMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SprintMemberController {

    private final SprintMemberService sprintMemberService;

    @PostMapping("/{sprintId}/members")
    public ResponseEntity<SprintMemberResponseDTO> addMember(
            @PathVariable Long sprintId,
            @Valid @RequestBody AddMemberToSprintDTO request) {
        SprintMemberResponseDTO member = sprintMemberService.addMemberToSprint(sprintId, request);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping("/{sprintId}/members")
    public ResponseEntity<List<SprintMemberResponseDTO>> getMembers(@PathVariable Long sprintId) {
        List<SprintMemberResponseDTO> members = sprintMemberService.getMembersBySprint(sprintId);
        return ResponseEntity.ok(members);
    }

    @DeleteMapping("/{sprintId}/members/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long sprintId, @PathVariable Long memberId) {
        // Note: sprintId n'est pas strictement nécessaire si on a memberId,
        // mais c'est bien pour la structure de l'URL et validation potentielle
        sprintMemberService.removeMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
