package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.OrderDetailDto;
import com.htphuoc.bookstore.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Object> getAllOrderDetail(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                                    @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return orderDetailService.getAllOrderDetail(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderDetailById(@PathVariable(name = "id") Long id) {
        return orderDetailService.getOrderDetailById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addOrderDetail(@RequestBody @Valid OrderDetailDto orderDetailDto) {
        return orderDetailService.addOrderDetail(orderDetailDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderDetail(@PathVariable(name = "id") Long id,
                                                    @RequestBody @Valid OrderDetailDto orderDetailDto) {
        return orderDetailService.updateOrderDetail(id, orderDetailDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderDetail(@PathVariable(name = "id") Long id) {
        return orderDetailService.deleteOrderDetail(id);
    }
}
