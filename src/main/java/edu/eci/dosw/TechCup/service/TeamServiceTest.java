package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TeamServiceTest implements TeamService{
    @Override
    public Team findById(Long id) {
        return null;
    }

    @Override
    public Team findByName(String name) {
        return null;
    }

    @Override
    public Team createTeam(Team team) {
        return null;
    }

    @Override
    public void deleteTeam(Long id) {

    }

    @Override
    public Team updateTeam(Long id, Team team) {
        return null;
    }
}
