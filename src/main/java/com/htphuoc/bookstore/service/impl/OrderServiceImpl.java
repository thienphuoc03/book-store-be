package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.OrderDto;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Order;
import com.htphuoc.bookstore.repository.OrderRepository;
import com.htphuoc.bookstore.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<Object> getAllOrder(Integer page, Integer size) {
        List<OrderDto> orderDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
            List<Order> orders = orderRepository.findAll(pageable).getContent();

            orderDtos = modelMapper.map(orders, orderDtos.getClass());
        } else {
            List<Order> orders = orderRepository.findAll();
            orderDtos = modelMapper.map(orders, orderDtos.getClass());
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getOrderById(Long id) {
        Order oldOrder = orderRepository.findById(id).orElse(null);
        if (oldOrder != null) {
            OrderDto orderDto = modelMapper.map(oldOrder, OrderDto.class);

            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order");
    }

    @Override
    public ResponseEntity<Object> addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        Order newOrder = orderRepository.save(order);
        OrderDto newOrderDto = modelMapper.map(newOrder, OrderDto.class);

        return new ResponseEntity<>(newOrderDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateOrder(Long id, OrderDto orderDto) {
        Order oldOrder = orderRepository.findById(id).orElse(null);
        if (oldOrder != null) {
            orderDto.setCreatedAt(oldOrder.getCreatedAt());
            orderDto.setCreatedBy(oldOrder.getCreatedBy());

            Order updateOrder = modelMapper.map(orderDto, Order.class);
            orderRepository.save(updateOrder);

            OrderDto newOrderDto = modelMapper.map(updateOrder, OrderDto.class);

            return new ResponseEntity<>(newOrderDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Not found Order !!!");
    }

    @Override
    public ResponseEntity<Object> deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            orderRepository.delete(order);

            return new ResponseEntity<>("You successfully deleted Order", HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order !!!");
    }
}
