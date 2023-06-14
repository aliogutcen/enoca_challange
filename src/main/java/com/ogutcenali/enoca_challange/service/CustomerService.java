package com.ogutcenali.enoca_challange.service;


import com.ogutcenali.enoca_challange.dto.request.CreateCustomerRequest;
import com.ogutcenali.enoca_challange.dto.request.UpdateAgeRequest;
import com.ogutcenali.enoca_challange.dto.request.UpdateNameRequest;
import com.ogutcenali.enoca_challange.dto.response.CustomersResponse;
import com.ogutcenali.enoca_challange.exception.AuthException;
import com.ogutcenali.enoca_challange.exception.EErrorType;
import com.ogutcenali.enoca_challange.mapper.ICustomerMapper;
import com.ogutcenali.enoca_challange.model.Customer;
import com.ogutcenali.enoca_challange.repository.CustomerRepository;
import com.ogutcenali.enoca_challange.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ServiceManager<Customer, Long> {

    private final CustomerRepository customerRepository;
    private final ICustomerMapper customerMapper = ICustomerMapper.INSTANCE;
    private final OrderService orderService;

    public CustomerService(CustomerRepository customerRepository, OrderService orderService) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = Customer.builder()
                .age(createCustomerRequest.getAge())
                .name(createCustomerRequest.getName())
                .build();
        save(customer);

    }

    public List<CustomersResponse> getAllCustomer() {
        return findAll().stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomersResponse getCustomerResponse(Long id) {
        Customer customer = findCustomerById(id);
        return customerMapper.fromCustomer(customer);
    }

    public void deleteCustomer(Long id) {
        Optional<Customer> customer = findById(id);

        verifyCustomerExists(customer);
        orderService.deleteOrderForCustomer(customer.get().getId());
        delete(customer.get());
    }

    public CustomersResponse updateCustomerAge(Long id, UpdateAgeRequest age) {
        Customer customer = findCustomerById(id);
        updateAge(customer, age.getAge());
        update(customer);
        return ICustomerMapper.INSTANCE.fromCustomer(customer);
    }

    private void updateAge(Customer customer, int age) {
        customer.setAge(age);
    }

    public CustomersResponse updateCustomerName(Long id, UpdateNameRequest name) {
        Customer customer = findCustomerById(id);
        updateName(customer, name.getName());
        update(customer);
        return ICustomerMapper.INSTANCE.fromCustomer(customer);
    }

    private void updateName(Customer customer, String name) {
        customer.setName(name);
    }

    public List<CustomersResponse> getCustomersWithoutOrders() {
        return customerRepository.findCustmersWithoutOrders().stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }
    public Customer findCustomerById(Long id) {
        Optional<Customer> customer = findById(id);
        verifyCustomerExists(customer);
        return customer.get();
    }

    private void verifyCustomerExists(Optional<Customer> customer) {
        if (customer.isEmpty())
            throw new AuthException(EErrorType.CUSTOMER_NOT_FOUND);
    }


    public List<Customer> getCustomersWithName(String name) {
        return customerRepository.findByNameContaining(name);
    }


}
