package com.htphuoc.bookstore.repository;

import com.htphuoc.bookstore.model.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
}
