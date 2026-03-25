package edu.eci.dosw.TechCup.controller;

import edu.eci.dosw.TechCup.controller.UserController;
import edu.eci.dosw.TechCup.model.Role;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    @Test
    void shouldReturnUserWhenExists() {
        User user = new User();

        when(userService.searchUserById(1L))
                .thenReturn(Optional.of(user));

        ResponseEntity<User> response = controller.get(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldReturn404WhenUserNotExists() {
        when(userService.searchUserById(1L))
                .thenReturn(Optional.empty());

        ResponseEntity<User> response = controller.get(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void shouldUpdateUserRole() {
        Role role = Role.ADMINISTRADOR; 

        doNothing().when(userService).updateRole(1L, role);

        ResponseEntity<User> response = controller.updateRole(1L, role);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).updateRole(1L, role);
    }

    @Test
    void shouldUpdateUserState() {
        UserState state = UserState.ACTIVE; 

        doNothing().when(userService).updateState(1L, state);

        ResponseEntity<User> response = controller.updateState(1L, state);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).updateState(1L, state);
    }
} 
    

