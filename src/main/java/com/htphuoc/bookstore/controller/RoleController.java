package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.RoleDto;
import com.htphuoc.bookstore.model.Role;
import com.htphuoc.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Object> getAllRole() {
        return roleService.getAllRole();
    }

    @PostMapping
    public ResponseEntity<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto) throws Exception{
        return roleService.addRole(roleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable(name = "id") Long id,
                                              @Valid @RequestBody RoleDto roleDto) {
        return roleService.updateRole(id, roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable(name = "id") Long id) {
        return roleService.deleteRole(id);
    }
}
