package com.Thienbao.booking.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String email;
//    private String password;
    private String fullname;
    private String avartar;
    private String address;
    private String phone;
    private String sex;
    private int role;
    private boolean isDeleted;
}
