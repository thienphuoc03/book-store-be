package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.OrderStateDto;
import com.htphuoc.bookstore.service.OrderStateService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/order-states")
public class OrderStateController {
    @Autowired
    private OrderStateService orderStateService;

    @GetMapping
    public ResponseEntity<Object> getAllOrderState(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return orderStateService.getAllOrderState(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderStateByOrderId(@PathVariable(name = "id") Long id) {
        return orderStateService.getOrderStateByOrderId(id);
    }

    @PostMapping
    public ResponseEntity<Object> addOrderState(@RequestBody @Valid OrderStateDto orderStateDto) {
        return orderStateService.addOrderState(orderStateDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrderState(@PathVariable(name = "id") Long id,
                                                   @RequestBody @Valid OrderStateDto orderStateDto) {
        return orderStateService.updateOrderState(id, orderStateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderState(@PathVariable (name = "id") Long id) {
        return orderStateService.deleteOrderState(id);
    }
}
