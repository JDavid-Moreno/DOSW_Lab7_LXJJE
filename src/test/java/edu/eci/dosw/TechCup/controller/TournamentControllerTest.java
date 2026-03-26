package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.model.TournamentRegistration;
import edu.eci.dosw.TechCup.service.TournamentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentController controller;

    @Test
    void shouldReturnTournamentWhenExists() {
        Tournament tournament = new Tournament();
        when(tournamentService.searchTournamentById(1L))
                .thenReturn(Optional.of(tournament));

        ResponseEntity<Tournament> response = controller.get(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldReturn404WhenTournamentNotExists() {
        when(tournamentService.searchTournamentById(1L))
                .thenReturn(Optional.empty());

        ResponseEntity<Tournament> response = controller.get(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldReturnTeams() {
        List<TournamentRegistration> teams = List.of(new TournamentRegistration(), new TournamentRegistration());

        when(tournamentService.searchTournamentTeams(1L))
                .thenReturn(teams);

        ResponseEntity<List<TournamentRegistration>> response = controller.getTournamentTeams(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void shouldCreate() {
        Tournament tournament = new Tournament();

        when(tournamentService.createTournament(tournament))
                .thenReturn(tournament);

        ResponseEntity<Tournament> response = controller.create(tournament);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void shouldDelete() {
        doNothing().when(tournamentService).deleteTournament(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(tournamentService, times(1)).deleteTournament(1L);
    }
}