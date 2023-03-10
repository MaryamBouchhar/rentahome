package com.fpt.rentahome.services.Impl;

import com.fpt.rentahome.Dto.ClientRequestDto;
import com.fpt.rentahome.Dto.responses.AuthResponse;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Security.jwt.JwtTokenProvider;
import com.fpt.rentahome.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(ClientRegistrationRequest clientRegistrationRequest) {
        Client existingClient = clientRepository.findByEmail(clientRegistrationRequest.getEmail());

        if (existingClient != null) {
            return new AuthResponse(false, "Email already exists", null);
        }

        Client client = Client.builder()
                .name(clientRegistrationRequest.getName())
                .email(clientRegistrationRequest.getEmail())
                .phone(clientRegistrationRequest.getPhone())
                .password(passwordEncoder.encode(clientRegistrationRequest.getPassword()))
                .build();

        clientRepository.save(client);

        String accessToken = jwtTokenProvider.createToken(client.getEmail(), client.getRole().toString());

        return new AuthResponse(true, "Registration Successful", accessToken);
    }

    @Override
    public AuthResponse login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String accessToken = jwtTokenProvider.createToken(email, userDetails.getAuthorities().toString());
            return new AuthResponse(true, "Login Successful", accessToken);
        } catch (AuthenticationException e) {
            return new AuthResponse(false, "Invalid email/password supplied", null);
        }
    }
}
