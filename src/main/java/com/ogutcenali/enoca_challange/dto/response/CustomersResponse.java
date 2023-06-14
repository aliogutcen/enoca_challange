package com.ogutcenali.enoca_challange.dto.response;

import com.ogutcenali.enoca_challange.model.Order;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomersResponse {
    private Long id;
    private String name;
    private Integer age;

}
