package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {
    Optional<TeamMemberEntity> findByTeamId(Long teamId);
    Optional<TeamMemberEntity> findByTeamIdAndIsCaptainTrue(Long teamId);
    Optional<TeamMemberEntity> findByTeamIdAndDorsal(Long teamId, Integer dorsal);
}
