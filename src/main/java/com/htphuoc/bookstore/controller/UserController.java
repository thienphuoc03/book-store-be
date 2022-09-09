package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.UserDto;
import com.htphuoc.bookstore.model.User;
import com.htphuoc.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUser(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return userService.getAllUser(page, size);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUserProfile(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        return userService.searchUser(keyword);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) throws Exception {
        return userService.addUser(userDto);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id,@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.deleteUser(id);
    }

//    @PutMapping("/updatePassword/{id}")
//    private ResponseEntity<Object> changePassword(@PathVariable(name = "id") Long id, @RequestBody ChangePassword changePassword){
//        return userService.changePassword(id, changePassword);
//    }
}
