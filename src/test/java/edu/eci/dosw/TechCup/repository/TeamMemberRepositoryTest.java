package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.*;
import edu.eci.dosw.TechCup.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TeamMemberRepositoryTest {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndVerifyRelationship() {
        TeamEntity team = new TeamEntity();
        team.setName("Eagles");
        team = teamRepository.save(team);
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity());

        UserEntity user = new UserEntity("player@mail.escuelaing.edu.co", "pass", "John", "Doe", LocalDate.now(), Program.IA);
        user.setRoles(roles);
        user.setIdentificationType(IdentificationType.CC);
        user.setIdentification("12345");
        user = userRepository.save(user);

        TeamMemberEntity member = new TeamMemberEntity();
        member.setTeam(team);
        member.setUser(user);
        member.setDorsal(10);
        member.setCaptain(true);
        member.setPosition(Position.DELANTERO);
        
        TeamMemberEntity saved = teamMemberRepository.save(member);

        assertNotNull(saved.getId());
        assertEquals("Eagles", saved.getTeam().getName());
        assertEquals("player@mail.escuelaing.edu.co", saved.getUser().getEmail());
        assertTrue(saved.isCaptain());
    }
}