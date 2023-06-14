package com.ogutcenali.enoca_challange.controller;

import com.ogutcenali.enoca_challange.dto.request.CreateOrderRequest;
import com.ogutcenali.enoca_challange.dto.response.OrderResponse;
import com.ogutcenali.enoca_challange.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
    @GetMapping("/after/{date}")
    public ResponseEntity<List<OrderResponse>> getOrdersCreatedAfter(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(orderService.getOrdersCreatedAfter(date));
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<Map<String, List<Long>>> getCustomersAndOrderIdsByName(@PathVariable String name) {
        Map<String, List<Long>> customersAndOrders = orderService.getCustomersAndOrderIdsByName(name);
        return new ResponseEntity<>(customersAndOrders, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity <>(HttpStatus.OK);
    }
}
