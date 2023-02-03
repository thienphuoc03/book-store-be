package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.BookDto;
import com.htphuoc.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<BookDto> getAllBook(int page, int size, String sortBy);

    ResponseEntity<BookDto> getBookById(Long id);

//    ResponseEntity<Object> searchBooks(String keyword, Integer page, Integer size, String order, String sortBy);

    ResponseEntity<Object> searchBooks(String keyword);

    ResponseEntity<Object> getBookByCategoryId(Long id, Integer page, Integer size);

    ResponseEntity<BookDto> addBook(BookDto bookDto) throws Exception;

    ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto);

    ResponseEntity<Object> deleteBook(Long id);



//    ResponseEntity<Object> getAllBook1(Integer page, Integer size);
}
