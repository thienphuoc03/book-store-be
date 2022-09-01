package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.UserDto;
import com.htphuoc.bookstore.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    ResponseEntity<Object> getAllUser(Integer page, Integer size);

    ResponseEntity<UserDto> getUserById(Long id);

    ResponseEntity<Object> searchUser(String keyword);

    ResponseEntity<UserDto> addUser(User user);
}
