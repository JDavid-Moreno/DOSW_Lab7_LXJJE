package edu.eci.dosw.TechCup.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.TechCup.model.User;
import edu.eci.dosw.TechCup.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

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
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("123");

        when(authenticationService.login("test@mail.com", "123")).thenReturn(user);
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@mail.com"));
    }
}
