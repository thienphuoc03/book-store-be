package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.OrderDetailDto;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Order;
import com.htphuoc.bookstore.model.OrderDetail;
import com.htphuoc.bookstore.repository.OrderDetailRepository;
import com.htphuoc.bookstore.service.OrderDetailService;
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
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<Object> getAllOrderDetail(Integer page, Integer size) {
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
            List<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable).getContent();

            orderDetailDtos = modelMapper.map(orderDetails, orderDetailDtos.getClass());
        } else {
            List<OrderDetail> orderDetails = orderDetailRepository.findAll();
            orderDetailDtos = modelMapper.map(orderDetails, orderDetailDtos.getClass());
        }

        return new ResponseEntity<>(orderDetailDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getOrderDetailById(Long id) {
        OrderDetail oldOrderDetail = orderDetailRepository.findById(id).orElse(null);
        if (oldOrderDetail != null) {
            OrderDetailDto orderDetailDto = modelMapper.map(oldOrderDetail, OrderDetailDto.class);

            return new ResponseEntity<>(orderDetailDto, HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order Detail !!!");
    }

    @Override
    public ResponseEntity<Object> addOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = modelMapper.map(orderDetailDto, OrderDetail.class);
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        OrderDetailDto newOrderDetailDto = modelMapper.map(newOrderDetail, OrderDetailDto.class);

        return new ResponseEntity<>(newOrderDetailDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateOrderDetail(Long id, OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        if (orderDetail != null) {
            orderDetail = modelMapper.map(orderDetailDto, orderDetail.getClass());
            orderDetailRepository.save(orderDetail);

            OrderDetailDto newOrderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);

            return new ResponseEntity<>(newOrderDetailDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Not found Order detail !!!");
    }

    @Override
    public ResponseEntity<Object> deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        if (orderDetail != null) {
            orderDetailRepository.delete(orderDetail);

            return new ResponseEntity<>("You successfully deleted Order detail", HttpStatus.OK);
        }

        throw new NotFoundException("Not found Order detail !!!");
    }
}