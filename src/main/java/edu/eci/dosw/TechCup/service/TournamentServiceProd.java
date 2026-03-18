package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.exception.TournamentException;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.model.TournamentState;
import edu.eci.dosw.TechCup.repository.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Profile("prod")
public class TournamentServiceProd implements TournamentService {
    private TournamentRepository tournamentRepository;

    public TournamentServiceProd(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    @Transactional
    public Optional<Tournament> searchTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }
    @Transactional
    public List<Team> searchTournamentTeams(Long id){
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        if (tournament.isPresent()) {
            return tournament.get().getTeams();
        }
        else
            throw new EntityNotFoundException("Tournament not found");
    }
    @Transactional
    public Tournament createTournament(Tournament tournament) {
        tournament.setState(TournamentState.BORRADOR);
        return tournamentRepository.save(tournament);
    }
    @Transactional
    public void deleteTournament(Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        if (tournament.isPresent() && tournament.get().getState().equals(TournamentState.BORRADOR)) {
            tournamentRepository.deleteById(id);
        }
        else if (tournament.isEmpty()) {
            throw new EntityNotFoundException("Tournament not found");
        }
        else{
            throw new TournamentException("Tournament must be in state BORRADOR");
        }
    }
}
