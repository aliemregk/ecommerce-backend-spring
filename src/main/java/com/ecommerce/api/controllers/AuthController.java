package com.ecommerce.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.business.abstracts.AuthService;
import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.results.ResultChecker;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<DataResult<String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        DataResult<User> result = authService.login(loginRequest);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage(), null));
        }
        return ResultChecker.checkResult(authService.createToken(result.getData()));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<DataResult<String>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        DataResult<User> result = authService.register(registerRequest);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage(), null));
        }
        return ResultChecker.checkResult(authService.createToken(result.getData()));
    }

}
