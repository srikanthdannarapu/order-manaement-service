package com.orderservice.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private List<Order> order;
}
