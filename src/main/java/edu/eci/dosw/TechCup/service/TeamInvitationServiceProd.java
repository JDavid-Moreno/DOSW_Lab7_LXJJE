package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import edu.eci.dosw.TechCup.mapper.UserMapper;
import edu.eci.dosw.TechCup.model.*;
import edu.eci.dosw.TechCup.repository.TeamMemberRepository;
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

import java.util.Optional;


@Service
public class TeamInvitationServiceProd implements TeamInvitationService {
    private TeamInvitationRepository teamInvitationRepository;
    private TeamInvitationMapper teamInvitationMapper;
    private UserRepository userRepository;
    private TeamMemberRepository teamMemberRepository;

    public TeamInvitationServiceProd(TeamInvitationRepository teamInvitationRepository, TeamInvitationMapper teamInvitationMapper, UserRepository userRepository, TeamMemberRepository teamMemberRepository) {
        this.teamInvitationRepository = teamInvitationRepository;
        this.teamInvitationMapper = teamInvitationMapper;
        this.userRepository = userRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Transactional
    public TeamInvitation inviteToTeam(Long id, Long invitedId) {
        Optional<UserEntity> user = userRepository.findById(invitedId);
        if (user.isEmpty()) {
            throw new TeamInvitationException(TeamInvitationException.USER_NOT_FOUND);
        }
        Optional<TeamMemberEntity> captain = teamMemberRepository.findById(id);
        if (captain.isEmpty()) {
            throw new TeamInvitationException(TeamInvitationException.INVITED_BY_NOT_FOUND);
        } else if (!captain.get().isCaptain()) {
            throw new TeamInvitationException(TeamInvitationException.INVITED_BY_MUST_BE_CAPTAIN);
        }
        TeamInvitationEntity entity = new TeamInvitationEntity();
        entity.setInvited(user.get());
        entity.setInvitedBy(captain.get());
        entity.setStatus(InvitationStatus.INVITED);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(entity));
    }

    @Transactional
    public TeamInvitation acceptInvitation(Long id) {
        Optional<TeamInvitationEntity> entity = teamInvitationRepository.findById(id);
        if (entity.isEmpty()) {
            throw new TeamInvitationException(TeamInvitationException.TEAM_INVITATION_NOT_FOUND);
        }
        if (entity.get().getStatus() != InvitationStatus.INVITED) {
            throw new TeamInvitationException(TeamInvitationException.INVITATION_MUST_BE_PENDING);
        }
        TeamInvitationEntity teamInvitationEntity = entity.get();
        teamInvitationEntity.setStatus(InvitationStatus.ACCEPTED);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(teamInvitationEntity));
    }

    @Transactional
    public TeamInvitation rejectInvitation(Long id) {
        Optional<TeamInvitationEntity> entity = teamInvitationRepository.findById(id);
        if (entity.isEmpty()) {
            throw new TeamInvitationException(TeamInvitationException.TEAM_INVITATION_NOT_FOUND);
        }
        if (entity.get().getStatus() != InvitationStatus.INVITED) {
            throw new TeamInvitationException(TeamInvitationException.INVITATION_MUST_BE_PENDING);
        }
        TeamInvitationEntity teamInvitationEntity = entity.get();
        teamInvitationEntity.setStatus(InvitationStatus.DECLINED);
        return teamInvitationMapper.toModel(teamInvitationRepository.save(teamInvitationEntity));
    }
}
