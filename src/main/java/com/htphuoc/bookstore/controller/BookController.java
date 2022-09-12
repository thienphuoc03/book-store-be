package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.BookDto;
import com.htphuoc.bookstore.model.Book;
import com.htphuoc.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> getAllBook(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return bookService.getAllBook(page, size);
    }

    @GetMapping("/{id}")
    private ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchBooks(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        return bookService.searchBooks(keyword);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<Object> getBookByCategoryId(@PathVariable(name = "id") Long id,
                                                      @RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                                      @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return bookService.getBookByCategoryId(id, page, size);
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) throws Exception{
        return bookService.addBook(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(name = "id") Long id,
                                              @Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(name = "id") Long id) {
        return bookService.deleteBook(id);
    }
}
