package com.srping_mvc_library_practicle;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_library_practicle.configration.JWTUtils;
import com.srping_mvc_library_practicle.configration.JwtAuthenticationFilter;
import com.srping_mvc_library_practicle.controller.AuthorController;
import com.srping_mvc_library_practicle.entity.Author;
import com.srping_mvc_library_practicle.service.AutherService;

@WebMvcTest(
	    controllers = AuthorController.class,
	    excludeAutoConfiguration = {
	        org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration.class
	    }
	)
@AutoConfigureMockMvc(addFilters = false)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutherService authorService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private JWTUtils jwtUtils;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testGetAllAuthors() throws Exception {

        Author author = new Author();
        author.setId(1L);
        author.setName("John");

        Page<Author> page = new PageImpl<>(List.of(author));

        when(authorService.getAllAuthors(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John"));
    }

    @Test
    void testGetAuthorById() throws Exception {

        Author author = new Author();
        author.setId(1L);
        author.setName("Martin");

        when(authorService.getAuthorById(1L)).thenReturn(author);

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Martin"));
    }

    @Test
    void testCreateAuthor() throws Exception {

        Author author = new Author();
        author.setId(1L);
        author.setName("Robert");

        when(authorService.createAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/api/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Robert"));
    }

    @Test
    void testUpdateAuthor() throws Exception {

        Author author = new Author();
        author.setId(1L);
        author.setName("Updated Author");

        when(authorService.updateAuthor(eq(1L), any(Author.class))).thenReturn(author);

        mockMvc.perform(put("/api/authors/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Author"));
    }

    @Test
    void testDeleteAuthor() throws Exception {

        doNothing().when(authorService).deleteAuthor(1L);

        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().isOk());
    }
}