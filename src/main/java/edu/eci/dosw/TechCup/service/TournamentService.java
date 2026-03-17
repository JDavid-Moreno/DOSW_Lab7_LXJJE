package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    @Transactional
    public Optional<Tournament> searchTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }
    @Transactional
    public Optional<List<Team>> searchTournamentTeams(Long id){
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return Optional.ofNullable(tournament.get().getTeams());
    }
    @Transactional
    public Tournament createTournament(Tournament tournament) {}
}
