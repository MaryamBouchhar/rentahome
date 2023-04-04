package com.fpt.rentahome.Controllers.Auth;

import com.fpt.rentahome.Dto.ClientLoginRequest;
import com.fpt.rentahome.Dto.TokenRequest;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.AuthResponse;
import com.fpt.rentahome.Helpers.JWT.JwtTokenProvider;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Dto.ClientRegistrationRequest;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.data.relational.core.sql.Assignments.value;

@CrossOrigin
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
    public ResponseEntity<AuthResponse> registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest, HttpServletRequest request, HttpServletResponse response) {
        // Check if email already exists
        if (clientService.existsByEmail(clientRegistrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, false, "Email address is already taken", null));
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

        // Set the token as a cookie in the response
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().body(new AuthResponse(token, true, "Client successfully registered", client));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateClient(@RequestBody ClientLoginRequest request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        // Get client by email
        Optional<Client> clientOptional = clientService.getClientByEmail(request.getEmail());

        if (!clientOptional.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid email or password"));
        }

        Client client = clientOptional.get();

        // Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid email or password"));
        }

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(client.getEmail());

        // Set the token as a cookie in the response
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);

        // Return token in response
        return ResponseEntity.ok(new AuthResponse(token, true, "Successfully logged in", client));
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/finish")
    public ResponseEntity<?> logoutClient(@RequestBody TokenRequest request) {
        String token = request.getToken();
        System.out.println("Request token: " + token);
        return ResponseEntity.ok(new ApiResponse(true, "Successfully logged out"));
    }

    @CrossOrigin(origins = "http://localhost:5173/")
    @PostMapping("/check")
    public ResponseEntity<?> checkClient(@RequestBody TokenRequest request) {
        String token = request.getToken();
        System.out.println("Token: " + token);

        if(JwtTokenProvider.validateToken(token)) {
            String email = JwtTokenProvider.getEmailFromJWT(token);

            Optional<Client> clientOptional = clientService.getClientByEmail(email);

            if(clientOptional.isPresent()) {
                return ResponseEntity.ok(new AuthResponse(token, true, "Session is valid", clientOptional.get()));
            } else {
                return ResponseEntity.ok(new ApiResponse(false, "Session is invalid"));
            }
        } 
        return ResponseEntity.ok(new ApiResponse(false, "Session is invalid"));
    }
}
