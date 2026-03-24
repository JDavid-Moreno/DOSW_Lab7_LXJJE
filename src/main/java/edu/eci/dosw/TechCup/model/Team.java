package edu.eci.dosw.TechCup.model;


public class Team {
    private Long id;
    private Tournament tournament;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
}
