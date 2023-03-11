package com.fpt.rentahome.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {
    private String name;
    private String email;
    private String phone;
    private String password;
}
