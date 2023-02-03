package com.htphuoc.bookstore.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespone {
	private String access_token;
	private Long id;
	private String username;
	private List roles;
}
