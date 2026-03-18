package edu.eci.dosw.TechCup.model;

import jakarta.persistence.*;

@Entity
public class TournamentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
