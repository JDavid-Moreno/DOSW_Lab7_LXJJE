package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentService {
    public Optional<Tournament> searchTournamentById(Long id);
    public Optional<List<Team>> searchTournamentTeams(Long id);
    public void createTournament(Tournament tournament);
}
