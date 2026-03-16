package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;

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
    public Tournament() {}
}
