package com.ogutcenali.enoca_challange.controller;

import com.ogutcenali.enoca_challange.dto.request.CreateCustomerRequest;
import com.ogutcenali.enoca_challange.dto.request.UpdateAgeRequest;
import com.ogutcenali.enoca_challange.dto.request.UpdateNameRequest;
import com.ogutcenali.enoca_challange.dto.response.CustomersResponse;
import com.ogutcenali.enoca_challange.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        customerService.createCustomer(createCustomerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CustomersResponse>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomersResponse> getCustomerInfo(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerResponse(id));
    }
    @GetMapping("/without-orders")
    public ResponseEntity<List<CustomersResponse>> getCustomersWithoutOrders() {
        return ResponseEntity.ok(customerService.getCustomersWithoutOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/update-age")
    public ResponseEntity<CustomersResponse> updateCustomerAge(@PathVariable Long id, @RequestBody UpdateAgeRequest age) {
       return ResponseEntity.ok(customerService.updateCustomerAge(id,age));
    }
    @PutMapping("/{id}/update-name")
    public ResponseEntity<CustomersResponse> updateCustomerName(@PathVariable Long id,@RequestBody UpdateNameRequest name){
        return ResponseEntity.ok(customerService.updateCustomerName(id,name));
    }



}
