package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.RegistrationStatus;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TournamentRegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentEntity tournament;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;
    private LocalDateTime registeredAt;

    public TournamentRegistrationEntity() {}
    public TournamentRegistrationEntity(Long id, TeamEntity team, TournamentEntity tournament) {
        this.id = id;
        this.team = team;
        this.tournament = tournament;
        this.status = RegistrationStatus.PENDIENTE;
        this.registeredAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
