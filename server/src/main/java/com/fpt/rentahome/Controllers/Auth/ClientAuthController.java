package com.fpt.rentahome.Controllers.Auth;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ClientAuthController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        // Check if email already exists
        if (clientService.getClientByEmail(clientRegistrationRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email address is already taken"));
        }

        // Create client object from request
        Client client = Client.builder()
                .name(clientRegistrationRequest.getName())
                .email(clientRegistrationRequest.getEmail())
                .phone(clientRegistrationRequest.getPhone())
                .password(passwordEncoder.encode(clientRegistrationRequest.getPassword()))
                .build();

        // Save client to database
        clientService.saveClient(client);

        return ResponseEntity.ok().body(new ApiResponse(true, "Client registered successfully"));
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
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
