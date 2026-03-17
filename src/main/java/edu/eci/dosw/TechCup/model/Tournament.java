package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private Long id;
    private String tournamentName;
    private TournamentState tournamentState;
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

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
