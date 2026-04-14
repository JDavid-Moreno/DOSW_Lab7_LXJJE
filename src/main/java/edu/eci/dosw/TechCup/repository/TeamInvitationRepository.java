package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.TeamInvitationEntity;
import edu.eci.dosw.TechCup.model.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamInvitationRepository extends JpaRepository<TeamInvitationEntity, Long> {
    List<TeamInvitationEntity> findAllByInvitedId(Long id);
    List<TeamInvitationEntity> findAllByInvitedIdAndStatus(Long invitedId, InvitationStatus status);
    List<TeamInvitationEntity> findAllByInvitedById(Long invitedById);
}
