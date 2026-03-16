package edu.eci.dosw.TechCup.model;

import java.time.LocalDate;


public class User {
    private Long id;
    private String mail;
    private String passwdHash;
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
    private File profilePicture;
    public User() {}
    public User(String correo, String passwdCifrado, String nombres, String apellidos, Role rol, LocalDate birthDate, Program program) {
        this.mail = correo;
        this.passwdHash = passwdCifrado;
        this.name = nombres;
        this.lastName = apellidos;
        this.role = rol;
        this.birthDate = birthDate;
        this.program = program;
    }
    public void setPassword(String passwdCifrado) {
        this.passwdHash = passwdCifrado;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setSemester(Integer semester) {
        if (!role.equals(Role.ESTUDIANTE)) {
            this.semester = semester;
        } else {
            throw new IllegalArgumentException("Solo los estudiantes tienen semester.");
        }
    }
    public void setIdentifiacion(IdentificationType tipoId, String identificacion) {
        this.identificationType = tipoId;
        this.identification = identificacion;
    }

    public String getMail() {
        return mail;
    }
    public String getPasswdHash() {
        return passwdHash;
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
}
