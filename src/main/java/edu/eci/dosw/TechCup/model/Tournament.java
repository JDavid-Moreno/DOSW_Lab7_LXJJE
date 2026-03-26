package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Tournament {
    private Long id;
    private String name;
    private TournamentState state;
    private File regulation;
    private LocalDate registrationClosingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberTeams;
    private Float coast;
    private User organizator;
    private List<Team> teams;
    public Tournament() {
        this.teams = new ArrayList<>();
    }
    public void setState(TournamentState state) {
        if (this.state == TournamentState.FINALIZADO) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getRegulation() {
        return regulation;
    }

    public void setRegulation(File regulation) {
        this.regulation = regulation;
    }

    public LocalDate getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getNumberTeams() {
        return numberTeams;
    }

    public void setNumberTeams(Integer numberTeams) {
        this.numberTeams = numberTeams;
    }

    public Float getCoast() {
        return coast;
    }

    public void setCoast(Float coast) {
        this.coast = coast;
    }

    public User getOrganizator() {
        return organizator;
    }

    public void setOrganizator(User organizator) {
        this.organizator = organizator;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
