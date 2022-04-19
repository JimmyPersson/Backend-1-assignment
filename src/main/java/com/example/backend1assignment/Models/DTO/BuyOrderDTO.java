package com.example.backend1assignment.Models.DTO;

import lombok.Data;

@Data
public class BuyOrderDTO {
    private Long customerId, itemId;
    private String orderNumber;
}
