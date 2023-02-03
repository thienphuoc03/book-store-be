package com.htphuoc.bookstore.repository;

import com.htphuoc.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT distinct * FROM Books AS b ORDER BY b.created_at", nativeQuery = true, countQuery = "SELECT count(*)")
    Page<Book> findAllBooks(Pageable pageable);

    Boolean existsBookByName(String name);

//    Page<Book> findBookByCategoryId(Long categoryId, Pageable pageable);
}
