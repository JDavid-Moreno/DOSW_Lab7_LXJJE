package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    Optional<TeamEntity> findById(Long teamId);
    List<TeamEntity> findAllByName(String teamName);
}
