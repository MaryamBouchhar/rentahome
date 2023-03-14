package com.fpt.rentahome.Controllers.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.rentahome.Dto.ClientLoginRequest;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.AuthResponse;
import com.fpt.rentahome.Helpers.JWT.JwtTokenProvider;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Dto.ClientRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientAuthController.class)
@ExtendWith(MockitoExtension.class)
public class ClientAuthControllerTest {

    @Mock
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private ClientAuthController clientAuthController;

    @Test
    public void shouldReturnOkWhenRegisteringValidClient() throws Exception {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
        clientRegistrationRequest.setName("Test User");
        clientRegistrationRequest.setEmail("test@example.com");
        clientRegistrationRequest.setPhone("0123456789");
        clientRegistrationRequest.setPassword("password");

        Client savedClient = Client.builder()
                .name(clientRegistrationRequest.getName())
                .email(clientRegistrationRequest.getEmail())
                .phone(clientRegistrationRequest.getPhone())
                .password(clientRegistrationRequest.getPassword())
                .build();

        when(clientService.existsByEmail(clientRegistrationRequest.getEmail())).thenReturn(false);
        when(!clientService.existsByEmail(clientRegistrationRequest.getEmail())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRegistrationRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Client registered successfully"));
    }

    @Test
    public void shouldReturnBadRequestWhenRegisteringWithExistingEmail() throws Exception {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
        clientRegistrationRequest.setName("Test User");
        clientRegistrationRequest.setEmail("test@example.com");
        clientRegistrationRequest.setPhone("0123456789");
        clientRegistrationRequest.setPassword("password");

        when(clientService.existsByEmail(clientRegistrationRequest.getEmail())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRegistrationRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Email address is already taken"));
    }

    private String asJsonString(ClientRegistrationRequest clientRegistrationRequest) {
        try {
            return new ObjectMapper().writeValueAsString(clientRegistrationRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnOkWhenAuthenticatingValidClient() throws Exception {
        ClientLoginRequest clientLoginRequest = new ClientLoginRequest();
        clientLoginRequest.setEmail("test@example.com");
        clientLoginRequest.setPassword("password");

        Client client = Client.builder()
                .name("Test User")
                .email(clientLoginRequest.getEmail())
                .phone("0123456789")
                .password(clientLoginRequest.getPassword())
                .build();

        when(clientService.getClientByEmail(clientLoginRequest.getEmail())).thenReturn(Optional.of(client));
        when(jwtTokenProvider.generateToken(any())).thenReturn("token");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientLoginRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Client logged in successfully"));


    }

    private String asJsonString(ClientLoginRequest clientLoginRequest) {
        try {
            return new ObjectMapper().writeValueAsString(clientLoginRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}