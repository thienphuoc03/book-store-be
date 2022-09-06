package com.htphuoc.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRatingDto {
    private Long id;

    private String url;

    private Integer book_ranting_id;
}
