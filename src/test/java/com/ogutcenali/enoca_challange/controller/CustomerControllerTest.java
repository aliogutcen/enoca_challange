package com.ogutcenali.enoca_challange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogutcenali.enoca_challange.dto.request.CreateCustomerRequest;
import com.ogutcenali.enoca_challange.dto.response.CustomersResponse;
import com.ogutcenali.enoca_challange.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() throws Exception {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        // set createCustomerRequest attributes here

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCustomerRequest)))
                .andExpect(status().isCreated());

        verify(customerService, times(1)).createCustomer(any(CreateCustomerRequest.class));
    }

    @Test
    public void testGetAllCustomer() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    public void testGetCustomerInfo() throws Exception {
        Long id = 1L;
        when(customerService.getCustomerResponse(id)).thenReturn(new CustomersResponse());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/customers/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).getCustomerResponse(id);
    }

    @Test
    public void testGetCustomersWithoutOrders() throws Exception {
        when(customerService.getCustomersWithoutOrders()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/customers/without-orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).getCustomersWithoutOrders();
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/customers/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomer(id);
    }

    @Test
    public void testUpdateCustomerAge() throws Exception {
        Long id = 1L;
        int age = 30;
        when(customerService.updateCustomerAge(id, age)).thenReturn(new CustomersResponse());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/" + id + "/update-age")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(age)))
                .andExpect(status().isOk());

        verify(customerService, times(1)).updateCustomerAge(eq(id), eq(age));
    }

    @Test
    public void testUpdateCustomerName() throws Exception {
        Long id = 1L;
        String name = "John";
        when(customerService.updateCustomerName(id, name)).thenReturn(new CustomersResponse());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/customers/" + id + "/update-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(name)))
                .andExpect(status().isOk());

        verify(customerService, times(1)).updateCustomerName(eq(id), eq(name));
    }
}
