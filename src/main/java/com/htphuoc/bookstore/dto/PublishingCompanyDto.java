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
public class PublishingCompanyDto {
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;

    private List<BookDto> bookDtos = new ArrayList<>();
}
