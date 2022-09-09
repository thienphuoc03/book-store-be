package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.AuthorDto;
import com.htphuoc.bookstore.exception.AlreadyExistsException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Author;
import com.htphuoc.bookstore.repository.AuthorRepository;
import com.htphuoc.bookstore.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<Object> getAllAuthor() {
        List<AuthorDto> authorDtos = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
            authorDtos.add(authorDto);
        }

        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorDto> addAuthor(Author author) throws Exception{
        if (authorRepository.existsAuthorByName(author.getName())) {
            throw new AlreadyExistsException("Author already exists !!!");
        }
        Author newAuthor = authorRepository.save(author);
        AuthorDto authorDto = modelMapper.map(newAuthor, AuthorDto.class);

        return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthorDto> updateAuthor(Long id, Author author) {
        author.setId(id);
        Author oldAuthor = authorRepository.findById(id).orElse(null);
        if (oldAuthor != null) {
            author.setCreatedAt(oldAuthor.getCreatedAt());
            author.setCreatedBy(oldAuthor.getCreatedBy());
            oldAuthor = modelMapper.map(author, oldAuthor.getClass());
            Author updateAuthor = authorRepository.save(oldAuthor);
            AuthorDto authorDto = modelMapper.map(updateAuthor, AuthorDto.class);

            return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Author not found with id = " + id);
    }

    @Override
    public ResponseEntity<Object> deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            String authorName = author.getName();
            authorRepository.delete(author);

            return new ResponseEntity<>("You successfully deleted author " + authorName, HttpStatus.OK);
        }

        throw new NotFoundException("Author not found with id = " + id);
    }

    @Override
    public void createAuthor(String name) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        authorRepository.save(newAuthor);
    }
}
