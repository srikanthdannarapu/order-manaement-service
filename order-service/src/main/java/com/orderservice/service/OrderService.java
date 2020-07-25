package com.orderservice.service;

import com.orderservice.model.Order;
import com.orderservice.model.OrderItem;
import com.orderservice.model.OrderVo;
import com.orderservice.model.ResponseData;
import com.orderservice.proxy.OrderItemServiceProxy;
import com.orderservice.repo.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderItemServiceProxy proxy;
    @Autowired
    private OrderRepository repo;

    public Optional<OrderVo> getOrderById(Long id) {
        ResponseData data = proxy.getOrderById(id);
        List<OrderItem> orderItems = data.getOrderItems();
        OrderVo vo = new OrderVo();
        vo.setOrderItems(orderItems);
        Optional<Order> order = repo.findById(id);
        vo.setCustomerName(order.get().getCustomerName());
        vo.setShippingAddress(order.get().getShippingAddress());
        vo.setOrderDate(order.get().getOrderDate());
        vo.setTotal(order.get().getTotal());
        return Optional.of(vo);

    }

    public OrderVo createOrder(OrderVo orderVo) {
        Order order = repo.save( new Order(null, orderVo.getCustomerName(), orderVo.getOrderDate(),
                orderVo.getShippingAddress(), orderVo.getTotal()));
        Long id = order.getId();
        final Long orderId = order.getId();
        List<OrderItem> modifiedOrderItems = orderVo.getOrderItems().stream()
                .map(orderItem -> new OrderItem(null, orderId, orderItem.getProductCode(),
                        orderItem.getProductName(), orderItem.getQuantity()))
                .collect(Collectors.toList());
        logger.info("{}",modifiedOrderItems);
        ResponseData data = proxy.createOrderItems(modifiedOrderItems);

        List<OrderItem> orderItems = data.getOrderItems();
        OrderVo vo = new OrderVo();
        vo.setTotal(order.getTotal());
        vo.setCustomerName(order.getCustomerName());
        vo.setShippingAddress(order.getShippingAddress());
        vo.setOrderDate(order.getOrderDate());
        vo.setOrderItems(orderItems);

        return vo;
    }
}
