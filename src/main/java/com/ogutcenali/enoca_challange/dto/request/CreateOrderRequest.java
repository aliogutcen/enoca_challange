package com.ogutcenali.enoca_challange.dto.request;

import lombok.*;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateOrderRequest {
    @Min(value = 0,message = "Total price must be equals or greater than 0")
    private Double totalPrice;
    private Long customerId;
}
