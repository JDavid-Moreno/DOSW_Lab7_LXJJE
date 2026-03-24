package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.Tournament;
import jakarta.persistence.*;

@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private TournamentEntity tournament;

    public TeamEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TournamentEntity getTournament() { return tournament; }
    public void setTournament(TournamentEntity tournament) { this.tournament = tournament; }
}
