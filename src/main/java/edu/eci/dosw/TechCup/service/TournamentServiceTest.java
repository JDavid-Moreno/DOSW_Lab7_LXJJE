package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("test")
public class TournamentServiceTest implements TournamentService {

    private Map<Long, Tournament> tournaments = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public Optional<Tournament> searchTournamentById(Long id) {
        return Optional.ofNullable(tournaments.get(id));
    }

    @Override
    public List<Team> searchTournamentTeams(Long id) {
        Tournament tournament = tournaments.get(id);
        if (tournament != null) {
            return tournament.getTeams();
        }
        return new ArrayList<>();
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        tournament.setId(idCounter++);
        tournaments.put(tournament.getId(), tournament);
        return tournament;
    }

    @Override
    public void deleteTournament(Long id) {
        tournaments.remove(id);
    }
}
