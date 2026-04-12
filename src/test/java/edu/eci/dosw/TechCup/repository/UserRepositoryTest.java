package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Punto 1: Debe guardar un usuario correctamente")
    void shouldSaveUser() {
        UserEntity user = new UserEntity("prueba@mail.escuelaing.edu.co", "pass123", "Juan", "Perez", LocalDate.now(), Program.INGENIERIA_SISTEMAS);
        user.setRoles(Role.JUGADOR);
        user.setIdentifiacion(IdentificationType.CC, "12345678");
        
        UserEntity savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("prueba@mail.escuelaing.edu.co", savedUser.getEmail());
    }

    @Test
    @DisplayName("Punto 2: Debe buscar un usuario por email (Consulta)")
    void shouldFindUserByEmail() {
        UserEntity user = new UserEntity("busqueda@mail.escuelaing.edu.co", "pass", "Ana", "Gomez", LocalDate.now(), Program.INGENIERIA_SISTEMAS);
        user.setRoles(Role.JUGADOR);
        user.setIdentifiacion(IdentificationType.CC, "987654");
        userRepository.save(user);

        Optional<UserEntity> found = userRepository.findByEmail("busqueda@mail.escuelaing.edu.co");

        assertTrue(found.isPresent());
        assertEquals("Ana", found.get().getName());
    }
}