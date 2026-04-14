package edu.eci.dosw.TechCup.model;


import java.time.LocalDate;
import java.util.Set;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Set<Role> roles;
    private LocalDate birthDate;
    private Gender gender;
    private Integer semester;
    private IdentificationType identificationType;
    private String identification;
    private Program program;
    private Position predefinedPosition;
    private String predefinedDorsal;
    private File profilePicture;
    private UserState state;
    public User() {}
    public User(String correo, String passwdCifrado, String nombres, String apellidos, Set<Role> roles, LocalDate birthDate, Program program) {
        this.email = correo;
        this.password = passwdCifrado;
        this.name = nombres;
        this.lastName = apellidos;
        this.roles = roles;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setProgram(Program program) {
        this.program = program;
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
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public LocalDate getBirthDate() {return birthDate;}
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
