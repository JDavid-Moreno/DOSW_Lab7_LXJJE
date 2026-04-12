package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TournamentEntity;
import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Punto 4: Debe actualizar y eliminar un torneo")
    void shouldUpdateAndDeleteTournament() {
        // Preparar un organizador para el torneo (requerido en tu entidad)
        UserEntity organizer = new UserEntity("org@mail.com", "123", "Org", "Test", LocalDate.now(), Program.INGENIERIA_SISTEMAS);
        organizer.setRoles(new HashSet<>());
        organizer.setIdentifiacion(IdentificationType.CC, "5555");
        userRepository.save(organizer);

        // 1. Crear torneo inicial
        TournamentEntity tournament = new TournamentEntity();
        tournament.setName("Copa Inicial");
        tournament.setState(TournamentState.ACTIVO);
        tournament.setStartDate(LocalDate.now().plusDays(10));
        tournament.setEndDate(LocalDate.now().plusDays(20));
        tournament.setRegistrationClosingDate(LocalDate.now().plusDays(5));
        tournament.setNumberTeams(10);
        tournament.setCoast(100.0f);
        tournament.setOrganizer(organizer);
        
        TournamentEntity saved = tournamentRepository.save(tournament);

        // 2. ACTUALIZACIÓN: Cambiar nombre
        saved.setName("Copa Actualizada");
        tournamentRepository.save(saved);
        
        TournamentEntity updated = tournamentRepository.findById(saved.getId()).get();
        assertEquals("Copa Actualizada", updated.getName());

        // 3. ELIMINACIÓN
        tournamentRepository.delete(updated);
        Optional<TournamentEntity> deleted = tournamentRepository.findById(saved.getId());
        
        assertFalse(deleted.isPresent());
    }
}