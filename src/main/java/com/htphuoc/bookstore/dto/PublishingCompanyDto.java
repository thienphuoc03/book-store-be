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
public class PublishingCompanyDto {
    private Integer id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;

    private List<BookDto> bookDtos = new ArrayList<>();
}
