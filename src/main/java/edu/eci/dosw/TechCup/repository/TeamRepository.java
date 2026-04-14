package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TeamEntity;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    Optional<TeamEntity> findById(Long teamId);
    List<TeamEntity> findAllByName(String teamName);
}
