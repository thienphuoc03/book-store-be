package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.RoleDto;
import com.htphuoc.bookstore.exception.AlreadyExistsException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Category;
import com.htphuoc.bookstore.model.Role;
import com.htphuoc.bookstore.repository.RoleRepository;
import com.htphuoc.bookstore.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<Object> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            RoleDto roleDto = modelMapper.map(role, RoleDto.class);
            roleDtos.add(roleDto);
        }

        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> addRole(RoleDto roleDto) throws Exception {
        if (roleRepository.existsByName(roleDto.getName())) {
            throw new AlreadyExistsException("Role already exists !!!");
        }
        return returnNewRole(roleDto);
    }

    @Override
    public ResponseEntity<RoleDto> updateRole(Long id, RoleDto roleDto) {
        roleDto.setId(id);
        Role oldRole = roleRepository.findById(id).orElse(null);
        if (oldRole != null) {
            return returnNewRole(roleDto);
        }

        throw new NotFoundException("Role not found");
    }

    @Override
    public ResponseEntity<Object> deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            roleRepository.delete(role);

            return new ResponseEntity<>("You successfully deleted role", HttpStatus.OK);
        }

        throw new NotFoundException("Role not found");
    }

    // function
    private ResponseEntity<RoleDto> returnNewRole(RoleDto roleDto) {
        String prefix = "ROLE_";
        roleDto.setName(prefix.concat(roleDto.getName().toUpperCase()));
        Role role = modelMapper.map(roleDto, Role.class);
        Role newRole = roleRepository.save(role);
        RoleDto newRoleDto = modelMapper.map(newRole, RoleDto.class);

        return new ResponseEntity<>(newRoleDto, HttpStatus.CREATED);
    }
}
