package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.OrderStateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrderStateService {
    ResponseEntity<Object> getAllOrderState(Integer page, Integer size);

    ResponseEntity<Object> getOrderStateByOrderId(Long id);

    ResponseEntity<Object> addOrderState(OrderStateDto orderStateDto);

    ResponseEntity<Object> updateOrderState(Long id, OrderStateDto orderStateDto);

    ResponseEntity<Object> deleteOrderState(Long id);
}
