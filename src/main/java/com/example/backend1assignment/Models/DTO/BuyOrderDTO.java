package com.example.backend1assignment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyOrderDTO {
    private Long customerId, itemId;
    private String orderNumber;
}
