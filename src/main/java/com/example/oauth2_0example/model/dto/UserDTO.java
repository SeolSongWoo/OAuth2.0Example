package com.example.oauth2_0example.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String user_name;
    private String user_id;
    private String user_password;
    private String user_type;
    private String user_email;
    private String user_sex;
}
