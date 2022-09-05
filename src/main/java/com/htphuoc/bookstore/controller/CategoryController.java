package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.CategoryDto;
import com.htphuoc.bookstore.model.Category;
import com.htphuoc.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable(name = "id") Long id, @RequestBody Category newCategory) {
        return categoryService.updateCategory(id, newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable(name = "id") Long id) {
        return categoryService.deleteCategory(id);
    }
}
