package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    private TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> get(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.searchTournamentById(id);
        return tournament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{id}/teams")
    public ResponseEntity<List<Team>> getTournamentTeams(@PathVariable Long id) {
        Optional<List<Team>> teams = tournamentService.searchTournamentTeams(id);
        return teams.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Void> createTournament(@RequestBody Tournament tournament) {
        tournamentService.createTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}
