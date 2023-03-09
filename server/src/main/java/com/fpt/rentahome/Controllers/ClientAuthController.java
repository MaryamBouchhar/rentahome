package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Dto.ClientDto;
import com.fpt.rentahome.Helpers.JwtRequest;
import com.fpt.rentahome.Helpers.JwtResponse;
import com.fpt.rentahome.Helpers.JwtTokenUtil;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class ClientAuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ClientService clientService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = (UserDetails) clientService.findByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> saveClient(@RequestBody ClientDto clientDto) throws Exception {
        Client user = clientService.findByUsername(clientDto.getUsername());
        return ResponseEntity.ok(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            clientService.findByUsername(username);
        } catch (Exception e) {
            throw new Exception("USER_NOT_FOUND", e);
        }
    }
}
