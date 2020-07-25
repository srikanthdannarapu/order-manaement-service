package com.orderitem.model;

import lombok.Data;

import java.util.List;
@Data
public class ResponseData {
    private List<OrderItem> orderItems;
}
