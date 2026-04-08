package com.srping_mvc_library_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_library_practicle.configration.JWTUtils;
import com.srping_mvc_library_practicle.configration.JwtAuthenticationFilter;
import com.srping_mvc_library_practicle.controller.AuthController;
import com.srping_mvc_library_practicle.controller.AuthorController;
import com.srping_mvc_library_practicle.customRepository.UserCustomRepository;
import com.srping_mvc_library_practicle.entity.Users;
import com.srping_mvc_library_practicle.request.AuthRequest;
import com.srping_mvc_library_practicle.service.LoginAttemptService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@WebMvcTest(
	    controllers = AuthController.class,
	    excludeAutoConfiguration = {
	        org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration.class
	    }
	)
@AutoConfigureMockMvc(addFilters = false) // disable security filters
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTUtils jwtUtils;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private LoginAttemptService loService;

    @MockBean
    private UserCustomRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //  Login Success
    @Test
    void testLoginSuccess() throws Exception {

        AuthRequest request = new AuthRequest();
        request.setUserName("admin");
        request.setPassword("123");

        User user = new User("admin", "123", Collections.emptyList());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);

        Mockito.when(jwtUtils.generateToken(Mockito.any()))
                .thenReturn("dummy-token");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("dummy-token"));
    }

    //  Login Failure
    @Test
    void testLoginFailure() throws Exception {

        AuthRequest request = new AuthRequest();
        request.setUserName("admin");
        request.setPassword("wrong");

        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenThrow(new RuntimeException("Bad credentials"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Bad credentials"));
    }

    //  Create User
    @Test
    void testCreateUser() throws Exception {

        Users user = new Users();
        user.setUserName("test");

        Mockito.doNothing().when(userRepository).saveUser(Mockito.any());

        mockMvc.perform(post("/auth/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User Successfully created"));
    }

    //  Unlock Account
    @Test
    void testUnlockAccount() throws Exception {

        Users user = new Users();
        user.setUserName("admin");

        Mockito.when(userRepository.getByUserName("admin"))
                .thenReturn(Optional.of(user));

        Mockito.doNothing().when(userRepository).updateUser(Mockito.any());

        mockMvc.perform(put("/auth/unlock/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account unlocked for admin"));
    }

    //  Test API
    @Test
    void testSimpleEndpoint() throws Exception {

        mockMvc.perform(get("/auth/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("TEST WORKING"));
    }

    //  Home API
    @Test
    void testHome() throws Exception {

        mockMvc.perform(get("/auth/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Home Page"));
    }
}
