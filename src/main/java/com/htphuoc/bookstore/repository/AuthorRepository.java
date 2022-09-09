package com.htphuoc.bookstore.repository;

import com.htphuoc.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Boolean existsAuthorByName(@NotBlank String name);

    Author findByName(String name);
}
