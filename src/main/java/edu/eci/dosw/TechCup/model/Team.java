package edu.eci.dosw.TechCup.model;


import edu.eci.dosw.TechCup.entity.FileEntity;

import java.util.List;

public class Team {
    private Long id;
    private String name;
    private List<String> colors;
    private File shield;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public File getShield() {
        return shield;
    }

    public void setShield(File shield) {
        this.shield = shield;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

}
