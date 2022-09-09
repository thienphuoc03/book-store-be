package com.htphuoc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.htphuoc.bookstore.model.Role;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyAt;

    private Integer status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Role> roles = new ArrayList<>();

    private List<String> rolesName = new ArrayList<>();

    private List<BookRatingDto> bookRatingDtos = new ArrayList<>();

    private List<OrderDto> orderDtos = new ArrayList<>();

    // function
    public List<String> setRolesName(List<Role> roleList) {
        List<String> roleName = new ArrayList<>();
        for (Role role : roleList) {
            roleName.add(role.getName());
            rolesName = roleName;
        }

        return rolesName;
    }
}
