package com.fpt.rentahome.services.Impl;

import com.fpt.rentahome.Dto.ClientRegistrationRequest;
import com.fpt.rentahome.Helpers.AuthResponse;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.JWT.JwtTokenProvider;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private List<UserDetailsService> userDetailsService;

    @Override
    public AuthResponse register(ClientRegistrationRequest clientRegistrationRequest) {
        Client existingClient = clientRepository.findByEmail(clientRegistrationRequest.getEmail());

        if (existingClient != null) {
            return new AuthResponse(null, false, "Email address is already taken", null);
        }

        Client client = Client.builder()
                .name(clientRegistrationRequest.getName())
                .email(clientRegistrationRequest.getEmail())
                .phone(clientRegistrationRequest.getPhone())
                .password(passwordEncoder.encode(clientRegistrationRequest.getPassword()))
                .build();

        clientRepository.save(client);

        String accessToken = jwtTokenProvider.generateToken(client.getEmail());

        return new AuthResponse(accessToken, true, "Registration Successful", client);
    }

    @Override
    public AuthResponse login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userDetailsService.get(0).loadUserByUsername(email);
            Client client = clientRepository.findByEmail(email);
            String accessToken = jwtTokenProvider.generateToken(email);
            return new AuthResponse(accessToken, true, "Login Successful", client);
        } catch (AuthenticationException e) {
            return new AuthResponse(null, false, "Invalid email or password", null);
        }
    }
}
