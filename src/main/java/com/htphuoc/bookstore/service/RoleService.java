package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface RoleService {
    ResponseEntity<Object> getAllRole();

    ResponseEntity<RoleDto> addRole(RoleDto roleDto) throws Exception;

    ResponseEntity<RoleDto> updateRole(Long id, RoleDto roleDto);

    ResponseEntity<Object> deleteRole(Long id);
}
