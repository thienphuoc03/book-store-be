package com.htphuoc.bookstore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;

    private Integer quantity;

    private Long totalAmount;

    private Integer order_id;

    private Integer book_id;
}
