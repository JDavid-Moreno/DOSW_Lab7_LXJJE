package edu.eci.dosw.TechCup.security;

import com.jayway.jsonpath.JsonPath;
import edu.eci.dosw.TechCup.mapper.RoleMapper;
import edu.eci.dosw.TechCup.service.JwtService;
import edu.eci.dosw.TechCup.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("prod")
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleMapper roleMapper;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

@BeforeEach
    void setup() throws Exception {
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername("test@test.com")
                .password("123456")
                .authorities("ROLE_USER")
                .build();

        when(userDetailsService.loadUserByUsername(any())).thenReturn(user);
        when(jwtService.generateToken(any())).thenReturn("fake-jwt-token");
        when(jwtService.extractUsername(anyString())).thenReturn("test@test.com");
        when(authenticationManager.authenticate(
                argThat(auth -> "wrong".equals(auth.getCredentials()))))
                .thenThrow(new BadCredentialsException("Bad credentials"));
        when(userService.getAllUsers()).thenReturn(List.of());
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "email": "test@test.com",
                            "password": "123456"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void loginFail() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "email": "test@test.com",
                            "password": "wrong"
                        }
                        """))
                .andExpect(status().isForbidden());
    }

    @Test
    void accessProtectedSuccess() throws Exception {
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "email": "test@test.com",
                            "password": "123456"
                        }
                        """))
                .andReturn();

        String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");

        mockMvc.perform(get("/users")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

}