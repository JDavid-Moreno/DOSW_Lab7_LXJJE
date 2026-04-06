package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.TournamentRegistration;
import edu.eci.dosw.TechCup.model.TournamentState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 50, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TournamentState state;
    @OneToOne
    @JoinColumn(name = "regulation_id")
    private FileEntity regulation;
    @Column(nullable = false)
    private LocalDate registrationClosingDate;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private Integer numberTeams;
    @Column(nullable = false)
    private Float coast;
    @OneToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private UserEntity organizer;
    @OneToMany(mappedBy = "tournament")
    private List<TournamentRegistrationEntity> teams;
    
    public TournamentEntity() {
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

    public List<TournamentRegistrationEntity> getTeams() {
        return teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileEntity getRegulation() {
        return regulation;
    }

    public void setRegulation(FileEntity regulation) {
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

    public UserEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserEntity organizer) {
        this.organizer = organizer;
    }

    public Float getCoast() {
        return coast;
    }

    public void setCoast(Float coast) {
        this.coast = coast;
    }

    public void setTeams(List<TournamentRegistrationEntity> teams) {
        this.teams = teams;
    }
}
