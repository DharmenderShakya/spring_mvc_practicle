package com.srping_mvc_library_practicle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_library_practicle.configration.JWTUtils;
import com.srping_mvc_library_practicle.configration.JwtAuthenticationFilter;
import com.srping_mvc_library_practicle.controller.BookController;
import com.srping_mvc_library_practicle.entity.Author;
import com.srping_mvc_library_practicle.entity.Book;
import com.srping_mvc_library_practicle.service.BookService;

@WebMvcTest(BookController.class)   // load only controller
@AutoConfigureMockMvc(addFilters = false)  // disable security
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;  // mock service

    @MockBean
    private JWTUtils jwtUtils;  // required because of security

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllBooks() throws Exception {

    	Book book = new Book();
    	book.setId(1L);
    	book.setTitle("java");
    	book.setIsbn(null);
    	book.setPrice(500.0);
        
    	 Page<Book> page = new PageImpl<>(List.of(book));
      
        Mockito.when(bookService.getAllBooks(Mockito.any()))
                .thenReturn(page);

        mockMvc.perform(get("/api/books?page=0&size=5"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetBookById() throws Exception {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring Boot");

        Mockito.when(bookService.getBook(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot"));
    }

    // Create Book
    @Test
    void testCreateBook() throws Exception {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Microservices");

        Mockito.when(bookService.createBook(Mockito.any(Book.class)))
                .thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Microservices"));
    }

    // Update Book
    @Test
    void testUpdateBook() throws Exception {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Book");

        Mockito.when(bookService.updateBook(Mockito.eq(1L), Mockito.any(Book.class)))
                .thenReturn(book);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    //   Delete Book
    @Test
    void testDeleteBook() throws Exception {

        Mockito.doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }
    
}
