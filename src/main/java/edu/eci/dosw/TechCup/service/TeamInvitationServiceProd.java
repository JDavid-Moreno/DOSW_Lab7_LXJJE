package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.TeamInvitation;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.repository.TeamRepository;
import edu.eci.dosw.TechCup.repository.TeamInvitationRepository;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.entity.TeamInvitationEntity;
import edu.eci.dosw.TechCup.entity.TeamEntity;
import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.mapper.TeamInvitationMapper;
import org.springframework.stereotype.Service;
import edu.eci.dosw.TechCup.exception.TeamInvitationException;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TeamInvitationServiceProd implements TeamInvitationService {
    private TeamInvitationRepository teamInvitationRepository;
    private TeamInvitationMapper teamInvitationMapper;
    private UserRepository userRepository;

    public TeamInvitationServiceProd(TeamInvitationRepository teamInvitationRepository, TeamInvitationMapper teamInvitationMapper, UserRepository userRepository) {
        this.teamInvitationRepository = teamInvitationRepository;
        this.teamInvitationMapper = teamInvitationMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public TeamInvitation inviteTeam(Long id, Long invitedId) {
        TeamEntity team = teamInvitationRepository.findAllByInvitedId(invitedId)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.TEAM_NOT_FOUND));
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.USER_NOT_FOUND));
        if (user.getRole() == Team.Role.JUGADOR) {
            throw new TeamInvitationException("User is not a team member");
            }
        TeamInvitationEntity entity = new TeamInvitationEntity();
        entity.setInvited(user);
        entity.setInvitedBy(team);
        entity.setStatus(TeamInvitationEntity.InvitationStatus.PENDING);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(entity));
    }

    @Transactional
    public TeamInvitation acceptInvitation(Long id) {
        TeamInvitationEntity entity = teamInvitationRepository.findById(id)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.INVITATION_NOT_FOUND));
        if (entity.getStatus() != TeamInvitationEntity.InvitationStatus.PENDING) {
            throw new TeamInvitationException("Invitation is not pending");
        }
        entity.setStatus(TeamInvitationEntity.InvitationStatus.ACCEPTED);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(entity));
    }

    @Transactional
    public TeamInvitation rejectInvitation(Long id) {
        TeamInvitationEntity entity = teamInvitationRepository.findById(id)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.INVITATION_NOT_FOUND));
        if (entity.getStatus() != TeamInvitationEntity.InvitationStatus.PENDING) {
            throw new TeamInvitationException("Invitation is not pending");
        }
        entity.setStatus(TeamInvitationEntity.InvitationStatus.REJECTED);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(entity));
    }
}
