package com.htphuoc.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;

    private String name;

    private Long price;

    private Integer quantity;

    private String description;

    private String avatar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;

    private Integer status;

    private List<BookRatingDto> bookRatingDtos = new ArrayList<>();

    private Integer publishing_company_id;

    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

    private Integer category_id;

    private Integer author_id;
}
