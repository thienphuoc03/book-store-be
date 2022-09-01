package com.htphuoc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Cleanup;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "price")
    private Long price;

    @NotBlank
    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "avatar")
    private String avatar;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @NotBlank
    @CreatedBy
    @Column(name = "created_by")
    private Date createdBy;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "modify_at")
    private Date modifyAt;

    @NotBlank
    @LastModifiedBy
    @Column(name = "modify_by")
    private Date modifyBy;

    @NotBlank
    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookRating> bookRatings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="publishing_company_id", nullable=false, referencedColumnName = "id")
    private PublishingCompany publishingCompany;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authors = new ArrayList<>();
}
