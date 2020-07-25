package com.orderitem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Srikanth Dannarapu
 */
@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Please provide a orderId")
    private Long orderId;

    @NotNull(message = "Please provide a productCode")
    @Min(value = 1, message = "productCode must be >= 1")
    private int productCode;

    @NotEmpty(message = "Please provide a productName")
    private String productName;

    @NotNull(message = "Please provide a quantity")
    @Min(value = 1, message = "quantity must be >= 1")
    private int quantity;

}
