package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.OrderStateDto;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Order;
import com.htphuoc.bookstore.model.OrderState;
import com.htphuoc.bookstore.repository.OrderRepository;
import com.htphuoc.bookstore.repository.OrderStateRepository;
import com.htphuoc.bookstore.service.OrderStateService;
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
public class OrderStateServiceImpl implements OrderStateService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<Object> getAllOrderState(Integer page, Integer size) {
        List<OrderStateDto> orderStateDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
            List<OrderState> orderStates = orderStateRepository.findAll(pageable).getContent();

            orderStateDtos = modelMapper.map(orderStates, orderStateDtos.getClass());
        } else {
            List<OrderState> orderStates = orderStateRepository.findAll();
            orderStateDtos = modelMapper.map(orderStates, orderStateDtos.getClass());
        }

        return new ResponseEntity<>(orderStateDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getOrderStateByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            OrderState orderState = order.getOrderState();

            return new ResponseEntity<>(orderState, HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order State!!!");
    }

    @Override
    public ResponseEntity<Object> addOrderState(OrderStateDto orderStateDto) {
        OrderState newOrderState = modelMapper.map(orderStateDto, OrderState.class);
        orderStateRepository.save(newOrderState);

        OrderStateDto newOrderStateDto = modelMapper.map(newOrderState, OrderStateDto.class);

        return new ResponseEntity<>(newOrderStateDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateOrderState(Long id, OrderStateDto orderStateDto) {
        OrderState orderState = orderStateRepository.findById(id).orElse(null);
        if (orderState != null) {
            OrderState newOrderState = modelMapper.map(orderStateDto, orderState.getClass());
            orderStateRepository.save(newOrderState);

            OrderStateDto newOrderStateDto = modelMapper.map(newOrderState, OrderStateDto.class);

            return new ResponseEntity<>(newOrderStateDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Not Found OrderState");
    }

    @Override
    public ResponseEntity<Object> deleteOrderState(Long id) {
        OrderState orderState = orderStateRepository.findById(id).orElse(null);
        if (orderState != null) {
            String name = orderState.getName();
            orderStateRepository.delete(orderState);

            return new ResponseEntity<>("You successfully deleted Order State: " + name, HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order State!!!");
    }
}
