package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.TeamInvitation;

public interface TeamInvitationService {
    TeamInvitation inviteTeam(Long id, Long invitedId);
    TeamInvitation acceptInvitation(Long id);
    TeamInvitation rejectInvitation(Long id);
}
