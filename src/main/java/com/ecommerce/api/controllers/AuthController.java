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
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;

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
        final DataResult<User> result = authService.login(loginRequest);
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return authService.createToken(result.getData());
    }

    @PostMapping(path = "/register")
    public DataResult<AuthResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        final String pw = registerRequest.getPassword();
        final DataResult<User> result = authService.register(registerRequest);
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        authenticate(registerRequest.getEmail(), pw);
        return authService.createToken(result.getData());
    }

    private void authenticate(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

}
