package com.fpt.rentahome.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
}

