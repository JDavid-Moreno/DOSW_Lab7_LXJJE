package edu.eci.dosw.TechCup.model;

import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import edu.eci.dosw.TechCup.entity.UserEntity;

import java.time.LocalDateTime;

public class TeamInvitation {
    private Long id;
    private InvitationStatus status;
    private LocalDateTime date;
    private User invited;
    private TeamMember invitedBy;

    public User getInvited() {
        return invited;
    }

    public void setInvited(User invited) {
        this.invited = invited;
    }

    public TeamMember getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(TeamMember invitedBy) {
        this.invitedBy = invitedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
