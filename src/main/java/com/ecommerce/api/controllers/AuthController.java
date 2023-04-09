package com.ecommerce.api.controllers;

import javax.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.AuthService;
import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.responses.auth.AuthResponse;
import com.ecommerce.core.entities.concretes.User;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;

    @PostMapping(path = "/login")
    public DataResult<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        final User result = authService.login(loginRequest);
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return authService.createAuthResponse(result);
    }

    @PostMapping(path = "/register")
    public DataResult<AuthResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        final String pw = registerRequest.getPassword();
        final User result = authService.register(registerRequest);
        authenticate(registerRequest.getEmail(), pw);
        return authService.createAuthResponse(result);
    }

    private void authenticate(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

}
