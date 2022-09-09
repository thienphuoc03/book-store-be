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
public class BookRatingDto {
    private Long id;

    private Integer rating;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;

    private Integer user_id;

    private Integer book_id;

    private List<ImageRatingDto> imageRatingDtos = new ArrayList<>();
}
