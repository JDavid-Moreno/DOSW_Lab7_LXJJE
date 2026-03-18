package edu.eci.dosw.TechCup.model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Role role;
    private LocalDate birthDate;
    private Gender gender;
    private Integer semester;
    private IdentificationType identificationType;
    private String identification;
    private Program program;
    private Position predefinedPosition;
    private String predefinedDorsal;
    @OneToOne
    @JoinColumn(name = "profile_picture_id")
    private File profilePicture;
    private UserState state;
    public User() {}
    public User(String correo, String passwdCifrado, String nombres, String apellidos, LocalDate birthDate, Program program) {
        this.email = correo;
        this.password = passwdCifrado;
        this.name = nombres;
        this.lastName = apellidos;
        this.birthDate = birthDate;
        this.program = program;
        this.state = UserState.ACTIVE;
    }
    public void setPassword(String passwdCifrado) {
        this.password = passwdCifrado;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setSemester(Integer semester) {
            this.semester = semester;
    }
    public void setIdentifiacion(IdentificationType tipoId, String identificacion) {
        this.identificationType = tipoId;
        this.identification = identificacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public Gender getGender() {
        return gender;
    }
    public Integer getSemester() {
        return semester;
    }
    public IdentificationType getIdentificationType() {
        return identificationType;
    }
    public String getIdentification() {
        return identification;
    }
    public Program getProgram() {
        return program;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setProfilePicture(File profilePicture) {
        this.profilePicture = profilePicture;
    }
    public File getProfilePicture() {
        return profilePicture;
    }
    public Position getPredefinedPosition() {
        return predefinedPosition;
    }
    public void setPredefinedPosition(Position predefinedPosition) {
        this.predefinedPosition = predefinedPosition;
    }
    public String getPredefinedDorsal() {
        return predefinedDorsal;
    }
    public void setPredefinedDorsal(String predefinedDorsal) {
        this.predefinedDorsal = predefinedDorsal;
    }
    public UserState getState() {
        return state;
    }
    public void setState(UserState state) {
        this.state = state;
    }
}
