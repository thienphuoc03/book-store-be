package com.htphuoc.bookstore.repository;

import com.htphuoc.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    Page<Book> findBookByCategoryId(Long categoryId, Pageable pageable);

    Boolean existsBookByName(String name);
}
