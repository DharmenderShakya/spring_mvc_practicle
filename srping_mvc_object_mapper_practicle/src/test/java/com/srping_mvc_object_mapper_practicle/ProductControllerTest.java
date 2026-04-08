package com.srping_mvc_object_mapper_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_object_mapper_practicle.controller.ProductController;
import com.srping_mvc_object_mapper_practicle.entity.Product;
import com.srping_mvc_object_mapper_practicle.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    //  GET ALL
    @Test
    void testGetAllProducts() throws Exception {

        Product p = new Product();
        p.setProductId(1L); 
        p.setName("Test Product");

        when(service.getAll()).thenReturn(List.of(p));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1))   
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    //  GET BY ID
    @Test
    void testGetById() throws Exception {

        Product p = new Product();
        p.setProductId(1L); 
        p.setName("Test Product");

        when(service.getById(1L)).thenReturn(p);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    //  CREATE
    @Test
    void testCreateProduct() throws Exception {

        Product p = new Product();
        p.setProductId(1L);  
        p.setName("New Product");

        when(service.save(any(Product.class))).thenReturn(p);

        mockMvc.perform(post("/products")
                .contentType("application/json")
                .content("""
                        {
                          "name": "New Product"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))   
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    //  UPDATE
    @Test
    void testUpdateProduct() throws Exception {

        Product p = new Product();
        p.setProductId(1L);
        p.setName("Updated");

        when(service.update(eq(1L), any(Product.class))).thenReturn(p);

        mockMvc.perform(put("/products/1")
                .contentType("application/json")
                .content("""
                        {
                          "name": "Updated"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));

        verify(service).update(eq(1L), any(Product.class));
    }

    //  DELETE
    @Test
    void testDeleteProduct() throws Exception {

        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("\"Deleted Successfully\""));

        verify(service).delete(1L);
    }
}
