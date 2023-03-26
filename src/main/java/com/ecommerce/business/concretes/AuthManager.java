package com.ecommerce.business.concretes;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.AuthService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.responses.auth.AuthResponse;
import com.ecommerce.business.responses.user.AuthUserModel;
import com.ecommerce.business.rules.AuthBusinessRules;
import com.ecommerce.core.dataaccess.UserRepository;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.exceptions.BusinessException;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.core.utilities.security.hashing.HashingService;
import com.ecommerce.core.utilities.security.jwt.JwtTokenUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final AuthBusinessRules authBusinessRules;
    private final HashingService hashingService;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User register(RegisterRequest registerRequest) {
        authBusinessRules.checkIfUserEmailExists(registerRequest.getEmail());
        registerRequest.setPassword(hashingService.createPassword(registerRequest.getPassword()));
        return userRepository.save(MapperUtil.map(registerRequest, User.class));
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User userToLogin = userRepository.getByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BusinessException("Email is not registered."));
        authBusinessRules.checkPassword(loginRequest.getPassword(), userToLogin.getPassword());
        return userToLogin;
    }

    @Override
    public DataResult<AuthResponse> createAuthResponse(User user) {
        final String accessToken = jwtTokenUtil.createToken(user);
        authBusinessRules.checkToken(accessToken);
        return new SuccessDataResult<>(Messages.TOKEN_MSG,
                new AuthResponse(accessToken, MapperUtil.map(user, AuthUserModel.class)));
    }

}
