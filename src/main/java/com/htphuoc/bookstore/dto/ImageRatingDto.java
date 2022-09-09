package com.htphuoc.bookstore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRatingDto {
    private Long id;

    private String url;

    private Integer book_ranting_id;
}
