package edu.eci.dosw.TechCup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.eci.dosw.TechCup.model.AuthRequest;
import edu.eci.dosw.TechCup.model.IdentificationType;
import edu.eci.dosw.TechCup.model.UserState;
import edu.eci.dosw.TechCup.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;


    @Test
    void shouldRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("123");

        //when(authenticationService.register(user)).thenReturn(user);
        when(authenticationService.register(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@mail.com"));
    }


    @Test
    void shouldLoginUser() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@mail.com");
        request.setPassword("123");

        Authentication authentication = mock(Authentication.class);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("test@mail.com")
                .password("123")
                .authorities("ROLE_JUGADOR")
                .build();

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authentication);

        when(userDetailsService.loadUserByUsername("test@mail.com"))
                .thenReturn(userDetails);

        when(jwtService.generateToken(userDetails))
                .thenReturn("fake-jwt-token");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"));
    }
}
