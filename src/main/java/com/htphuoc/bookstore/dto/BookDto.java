package com.htphuoc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookDto {
    private Long id;

    private String name;

    private Long price;

    private Integer quantity;

    private String description;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private Date createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyAt;

    private Date modifyBy;

    private Integer status;

    private List<BookRatingDto> bookRatingDtos = new ArrayList<>();

    private Integer publishing_company_id;

    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    private Integer category_id;

    private Integer author_id;
}
