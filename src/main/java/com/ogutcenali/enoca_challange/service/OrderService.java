package com.ogutcenali.enoca_challange.service;

import com.ogutcenali.enoca_challange.dto.request.CreateOrderRequest;
import com.ogutcenali.enoca_challange.dto.response.OrderResponse;
import com.ogutcenali.enoca_challange.exception.AuthException;
import com.ogutcenali.enoca_challange.exception.EErrorType;
import com.ogutcenali.enoca_challange.mapper.IOrderMapper;
import com.ogutcenali.enoca_challange.model.Customer;
import com.ogutcenali.enoca_challange.model.Order;
import com.ogutcenali.enoca_challange.repository.OrderRepository;
import com.ogutcenali.enoca_challange.utility.ServiceManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order, Long> {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    private final IOrderMapper orderMapper = IOrderMapper.INSTANCE;

    public OrderService(OrderRepository orderRepository,@Lazy CustomerService customerService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public void createOrder(CreateOrderRequest createOrderRequest) {
        Customer customer = verifyCustomerExists(createOrderRequest.getCustomerId());
        Order order = Order.builder().createDate(LocalDate.now()).customer(customer).totalPrice(createOrderRequest.getTotalPrice()).build();
        save(order);
    }

    private Customer verifyCustomerExists(Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    public List<OrderResponse> getAllOrder() {
        return findAll().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersCreatedAfter(LocalDate date) {
        return orderRepository.findAllCreateAfter(date).stream().map(x -> OrderResponse.builder().customerId(x.getCustomer().getId()).id(x.getId()).totalPrice(x.getTotalPrice()).createDate(x.getCreateDate()).build()).collect(Collectors.toList());
    }

    public OrderResponse getOrder(Long id) {
        Order order = findOrderById(id);
        return OrderResponse.builder().createDate(order.getCreateDate()).customerId(order.getCustomer().getId()).totalPrice(order.getTotalPrice()).id(order.getId()).build();
    }

    public void deleteOrder(Long id) {
        Optional<Order> order = findById(id);
        verifyOrderExist(order);
        delete(order.get());

    }

    public Order findOrderById(Long id) {
        Optional<Order> order = findById(id);
        verifyOrderExist(order);
        return order.get();
    }

    private void verifyOrderExist(Optional<Order> order) {
        if (order.isEmpty()) throw new AuthException(EErrorType.ORDER_NOT_FOUND);
    }


    public Map<String, List<Long>> getCustomersAndOrderIdsByName(String name) {
        List<Customer> customers = customerService.getCustomersWithName(name);
        Map<String, List<Long>> customersAndOrders = new HashMap<>();
        for (Customer customer : customers) {
            List<Long> orderIds = orderRepository.findIdsByCustomer(customer);
            customersAndOrders.put(customer.getName(), orderIds);
        }
        return customersAndOrders;
    }

    public void deleteOrderForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomer_Id(customerId);
        if (orders != null) {
            orderRepository.deleteAll(orders);
        }

    }
}
