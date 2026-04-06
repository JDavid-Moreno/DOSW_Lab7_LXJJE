package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.model.TeamInvitation;

public interface TeamInvitationService {
    TeamInvitation inviteToTeam(Long id, Long invitedId);
    TeamInvitation acceptInvitation(Long id);
    TeamInvitation rejectInvitation(Long id);
}
