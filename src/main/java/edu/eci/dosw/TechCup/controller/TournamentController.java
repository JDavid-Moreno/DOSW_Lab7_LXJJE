package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tournaments")
@Tag(name = "Torneos", description = "Operaciones CRUD para torneos")
public class TournamentController {
    private TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener torneo por ID", description = "Retorna un torneo dado su ID")
    public ResponseEntity<Tournament> get(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.searchTournamentById(id);
        return tournament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{id}/teams")
    @Operation(summary = "Obtener equipos del torneo", description = "Retorna los equipos asociados a un torneo")
    public ResponseEntity<List<Team>> getTournamentTeams(@PathVariable Long id) {
        List<Team> teams = tournamentService.searchTournamentTeams(id);
        return ResponseEntity.ok(teams);
    }
    @PostMapping
    @Operation(summary = "Crear torneo", description = "Crea un nuevo torneo en estado Borrador por defecto")
    public ResponseEntity<Tournament> create(@RequestBody Tournament tournament) {
        Tournament created = tournamentService.createTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar torneo", description = "Elimina un torneo solo si está en estado Borrador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}
