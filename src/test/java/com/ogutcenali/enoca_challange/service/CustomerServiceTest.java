package com.ogutcenali.enoca_challange.service;

import com.ogutcenali.enoca_challange.dto.request.CreateCustomerRequest;
import com.ogutcenali.enoca_challange.dto.response.CustomersResponse;
import com.ogutcenali.enoca_challange.mapper.ICustomerMapper;
import com.ogutcenali.enoca_challange.model.Customer;
import com.ogutcenali.enoca_challange.repository.CustomerRepository;
import com.ogutcenali.enoca_challange.exception.AuthException;
import com.ogutcenali.enoca_challange.exception.EErrorType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Collections;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ICustomerMapper customerMapper;

    public CustomerServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        // set createCustomerRequest attributes here

        customerService.createCustomer(createCustomerRequest);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testGetAllCustomer() {
        when(customerService.getAllCustomer()).thenReturn(Collections.emptyList());

        customerService.getAllCustomer();

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerResponse() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));
        when(customerMapper.fromCustomer(any(Customer.class))).thenReturn(new CustomersResponse());

        customerService.getCustomerResponse(id);

        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteCustomer() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));

        customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).delete(any(Customer.class));
    }

    @Test
    public void testUpdateCustomerAge() {
        Long id = 1L;
        int age = 30;
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));
        when(customerMapper.fromCustomer(any(Customer.class))).thenReturn(new CustomersResponse());

        customerService.updateCustomerAge(id, age);

        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomerName() {
        Long id = 1L;
        String name = "John";
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));
        when(customerMapper.fromCustomer(any(Customer.class))).thenReturn(new CustomersResponse());

        customerService.updateCustomerName(id, name);

        verify(customerRepository, times(1)).findById(id);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }



    @Test
    public void testFindCustomerById() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));

        customerService.findCustomerById(id);

        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    public void testFindCustomerByIdNotFound() {
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        try {
            customerService.findCustomerById(id);
        } catch (AuthException e) {
            assert(e.getErrorType() == EErrorType.CUSTOMER_NOT_FOUND);
        }

        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    public void testGetCustomersWithName() {
        String name = "John";
        when(customerRepository.findByNameContaining(name)).thenReturn(Collections.emptyList());

        customerService.getCustomersWithName(name);

        verify(customerRepository, times(1)).findByNameContaining(name);
    }
}
