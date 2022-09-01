package com.htphuoc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookRatingDto {
    private Long id;

    private Integer rating;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    private Date createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyAt;

    private Date modifyBy;

    private Integer user_id;

    private Integer book_id;

    private List<ImageRatingDto> imageRatingDtos = new ArrayList<>();
}
