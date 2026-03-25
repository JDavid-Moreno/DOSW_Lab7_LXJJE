package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {
    List<TournamentEntity> findAllByName(String name);
    List<TournamentEntity> findAllByStartDate(LocalDate startDate);
}
