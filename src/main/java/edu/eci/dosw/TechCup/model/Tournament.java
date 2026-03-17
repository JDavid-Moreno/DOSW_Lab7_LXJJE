package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private Long id;
    private String tournamentName;
    private TournamentState state;
    private File regulation;
    private LocalDate registrationClosingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberTeams;
    private Float coast;
    private User captain;
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
    public TournamentState getState() {
        return state;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
