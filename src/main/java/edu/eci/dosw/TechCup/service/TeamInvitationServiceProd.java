package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.TeamEntity;
import edu.eci.dosw.TechCup.entity.TeamInvitationEntity;
import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.exception.TeamInvitationException;
import edu.eci.dosw.TechCup.mapper.TeamInvitationMapper;
import edu.eci.dosw.TechCup.model.InvitationStatus;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.TeamInvitation;
import edu.eci.dosw.TechCup.repository.TeamInvitationRepository;
import edu.eci.dosw.TechCup.repository.TeamMemberRepository;
import edu.eci.dosw.TechCup.repository.UserRepository;
import edu.eci.dosw.TechCup.service.TeamInvitationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TeamInvitationServiceProd implements TeamInvitationService {
    private final TeamInvitationRepository invitationRepository;
    private final TeamInvitationMapper mapper;
    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;


    public TeamInvitationServiceProd(TeamInvitationRepository invitationRepository,
                                     TeamInvitationMapper mapper, 
                                    UserRepository userRepository, 
                                    TeamMemberRepository teamMemberRepository) {
        this.invitationRepository = invitationRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    @Transactional
    public TeamInvitation inviteTeam(Long id, Long invitedId) {
        UserEntity invited = userRepository.findById(invitedId)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.USER_NOT_FOUND));
        TeamMemberEntity invitedBy = teamMemberRepository.findById(invitedId)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.TEAM_NOT_FOUND));
        if (invited.getRole() != Role.JUGADOR) {
            throw new TeamInvitationException(TeamInvitationException.ONLY_PLAYERS_INVITED);
        }

        TeamInvitationEntity entity = new TeamInvitationEntity();
        entity.setInvited(invited);
        entity.setInvitedBy(invitedBy);

        return mapper.toModel(invitationRepository.save(entity));
    }   

    @Override
    @Transactional
    public TeamInvitation acceptInvitation(Long id) {
        TeamInvitationEntity invitation = invitationRepository.findById(id)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.INVITATION_NOT_FOUND));
        return mapper.toModel(invitationRepository.save(invitation));
    }

    @Transactional
    public TeamInvitation rejectInvitation(Long id) {
        TeamInvitationEntity invitation = invitationRepository.findById(id)
            .orElseThrow(() -> new TeamInvitationException(TeamInvitationException.INVITATION_NOT_FOUND));
        
       return mapper.toModel(invitationRepository.save(invitation));
    }
}
