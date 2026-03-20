package edu.eci.dosw.TechCup.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tournamentName;
    private TournamentState state;
    @OneToOne
    @JoinColumn(name = "regulation_id")
    private File regulation;
    private LocalDate registrationClosingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberTeams;
    private Float coast;
    @OneToOne
    @JoinColumn(name = "captain_id")
    private User captain;
    @OneToMany(mappedBy = "tournament")
    private List<Team> teams;
    public Tournament() {
        this.teams = new ArrayList<>();
    }
    public void setState(TournamentState state) {
        if (state == TournamentState.FINALIZADO) {
            throw new IllegalArgumentException("Tournament already finalized");
        }
        this.state = state;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public TournamentState getState() {
        return state;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
