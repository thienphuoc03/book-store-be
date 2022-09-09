package com.htphuoc.bookstore.dto;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    private String name;

    private String deliveryAddress;

    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryAt;

    private Long total;

    private Integer user_id;

    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    private Integer order_state_id;
}
