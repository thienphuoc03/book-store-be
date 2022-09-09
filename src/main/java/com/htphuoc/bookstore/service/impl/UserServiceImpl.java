package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.UserDto;
import com.htphuoc.bookstore.exception.AlreadyExistsException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Role;
import com.htphuoc.bookstore.model.User;
import com.htphuoc.bookstore.repository.RoleRepository;
import com.htphuoc.bookstore.repository.UserRepository;
import com.htphuoc.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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
    public ResponseEntity<UserDto> addUser(UserDto userDto) throws Exception{
        User user = new User();
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new AlreadyExistsException("Username already exists !!!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new AlreadyExistsException("Email is already taken");
        }

        userDto.setStatus(1);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user = modelMapper.map(userDto, user.getClass());

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);

        User newUser = userRepository.save(user);

        UserDto newUserDto = modelMapper.map(newUser, UserDto.class);
        newUserDto.setRolesName(newUser.getRoles());

        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) {
        User updateUser = new User();
        userDto.setId(id);
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser != null) {
            userDto.setStatus(oldUser.getStatus());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setCreateAt(oldUser.getCreateAt());

            List<Role> roles = new ArrayList<>();
            for (GrantedAuthority roleNames : oldUser.getAuthorities()) {
                roles.add(roleRepository.findByName(roleNames.toString()));
            }
            updateUser.setRoles(roles);

            oldUser = modelMapper.map(userDto, oldUser.getClass());
            oldUser.setRoles(updateUser.getRoles());
            updateUser = userRepository.save(oldUser);

            UserDto updateUserDto = modelMapper.map(updateUser, UserDto.class);
            updateUserDto.setRolesName(updateUser.getRoles());

            return new ResponseEntity<>(updateUserDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("Not found user in system");
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            String username = user.getName();
            userRepository.delete(user);
            return ResponseEntity.ok("You successfully deleted user: " + username);
        }

        throw new NotFoundException("Not found user in system");
    }

//    @Override
//    public ResponseEntity<Object> changePassword(Long id, ChangePassword changePassword) {
//        User user = new User();
//        user.setId(id);
//        User oldUser = userRepository.findById(user.getId()).orElse(null);
//        if (oldUser != null) {
//            String oldPass = changePassword.getOldPassword();
//            if (userRepository.existsByPassword(oldPass)) {
//                String newPass = changePassword.getNewPassword();
//                String reNewPass = changePassword.getReNewPassword();
//                if (newPass == reNewPass) {
//                    user.setPassword(newPass);
//                    userRepository.save(user);
//
//                    return ResponseEntity.ok("Change password successfully");
//                } else {
//                    ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "The new password is not the same");
//                    throw new BadRequestException(apiResponse);
//                }
//            } else {
//                ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Old password is not correct");
//                throw new BadRequestException(apiResponse);
//            }
//        }
//
//        throw new NotFoundException("Not found user in system");
//    }
}
