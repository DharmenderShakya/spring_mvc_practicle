package com.srping_mvc_json_view_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_json_view_practicle.controller.UserController;
import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(new User()));

        mockMvc.perform(get("/users/getAllUser"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetUserById() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(new User()));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setName("Dharmender");

        Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/users/createUser")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

//    @Test
//    void testDeleteUser() throws Exception {
//        mockMvc.perform(delete("/users/delete/2"))
//                .andExpect(status().isOk());
//
//        Mockito.verify(userService).deleteUser(1L);
//    }
}