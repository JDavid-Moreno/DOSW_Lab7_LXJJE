package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {
    Optional<TeamMemberEntity> findByTeamId(Long teamId);
    Optional<TeamMemberEntity> findByTeamIdAndIsCaptainTrue(Long teamId);
    Optional<TeamMemberEntity> findByTeamIdAndDorsal(Long teamId, Integer dorsal);
}
