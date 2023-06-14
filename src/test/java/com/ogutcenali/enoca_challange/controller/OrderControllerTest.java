package com.ogutcenali.enoca_challange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutcenali.enoca_challange.dto.request.CreateOrderRequest;
import com.ogutcenali.enoca_challange.dto.response.OrderResponse;
import com.ogutcenali.enoca_challange.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    public void testCreateOrder() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        // set createOrderRequest attributes here

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderRequest)))
                .andExpect(status().isCreated());

        verify(orderService, times(1)).createOrder(any(CreateOrderRequest.class));
    }

    @Test
    public void testGetAllOrder() throws Exception {
        when(orderService.getAllOrder()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getAllOrder();
    }

    @Test
    public void testGetOrder() throws Exception {
        Long id = 1L;
        when(orderService.getOrder(id)).thenReturn(new OrderResponse());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/orders/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getOrder(id);
    }

    @Test
    public void testGetOrdersCreatedAfter() throws Exception {
        LocalDate date = LocalDate.now();
        when(orderService.getOrdersCreatedAfter(date)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/orders/after/" + date)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getOrdersCreatedAfter(date);
    }

    @Test
    public void testGetCustomersAndOrderIdsByName() throws Exception {
        String name = "John";
        when(orderService.getCustomersAndOrderIdsByName(name)).thenReturn(new HashMap<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/orders/search/" + name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getCustomersAndOrderIdsByName(name);
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/orders/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteOrder(id);
    }
}
