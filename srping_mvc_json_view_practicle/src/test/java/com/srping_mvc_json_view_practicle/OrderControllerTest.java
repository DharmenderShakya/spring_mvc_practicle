package com.srping_mvc_json_view_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_json_view_practicle.controller.OrderController;
import com.srping_mvc_json_view_practicle.entity.Order;
import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.repository.UserRepository;
import com.srping_mvc_json_view_practicle.request.OrderRequest;
import com.srping_mvc_json_view_practicle.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrder()).thenReturn(Arrays.asList(new Order()));

        mockMvc.perform(get("/order/getAllOrder"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateOrder() throws Exception {

        //  Use DTO (NOT entity)
        OrderRequest request = new OrderRequest();
        request.setUserId(2L);
        request.setProduct("Laptop");
        request.setStatus("sold");
        request.setTotal(500.0);

        //  Mock response
        Order order = new Order();
        order.setProduct("Laptop");
        order.setStatus("sold");
        order.setTotal(500.0);

        when(orderService.create(Mockito.any())).thenReturn(order);

        mockMvc.perform(post("/order/createOrder")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
