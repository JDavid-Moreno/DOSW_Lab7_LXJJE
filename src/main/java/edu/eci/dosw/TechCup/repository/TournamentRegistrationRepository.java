package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TournamentRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistrationEntity, Long> {
    List<TournamentRegistrationEntity> findAllByTeamId(Long teamId);
    List<TournamentRegistrationEntity> findAllByTournamentId(Long tournamentId);
}
