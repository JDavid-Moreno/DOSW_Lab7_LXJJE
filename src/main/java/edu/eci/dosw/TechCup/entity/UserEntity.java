package edu.eci.dosw.TechCup.entity;

import edu.eci.dosw.TechCup.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String password;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer semester;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdentificationType identificationType;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String identification;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Program program;
    @Enumerated(EnumType.STRING)
    private Position predefinedPosition;
    private String predefinedDorsal;
    @OneToOne
    @JoinColumn(name = "profile_picture_id", nullable = true)
    private FileEntity profilePicture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState state;
    public UserEntity() {}
    public UserEntity(String correo, String passwdCifrado, String nombres, String apellidos, LocalDate birthDate, Program program) {
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
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
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
    public void setProfilePicture(FileEntity profilePicture) {
        this.profilePicture = profilePicture;
    }
    public FileEntity getProfilePicture() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
