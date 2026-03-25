package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;

public interface TeamService {
    Team findById(Long id);
    Team findByName(String name);
    Team createTeam(Team team);
    void deleteTeam(Long id);
    Team updateTeam(Long id, Team team);
    
}
