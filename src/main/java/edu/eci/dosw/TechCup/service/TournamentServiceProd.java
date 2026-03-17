package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.repository.TournamentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Profile("prod")
public class TournamentServiceProd implements TournamentService {
    private TournamentRepository tournamentRepository;
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public TournamentServiceProd(TournamentRepository tournamentRepository) {
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
    public void createTournament(Tournament tournament) {
        tournament.setId(idGenerator.getAndIncrement());
        tournamentRepository.save(tournament);
    }
}
