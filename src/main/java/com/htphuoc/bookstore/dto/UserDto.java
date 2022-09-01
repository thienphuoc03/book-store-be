package com.htphuoc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.htphuoc.bookstore.model.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyAt;

    private Integer status;

//    private List<Role> roles = new ArrayList<>();

    private List<BookRatingDto> bookRatingDtos = new ArrayList<>();

    private List<OrderDto> orderDtos = new ArrayList<>();
}
