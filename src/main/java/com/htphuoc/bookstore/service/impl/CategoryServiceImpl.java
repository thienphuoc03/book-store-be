package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.CategoryDto;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Category;
import com.htphuoc.bookstore.repository.CategoryRepository;
import com.htphuoc.bookstore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> getAllCategory() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDtos.add(categoryDto);
        }

        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> addCategory(Category category) {
        Category newCategory = categoryRepository.save(category);
        CategoryDto categoryDto = modelMapper.map(newCategory, CategoryDto.class);

        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(Long id, Category newCategory) {
        newCategory.setId(id);
        Category oldCategory = categoryRepository.findById(id).orElse(null);
        if (oldCategory != null) {
            newCategory.setCreatedAt(oldCategory.getCreatedAt());
            newCategory.setCreatedBy(oldCategory.getCreatedBy());
            oldCategory = modelMapper.map(newCategory, oldCategory.getClass());
            Category updateCategory = categoryRepository.save(oldCategory);
            CategoryDto categoryDto = modelMapper.map(updateCategory, CategoryDto.class);
            return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Category not found with id = " + id);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
            return new ResponseEntity<HttpStatus>(HttpStatus.valueOf("You successfully deleted category"), HttpStatus.OK);
        }

        throw new NotFoundException("Category not found with id = " + id);
    }
}
