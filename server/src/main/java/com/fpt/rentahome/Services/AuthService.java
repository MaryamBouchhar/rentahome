package com.fpt.rentahome.Services;

import com.fpt.rentahome.Dto.ClientRegistrationRequest;
import com.fpt.rentahome.Helpers.AuthResponse;

public interface AuthService {
    AuthResponse register(ClientRegistrationRequest clientRegistrationRequest);
    AuthResponse login(String email, String password);
}
