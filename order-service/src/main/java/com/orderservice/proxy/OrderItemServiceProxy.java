package com.orderservice.proxy;

import com.orderservice.model.Order;
import com.orderservice.model.OrderItem;
import com.orderservice.model.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(url="http://localhost:8080/api",name="order-item-service")
public interface OrderItemServiceProxy {
	
	@GetMapping("/orderItems/orders/{orderId}")
	public ResponseData getOrderById(@PathVariable("orderId")Long orderId);

	@PostMapping("/orderItems")
	public ResponseData createOrderItems(List<OrderItem> orderItems);

}