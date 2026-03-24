package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.model.Tournament;
import jakarta.persistence.*;

@Entity
public class TournamentRegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private TournamentEntity tournament;

    public TournamentRegistrationEntity() {}
    public TournamentRegistrationEntity(Long id, TeamEntity team, TournamentEntity tournament) {
        this.id = id;
        this.team = team;
        this.tournament = tournament;
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
}
