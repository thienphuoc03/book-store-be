package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.AuthorDto;
import com.htphuoc.bookstore.model.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {
    ResponseEntity<Object> getAllAuthor();

    ResponseEntity<AuthorDto> addAuthor(Author author) throws Exception;

    ResponseEntity<AuthorDto> updateAuthor(Long id, Author author);

    ResponseEntity<Object> deleteAuthor(Long id);
}
