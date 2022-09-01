package com.htphuoc.bookstore.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDto {
    private Long id;

    private String name;

    private List<UserDto> userDtos = new ArrayList<>();
}
