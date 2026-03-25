package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.FileEntity;
import edu.eci.dosw.TechCup.entity.TeamEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FileRepository fileRepository;

    @Test
    @DisplayName("Punto 3: Debe guardar un equipo con su escudo (Relación entre entidades)")
    void shouldSaveTeamWithShield() {
        // 1. Creamos y guardamos primero la entidad relacionada (File)
        FileEntity shieldFile = new FileEntity(new byte[]{1, 2, 3}, "image/png");
        FileEntity savedFile = fileRepository.save(shieldFile);

        // 2. Creamos el equipo y le asignamos el archivo
        TeamEntity team = new TeamEntity();
        team.setName("TechCup FC");
        team.setColors(List.of("#FF5733"));
        team.setShield(savedFile);

        TeamEntity savedTeam = teamRepository.save(team);

        // 3. Verificamos que la relación se mantenga
        assertNotNull(savedTeam.getShield());
        assertEquals(savedFile.getId(), savedTeam.getShield().getId());
        assertEquals("image/png", savedTeam.getShield().getMime());
    }
}