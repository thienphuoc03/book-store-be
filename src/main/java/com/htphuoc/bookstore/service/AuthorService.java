package com.htphuoc.bookstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {
    ResponseEntity<Object> getAllAuthor();
}
