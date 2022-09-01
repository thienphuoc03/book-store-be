package com.htphuoc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    private String name;

    private String deliveryAddress;

    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private Date createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyAt;

    private Date modifyBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryAt;

    private Long total;

    private Integer user_id;

    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    private Integer order_state_id;
}
