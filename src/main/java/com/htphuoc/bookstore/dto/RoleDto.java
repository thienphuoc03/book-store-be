package com.htphuoc.bookstore.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;

    private String name;

    private List<UserDto> userDtos = new ArrayList<>();
}
