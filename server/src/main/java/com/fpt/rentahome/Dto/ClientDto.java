package com.fpt.rentahome.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public ClientDto(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
