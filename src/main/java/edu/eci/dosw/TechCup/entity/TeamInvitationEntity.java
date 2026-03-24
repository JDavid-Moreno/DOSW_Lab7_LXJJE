package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.InvitationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class TeamInvitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvitationStatus status;
    @Column(nullable = false)
    private LocalDateTime date;
    @OneToOne
    @JoinColumn(name = "invited_id", nullable = false)
    private UserEntity invited;
    @ManyToOne
    @JoinColumn(name = "invited_by_id", nullable = false)
    private TeamMemberEntity invitedBy;

    public TeamInvitationEntity() {
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

    public UserEntity getInvited() {
        return invited;
    }

    public void setInvited(UserEntity invited) {
        this.invited = invited;
    }

    public TeamMemberEntity getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(TeamMemberEntity invitedBy) {
        this.invitedBy = invitedBy;
    }
}
