package com.htphuoc.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank
    @Column(name = "total_amount")
    private Long totalAmount;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false, referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false, referencedColumnName = "id")
    private Book book;
}
