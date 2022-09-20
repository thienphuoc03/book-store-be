package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.OrderDto;
import com.htphuoc.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Object> getAllOrder(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return orderService.getAllOrder(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable(name = "id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody @Valid OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(name = "id") Long id, @RequestBody @Valid OrderDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(name = "id") Long id) {
        return orderService.deleteOrder(id);
    }
}
