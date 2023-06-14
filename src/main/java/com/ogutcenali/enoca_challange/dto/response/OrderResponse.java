package com.ogutcenali.enoca_challange.dto.response;

import com.ogutcenali.enoca_challange.model.Customer;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponse {
    private Long id;
    private LocalDate createDate;
    private Double totalPrice;
    private Long customerId;
}
