package com.orderitem.controller;

import com.orderitem.exception.OrderItemNotFoundException;
import com.orderitem.model.OrderItem;
import com.orderitem.model.ResponseData;
import com.orderitem.repo.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/orderItems")
    public ResponseEntity<ResponseData> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        if (orderItems.size() > 0) {
            ResponseData responseData = new ResponseData();
            responseData.setOrderItems(orderItems);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            throw new OrderItemNotFoundException("No OrderItems Found");
        }
    }

    @GetMapping("/orderItems/{id}")
    public ResponseEntity<OrderItem> findOrderById(@PathVariable @Min(1) Long id) {
        return orderItemRepository.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new OrderItemNotFoundException("Order with id "+ id +" Not found"));
    }

    @GetMapping("/orderItems/orders/{orderId}")
    public ResponseEntity<ResponseData> findOrdersByOrderId(@PathVariable @Min(1) Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findOrdersByOrderId(orderId);
        if (orderItems.size() > 0) {
            ResponseData responseData = new ResponseData();
            responseData.setOrderItems(orderItems);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            throw new OrderItemNotFoundException("No OrderItems Found");
        }
    }

    @PostMapping("/orderItems")
    public ResponseEntity<ResponseData> createOrderItems(@Valid @RequestBody List<OrderItem> orderItems) {
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
            ResponseData responseData = new ResponseData();
            responseData.setOrderItems(savedOrderItems);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }
    @PostMapping("/orderItem")
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItem orderItem) {
        return new ResponseEntity<>(orderItemRepository.save(orderItem), HttpStatus.CREATED);
    }
}
