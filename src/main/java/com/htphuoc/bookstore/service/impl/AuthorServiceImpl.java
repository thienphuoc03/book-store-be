package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.AuthorDto;
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
}
