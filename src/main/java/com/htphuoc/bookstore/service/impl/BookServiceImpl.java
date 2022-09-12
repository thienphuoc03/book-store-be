package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.BookDto;
import com.htphuoc.bookstore.exception.AlreadyExistsException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.*;
import com.htphuoc.bookstore.repository.AuthorRepository;
import com.htphuoc.bookstore.repository.BookRepository;
import com.htphuoc.bookstore.repository.CategoryRepository;
import com.htphuoc.bookstore.repository.PublishingCompanyRepository;
import com.htphuoc.bookstore.service.AuthorService;
import com.htphuoc.bookstore.service.BookService;
import com.htphuoc.bookstore.service.CategoryService;
import com.htphuoc.bookstore.service.PublishingCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublishingCompanyRepository publishingCompanyRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @Override
    public ResponseEntity<Object> getAllBook(Integer page, Integer size) {
        List<BookDto> bookDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Book> books = bookRepository.findAll(pageable).getContent();
            returnBookDtos(bookDtos, books);
        } else {
            List<Book> books = bookRepository.findAll();
            returnBookDtos(bookDtos, books);
        }

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDto> getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            BookDto bookDto = modelMapper.map(book, BookDto.class);
            bookDto.setCategoryName(book.getCategories());
            bookDto.setAuthorName(book.getAuthors());
            bookDto.publishingCompanyName(book.getPublishingCompany());
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }
        throw new NotFoundException("Not found book");
    }

    @Override
    public ResponseEntity<Object> searchBooks(String keyword) {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (book.getName().equals(keyword)) {
                BookDto bookDto = modelMapper.map(book, BookDto.class);
                bookDto.setCategoryName(book.getCategories());
                bookDto.setAuthorName(book.getAuthors());
                bookDto.publishingCompanyName(book.getPublishingCompany());
                bookDtos.add(bookDto);
            }
        }

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getBookByCategoryId(Long id, Integer page, Integer size) {
        List<BookDto> bookDtos = new ArrayList<>();
        Category category = categoryRepository.findById(id).orElse(null);

        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Book> books = bookRepository.findAll(pageable).getContent();
            returnFilterBookDtos(bookDtos, category, books);
        } else {
            List<Book> books = bookRepository.findAll();
            returnFilterBookDtos(bookDtos, category, books);
        }

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDto> addBook(BookDto bookDto) throws Exception {
        Book book = new Book();
        if (bookRepository.existsBookByName(bookDto.getName())) {
            throw new AlreadyExistsException("Book already exists !!!");
        }

        bookDto.setStatus(1);
        modelMapper.map(bookDto, book);
        checkAndAddInfBook(book, bookDto);

        Book newBook = bookRepository.save(book);
        BookDto newBookDto = modelMapper.map(newBook, BookDto.class);

        return new ResponseEntity<>(newBookDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto) {
        Book oldBook = bookRepository.findById(id).orElse(null);
        if (oldBook != null) {
            bookDto.setCreatedAt(oldBook.getCreatedAt());
            bookDto.setCreatedBy(oldBook.getCreatedBy());
            bookDto.setStatus(oldBook.getStatus());

            modelMapper.map(bookDto, oldBook.getClass());

            checkAndAddInfBook(oldBook, bookDto);

            Book updateBook = bookRepository.save(oldBook);

            BookDto updateBookDto = modelMapper.map(updateBook, BookDto.class);
            updateBookDto.setCategoryName(updateBook.getCategories());
            updateBookDto.setAuthorName(updateBook.getAuthors());
            updateBookDto.publishingCompanyName(updateBook.getPublishingCompany());

            return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Not found book");
    }

    @Override
    public ResponseEntity<Object> deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            String bookName = book.getName();
            bookRepository.delete(book);
            return ResponseEntity.ok("You successfully deleted book: " + bookName);
        }

        throw new NotFoundException("Book not found");
    }


    // function
    private void returnBookDtos(List<BookDto> bookDtos, List<Book> books) {
        for (Book book : books) {
            BookDto bookDto = modelMapper.map(book, BookDto.class);
            bookDto.setCategoryName(book.getCategories());
            bookDto.setAuthorName(book.getAuthors());
            bookDto.publishingCompanyName(book.getPublishingCompany());
            bookDtos.add(bookDto);
        }
    }

    private void returnFilterBookDtos(List<BookDto> bookDtos, Category category, List<Book> books) {
        for (Book book : books) {
            if (book.getCategories().contains(category)) {
                BookDto bookDto = modelMapper.map(book, BookDto.class);
                bookDto.setCategoryName(book.getCategories());
                bookDto.setAuthorName(book.getAuthors());
                bookDto.publishingCompanyName(book.getPublishingCompany());
                bookDtos.add(bookDto);
            }
        }
    }

    private void checkAndAddInfBook(Book book, BookDto bookDto) {
        //set category and check if not exists then create new
        List<Category> categories = new ArrayList<>();
        for (String categoryName : bookDto.getCategories()) {
            Category category = categoryRepository.findByName(categoryName);
            if (category != null) {
                categoryService.createCategory(categoryName);
            }
            categories.add(category);
        }
        book.setCategories(categories);

        //set author and check if not exists then create new
        List<Author> authors = new ArrayList<>();
        for (String authorName : bookDto.getAuthors()) {
            Author author = authorRepository.findByName(authorName);
            if (author == null) {
                authorService.createAuthor(authorName);
            }
            authors.add(author);
        }
        book.setAuthors(authors);

        // Set Publishing Company and check if not exists then create new
        PublishingCompany publishingCompany = publishingCompanyRepository.findByName(bookDto.getPublishing_company());
        if (publishingCompany == null) {
            publishingCompanyService.createPublishingCompany(bookDto.getPublishing_company());
        }
        book.setPublishingCompany(publishingCompany);
    }
}
