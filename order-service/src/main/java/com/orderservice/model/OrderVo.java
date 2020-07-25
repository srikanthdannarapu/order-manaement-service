package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
    @NotNull(message = "Please provide a customerName")
    private String customerName;
    @FutureOrPresent
    private Date orderDate;
    private String shippingAddress;
    List<OrderItem> orderItems;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal total;
}
