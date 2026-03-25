package edu.eci.dosw.TechCup.model;


import java.time.LocalDateTime;

public class TournamentRegistration {
    private Long id;
    private Team team;
    private Tournament tournament;
    private RegistrationStatus status;
    private LocalDateTime registeredAt;
}
