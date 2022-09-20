package com.htphuoc.bookstore.dto;

import com.htphuoc.bookstore.model.Author;
import com.htphuoc.bookstore.model.Category;
import com.htphuoc.bookstore.model.PublishingCompany;
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
public class BookDto {
    private Long id;

    private String avatar;

    private String name;

    private List<String> categories = new ArrayList<>();

    private List<String> authors = new ArrayList<>();

    private String publishing_company;

    private String description;

    private Long price;

    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private String modifyBy;
//
//    private List<BookRatingDto> bookRatingDtos = new ArrayList<>();
//
//    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
//
    private Integer status;

    //function
    public String publishingCompanyName(PublishingCompany publishingCompany) {

        return publishing_company = publishingCompany.getName();
    }

    public List<String> setCategoryName(List<Category> categoryList) {
        List<String> categoryName = new ArrayList<>();
        for (Category category : categoryList) {
            categoryName.add(category.getName());
            categories = categoryName;
        }

        return categories;
    }

    public List<String> setAuthorName(List<Author> authorList) {
        List<String> authorName = new ArrayList<>();
        for (Author author : authorList) {
            authorName.add(author.getName());
            authors = authorName;
        }

        return authors;
    }
}
