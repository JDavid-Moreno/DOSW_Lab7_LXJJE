package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teams")
@Tag(name = "Equipos", description = "Operaciones de administración de equipos")
public class TeamController {
    private TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID", description = "Retorna un equipo dado su ID")
    public ResponseEntity<Team> get(@PathVariable Long id) {
        Team team = teamService.findById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @GetMapping("/{name}")
    @Operation(summary = "Obtener equipo por nombre", description = "Retorna un equipo dado su nombre")
    public ResponseEntity<Team> get(@PathVariable String name) {
        Team team = teamService.findByName(name);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "Crear un equipo", description = "Se crea un equipo y se envia el equipo creado con codigo 201")
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un equipo", description = "actualiza el equipo del ID dado con los datos del nuevo equipo")
    public ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un equipo", description = "Elimina el equipo y se envia un responde entity vacio")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

}
