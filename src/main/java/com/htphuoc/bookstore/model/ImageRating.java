package com.htphuoc.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_ratings")
public class ImageRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name="book_ranting_id", nullable=false, referencedColumnName = "id")
    private BookRating bookRating;
}
