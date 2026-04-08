package com.srping_mvc_object_mapper_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_object_mapper_practicle.controller.OrderController;
import com.srping_mvc_object_mapper_practicle.entity.Order;
import com.srping_mvc_object_mapper_practicle.service.OrderService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    // PLACE ORDER
    @Test
    void testPlaceOrder() throws Exception {

        Order order = new Order();
        order.setOrderId(1L); 

        when(service.placeOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content("""
                        {
                          "orderId": 1
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "orderId": 1
                        }
                        """));
    }

    //  GET ORDER
    @Test
    void testGetOrder() throws Exception {

        Order order = new Order();
        order.setOrderId(1L);   // ✅ FIX

        when(service.getById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "orderId": 1
                        }
                        """));
    }
}
