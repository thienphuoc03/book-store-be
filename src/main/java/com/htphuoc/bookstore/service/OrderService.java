package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrderService {
    ResponseEntity<Object> getAllOrder(Integer page, Integer size);

    ResponseEntity<Object> getOrderById(Long id);

    ResponseEntity<Object> addOrder(OrderDto orderDto);

    ResponseEntity<Object> updateOrder(Long id, OrderDto orderDto);

    ResponseEntity<Object> deleteOrder(Long id);
}
