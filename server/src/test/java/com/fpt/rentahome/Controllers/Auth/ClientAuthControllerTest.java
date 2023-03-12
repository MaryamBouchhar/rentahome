package com.fpt.rentahome.Controllers.Auth;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Helpers.JWT.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClientAuthControllerTest {
    @InjectMocks
    private ClientAuthController myApi;

    @Mock
    private JwtTokenProvider jwtTokenUtil;

    private String token;

    @Test
    void registerClient() {

    }

    @Test
    void authenticateClient() {
    }

    @Test
    void logoutClient() {
    }

    @Test
    void checkClient() {
    }

    @Test
    void protectedClient() {
        // Prepare a mock HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("token", token));

        // Mock the JWT token util to return a valid session ID
        String sessionId = "valid-session-id";
        when(jwtTokenUtil.getSessionIdFromToken(token)).thenReturn(sessionId);
        when(jwtTokenUtil.isSessionValid(sessionId)).thenReturn(true);

        // Call the API
        ResponseEntity<?> response = myApi.protectedClient(token, request);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Check if the response contains the expected success message
        assertTrue(response.getBody() instanceof ApiResponse);
        ApiResponse apiResponse = (ApiResponse) response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertEquals("You are authorized to access this resource", apiResponse.getMessage());
    }
}