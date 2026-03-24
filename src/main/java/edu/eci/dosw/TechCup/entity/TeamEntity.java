package edu.eci.dosw.TechCup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ElementCollection
    @CollectionTable(
            name = "team_colors",
            joinColumns = @JoinColumn(name = "team_id")
    )
    @Column(name = "color", length = 7)
    @Size(min = 1, max = 3)
    private List<
            @Pattern(regexp = "^#[0-9A-Fa-f]{6}$")
                    String
            > colors;
    @OneToOne
    @JoinColumn(name = "shield_id")
    private FileEntity shield;
    public TeamEntity() {
    }
    public FileEntity getShield() {
        return shield;
    }
    public void setShield(FileEntity shield) {
        this.shield = shield;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
