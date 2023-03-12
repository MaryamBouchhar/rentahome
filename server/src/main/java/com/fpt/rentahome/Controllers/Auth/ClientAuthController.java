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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
    public ResponseEntity<AuthResponse> registerClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest, HttpServletRequest request, HttpServletResponse response) {
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

        // Create a new session and return the session ID as a token
        HttpSession session = request.getSession(true);

        session.setAttribute("client", client);

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(session.getId());

        // Set the token as a cookie in the response
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().body(new AuthResponse(true, "Client registered successfully", token));
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

        // Create a new session and return the session ID as a token
        HttpSession session = servletRequest.getSession();

        if (session.getAttribute("client") == null) {
            session.setAttribute("client", client);
        }

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(session.getId());

        // Set the token as a cookie in the response
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);

        // Return token in response
        return ResponseEntity.ok(new AuthResponse(true, "Successfully logged in", token));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/logout")
    public ResponseEntity<?> logoutClient(@CookieValue("token") String token) {
        // Get session ID from token
        String sessionId = jwtTokenUtil.getSessionIdFromToken(token);

        System.out.println("Session ID: " + sessionId);

        // Invalidate session
        jwtTokenUtil.invalidateSession(sessionId);

        return ResponseEntity.ok(new ApiResponse(true, "Successfully logged out"));
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkClient(@CookieValue("token") String token) {
        // Get session ID from token
        String sessionId = jwtTokenUtil.getSessionIdFromToken(token);

        // Check if session is valid
        if (jwtTokenUtil.isSessionValid(sessionId)) {
            return ResponseEntity.ok(new ApiResponse(true, "Session is valid"));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Session is invalid"));
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedClient(@CookieValue("token") String token, HttpServletRequest request) {
        // Get session ID from token
        String sessionId = jwtTokenUtil.getSessionIdFromToken(token);

        HttpSession session = request.getSession(false);

        // Check if session is valid
        if (jwtTokenUtil.isSessionValid(sessionId)) {
            return ResponseEntity.ok(new ApiResponse(true, "You are authorized to access this resource"));
        }

        Client attribute = (Client) session.getAttribute("client");

        class response {
            boolean success = true;
            Client client = attribute;
            LocalDateTime timestamp = LocalDateTime.now();
        }

        return ResponseEntity.ok(new response());
    }
}
