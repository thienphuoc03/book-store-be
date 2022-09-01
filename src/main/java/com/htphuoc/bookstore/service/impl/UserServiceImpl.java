package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.UserDto;
import com.htphuoc.bookstore.exception.BadRequestException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Role;
import com.htphuoc.bookstore.model.User;
import com.htphuoc.bookstore.repository.ApiResponse;
import com.htphuoc.bookstore.repository.RoleRepository;
import com.htphuoc.bookstore.repository.UserRepository;
import com.htphuoc.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> getAllUser(Integer page, Integer size) {
        List<UserDto> userDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<User> users = userRepository.findAll(pageable).getContent();
            for (User user : users) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                userDtos.add(userDto);
            }
        } else {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                userDtos.add(userDto);
            }
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        throw new NotFoundException("Not found user");
    }

    @Override
    public ResponseEntity<Object> searchUser(String keyword) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getName().equals(keyword)) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                userDtos.add(userDto);
            }
        }

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new BadRequestException(apiResponse);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = userRepository.save(user);
        UserDto userDto = modelMapper.map(newUser, UserDto.class);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
