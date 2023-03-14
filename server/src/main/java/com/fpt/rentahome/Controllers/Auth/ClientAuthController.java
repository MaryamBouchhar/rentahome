package com.fpt.rentahome.Controllers.Auth;

import com.fpt.rentahome.Dto.ClientLoginRequest;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.AuthResponse;
import com.fpt.rentahome.Helpers.JWT.JwtTokenProvider;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Dto.ClientRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ClientAuthController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        // Check if email already exists
        if (clientService.existsByEmail(clientRegistrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse(false, "Email address is already taken", null));
        }

        // Create client object from request
        Client client = Client.builder()
                .name(clientRegistrationRequest.getName())
                .email(clientRegistrationRequest.getEmail())
                .phone(clientRegistrationRequest.getPhone())
                .password(passwordEncoder.encode(clientRegistrationRequest.getPassword()))
                .build();

        // Save client to database
        clientService.createClient(client);

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(client.getEmail());

        return ResponseEntity.ok().body(new AuthResponse(true, "Client registered successfully", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateClient(@RequestBody ClientLoginRequest clientLoginRequest) {
        // Get client by email
        Optional<Client> clientOptional = clientService.getClientByEmail(clientLoginRequest.getEmail());

        if (!clientOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid email or password"));
        }

        Client client = clientOptional.get();

        // Check if password matches
        if (!passwordEncoder.matches(clientLoginRequest.getPassword(), client.getPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid email or password"));
        }

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(client.getEmail());

        // Return token in response
        return ResponseEntity.ok(new AuthResponse(true, "Successfully logged in", token));
    }
}
