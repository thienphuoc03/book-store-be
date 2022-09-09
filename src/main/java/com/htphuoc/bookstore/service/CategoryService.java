package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.CategoryDto;
import com.htphuoc.bookstore.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CategoryService {
    ResponseEntity<Object> getAllCategory();

    ResponseEntity<CategoryDto> addCategory(Category category);

    ResponseEntity<CategoryDto> updateCategory(Long id, Category newCategory);

    ResponseEntity<HttpStatus> deleteCategory(Long id);

    void createCategory(String name);
}
