package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Profile("test")
public class TournamentServiceTest implements TournamentService {
    @Override
    public Optional<Tournament> searchTournamentById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Team> searchTournamentTeams(Long id) {
        return Optional.empty();
    }

    @Override
    public Tournament createTournament(Tournament tournament) {

    }

    @Override
    public void deleteTournament(Long id) {

    }
}
