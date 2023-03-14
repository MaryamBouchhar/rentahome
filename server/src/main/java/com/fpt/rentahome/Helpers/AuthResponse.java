package com.fpt.rentahome.Helpers;

import com.fpt.rentahome.Models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private boolean success;
    private String message;
    private Client client;
}
