package com.ensa_agadir.agile_app.projectmanagement.service;

import com.ensa_agadir.agile_app.projectmanagement.dto.SprintRequestDTO;
import com.ensa_agadir.agile_app.projectmanagement.dto.SprintResponseDTO;
import com.ensa_agadir.agile_app.projectmanagement.mapper.SprintMapper;
import com.ensa_agadir.agile_app.projectmanagement.models.Sprint;
import com.ensa_agadir.agile_app.projectmanagement.models.StatutSprint;
import com.ensa_agadir.agile_app.projectmanagement.repository.SprintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Active Mockito
class SprintServiceTest {

        @Mock
        private SprintRepository sprintRepository; // On simule la BDD

        @Mock
        private SprintMapper sprintMapper; // On simule le Mapper

        @InjectMocks
        private SprintService sprintService; // C'est lui qu'on teste

        @Test
        void shouldCreateSprintWithBacklog() {
                // 1. ARRANGE (Préparation des données)
                SprintRequestDTO request = new SprintRequestDTO(
                                "Sprint Test",
                                LocalDateTime.now(),
                                LocalDateTime.now().plusDays(14),
                                "Objectif Test",
                                100L);

                Sprint sprintEntity = Sprint.builder()
                                .nom("Sprint Test")
                                .build();
                sprintEntity.setId(1L);

                SprintResponseDTO expectedResponse = new SprintResponseDTO(
                                1L, "Sprint Test", null, null, "Objectif Test", StatutSprint.PLANIFIE, 100L);

                // On dit aux Mocks comment se comporter
                when(sprintMapper.toEntity(request)).thenReturn(sprintEntity);
                when(sprintRepository.save(any(Sprint.class))).thenReturn(sprintEntity);
                when(sprintMapper.toDto(sprintEntity)).thenReturn(expectedResponse);

                // 2. ACT (Exécution)
                SprintResponseDTO result = sprintService.createSprint(request);

                // 3. ASSERT (Vérification)
                assertNotNull(result);
                assertEquals("Sprint Test", result.nom());

                // La vérification CRUCIALE : Est-ce que le backlog a bien été créé ?
                assertNotNull(sprintEntity.getSprintBacklog(), "Le Backlog aurait dû être créé automatiquement !");

                // On vérifie que le repository a bien été appelé une fois
                verify(sprintRepository, times(1)).save(any(Sprint.class));
        }
}
