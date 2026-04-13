package edu.eci.dosw.TechCup.model;

import java.util.Set;

public class Role {
    // ORGANIZADOR, ADMINISTRADOR, ARBITRO, JUGADOR, PERSONAL_ADMINISTRATIVO
    private Long id;
    private String name;
    private Set<Permission> permissions;

    public Role() {}

    public Role(Long id, String name, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
