package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.AuthorDto;
import com.htphuoc.bookstore.model.Author;
import com.htphuoc.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Object> getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody Author author) throws Exception {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable(name = "id") Long id, @Valid @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(name = "id") Long id) {
        return authorService.deleteAuthor(id);
    }
}
