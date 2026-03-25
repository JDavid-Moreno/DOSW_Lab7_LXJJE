package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.TournamentEntity;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Profile("prod")
public class TournamentServiceProd implements TournamentService {
    private TournamentRepository tournamentRepository;
    private static final Logger log = LoggerFactory.getLogger(TournamentServiceProd.class);

    public TournamentServiceProd(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public Optional<Tournament> searchTournamentById(Long id) {
        log.info("Buscando torneo con id: {}", id);

        Optional<TournamentEntity> tournament = tournamentRepository.findById(id);

        if (tournament.isPresent()) {
            log.info("Torneo encontrado con id: {}", id);
        } else {
            log.warn("Torneo no encontrado con id: {}", id);
        }

        return tournament;
    }

    @Transactional
    public List<Team> searchTournamentTeams(Long id){
        log.info("Consultando equipos del torneo con id: {}", id);

        Optional<TournamentEntity> tournamentEntity = tournamentRepository.findById(id);

        if (tournamentEntity.isPresent()) {
            log.info("Equipos encontrados para el torneo con id: {}", id);
            return tournamentEntity.get().getTeams();
        }
        else {
            log.warn("No se encontraron equipos: torneo no existe con id {}", id);
            throw new EntityNotFoundException("Tournament not found");
        }
    }

    @Transactional
    public Tournament createTournament(Tournament tournament) {
        log.info("Creando torneo");

        tournament.setState(TournamentState.BORRADOR);
        TournamentEntity savedTournament = tournamentRepository.save(tournament);

        log.info("Torneo creado en estado BORRADOR con id: {}", savedTournament.getId());
        return savedTournament;
    }

    @Transactional
    public void deleteTournament(Long id) {
        log.info("Intentando eliminar torneo con id: {}", id);

        Optional<TournamentEntity> tournamentEntity = tournamentRepository.findById(id);

        if (tournamentEntity.isPresent() && tournamentEntity.get().getState().equals(TournamentState.BORRADOR)) {
            tournamentRepository.deleteById(id);
            log.info("Torneo eliminado correctamente con id: {}", id);
        }
        else if (tournamentEntity.isEmpty()) {
            log.warn("No se puede eliminar: torneo no encontrado con id {}", id);
            throw new EntityNotFoundException("Tournament not found");
        }
        else{
            log.warn("No se puede eliminar torneo con id {} porque no está en estado BORRADOR", id);
            throw new TournamentException("Tournament must be in state BORRADOR");
        }
    }
}
