package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.TournamentEntity;
import edu.eci.dosw.TechCup.exception.TournamentException;
import edu.eci.dosw.TechCup.mapper.TournamentMapper;
import edu.eci.dosw.TechCup.model.Tournament;
import edu.eci.dosw.TechCup.model.TournamentRegistration;
import edu.eci.dosw.TechCup.model.TournamentState;
import edu.eci.dosw.TechCup.repository.TournamentRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Profile("prod")
public class TournamentServiceProd implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    private static final Logger log =
            LoggerFactory.getLogger(TournamentServiceProd.class);

    public TournamentServiceProd(
            TournamentRepository tournamentRepository,
            TournamentMapper tournamentMapper) {

        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Transactional
    public Optional<Tournament> searchTournamentById(Long id) {

        log.info("Buscando torneo con id: {}", id);

        return tournamentRepository.findById(id)
                .map(tournamentMapper::toModel);
    }

    @Transactional
    public List<TournamentRegistration> searchTournamentTeams(Long id){

        Optional<TournamentEntity> tournamentEntity =
                tournamentRepository.findById(id);

        if (tournamentEntity.isPresent()) {

            return tournamentMapper
                    .toModel(tournamentEntity.get())
                    .getTeams();

        } else {

            throw new EntityNotFoundException(
                    "Tournament not found");
        }
    }

    @Transactional
    public Tournament createTournament(Tournament tournament) {

        tournament.setState(TournamentState.BORRADOR);

        TournamentEntity savedTournament =
                tournamentRepository.save(
                        tournamentMapper.toEntity(tournament));

        return tournamentMapper.toModel(savedTournament);
    }

    @Transactional
    public void deleteTournament(Long id) {

        Optional<TournamentEntity> tournamentEntity =
                tournamentRepository.findById(id);

        if (tournamentEntity.isPresent()
                && tournamentEntity.get()
                .getState()
                .equals(TournamentState.BORRADOR)) {

            tournamentRepository.deleteById(id);

        }
        else if (tournamentEntity.isEmpty()) {

            throw new EntityNotFoundException(
                    "Tournament not found");

        }
        else {

            throw new TournamentException(
                    "Tournament must be in state BORRADOR");

        }
    }
}