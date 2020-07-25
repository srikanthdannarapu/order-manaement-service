package com.orderitem.repo;

import com.orderitem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findOrdersByOrderId(long orderId);


}