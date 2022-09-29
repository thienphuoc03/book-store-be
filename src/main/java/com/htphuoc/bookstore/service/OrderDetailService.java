package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.OrderDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrderDetailService {
    ResponseEntity<Object> getAllOrderDetail(Integer page, Integer size);

    ResponseEntity<Object> getOrderDetailById(Long id);

    ResponseEntity<Object> addOrderDetail(OrderDetailDto orderDetailDto);

    ResponseEntity<Object> updateOrderDetail(Long id, OrderDetailDto orderDetailDto);

    ResponseEntity<Object> deleteOrderDetail(Long id);
}
