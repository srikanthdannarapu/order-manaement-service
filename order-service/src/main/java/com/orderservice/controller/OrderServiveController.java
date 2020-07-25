package com.orderservice.controller;

import com.orderservice.model.Order;
import com.orderservice.model.OrderVo;
import com.orderservice.repo.OrderRepository;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api")
@Validated
public class OrderServiveController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;



    @GetMapping("/order/{id}")
    public ResponseEntity<OrderVo> findOrderById(@PathVariable @Min(1) Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderVo> createOrder(@Valid @RequestBody OrderVo orderVo) {
        return new ResponseEntity<>(orderService.createOrder(orderVo), HttpStatus.CREATED);
    }

}
