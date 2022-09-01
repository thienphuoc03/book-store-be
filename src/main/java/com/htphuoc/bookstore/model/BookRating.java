package com.htphuoc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "book_ratings")
public class BookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "content")
    private String content;

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

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false, referencedColumnName = "id")
    private Book book;

    @OneToMany(mappedBy = "bookRating", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageRating> imageRatings = new ArrayList<>();
}
