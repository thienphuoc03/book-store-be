package com.htphuoc.bookstore.dto;

import com.htphuoc.bookstore.model.User;
import lombok.Data;

@Data
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;
}
