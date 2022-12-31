package com.ecommerce.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authManager) {
        this.authService = authService;
        this.authManager = authManager;
    }

    @PostMapping(path = "/login")
    public DataResult<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        final DataResult<User> result = authService.login(loginRequest);
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        return authService.createToken(result.getData());
    }

    @PostMapping(path = "/register")
    public DataResult<String> register(@RequestBody @Valid RegisterRequest registerRequest) {
        final String pw = registerRequest.getPassword();
        final DataResult<User> result = authService.register(registerRequest);
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), pw));
        return authService.createToken(result.getData());
    }

}
