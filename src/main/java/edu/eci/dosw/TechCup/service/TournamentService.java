package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentService {
    Optional<Tournament> searchTournamentById(Long id);
    List<Team> searchTournamentTeams(Long id);
    Tournament createTournament(Tournament tournament);
    void deleteTournament(Long id);
}
